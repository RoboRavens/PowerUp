package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.ravenhardware.BufferedDigitalInput;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorHoldPositionCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorStopCommand;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
	public TalonSRX elevatorMotor;
	BufferedDigitalInput extendedLimitSwitch;
	BufferedDigitalInput retractedLimitSwitch;
	Encoder encoder;
	private Timer _safetyTimer = new Timer();
	private int _targetEncoderPosition;
	private double _expectedPower;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ElevatorSubsystem() {
		this.elevatorMotor = new TalonSRX(RobotMap.elevatorMotor);
		this.elevatorMotor.config_kP(2, 9, 9);
		this.encoder = new Encoder(RobotMap.elevatorEncoder1, RobotMap.elevatorEncoder2);
		this.retractedLimitSwitch = new BufferedDigitalInput(RobotMap.RetractionLimitSwitch);
		this.extendedLimitSwitch = new BufferedDigitalInput(RobotMap.ExtendedLimitSwitch);
		this._targetEncoderPosition = Calibrations.elevatorLiftEncoderMinimumValue;
		
		
	}
	
	public void setTargetEncoderPosition(int position) {
		this._targetEncoderPosition = position;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorHoldPositionCommand());
    } 
    
    public void extend() {
		this.extend(Calibrations.elevatorExtensionPowerMagnitude); 
	}
    
    public void extend(double magnitude) {
    	if (this.getIsAtExtensionLimit()) {
    		this.stop();
    	}
    	else {
        	this.set(magnitude);	
    	}
	}
    
    public void retract() {
		this.retract(Calibrations.elevatorRetractionPowerMagnitude); 
	}
    
    public void retract(double magnitude) {
    	if (this.getIsAtRetractionLimit()) {
    		this.stop();
    	}
    	else {
    		this.set(-1 * magnitude);
    	}
	}
    
    public void stop() {
		this.set(0);
	}
    
    public void getPosition() {
    	System.out.print("Elevator Position: " + this.getEncoderPosition());
    }
    
    public int getEncoderPosition() {
    	int EncoderPosition = this.elevatorMotor.getSelectedSensorPosition(0);
    	
    	return EncoderPosition;
    }
    
    public void periodic() {
    	retractedLimitSwitch.maintainState();
    	extendedLimitSwitch.maintainState();
    	this.getIsAtExtensionLimit();
    	this.getIsAtRetractionLimit();
    	
    	elevatorSubsystemDiagnostics();
    	checkExpectedSpeedVersusPower();
    }
    
    public void elevatorSubsystemDiagnostics() {
    	PCDashboardDiagnostics.SubsystemNumber("Elevator", "Encoder", this.getEncoderPosition());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitEncoderExtended", this.isEncoderAtExtensionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitEncoderRetracted", this.isEncoderAtRetractionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitSwitchExtended", this.getExtendedLimitSwitchValue());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitSwitchRetracted", this.getRetractionLimitSwitchValue());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitFinalExtension", this.getIsAtExtensionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitFinalRetraction", this.getIsAtRetractionLimit());
    	
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "OverrideExtend", Robot.OVERRIDE_SYSTEM_ELEVATOR_EXTEND.getOverride1());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "OverrideRetract", Robot.OVERRIDE_SYSTEM_ELEVATOR_RETRACT.getOverride1());

    	// Measure speed of elevator
    	PCDashboardDiagnostics.SubsystemNumber("Elevator", "EncoderRate", encoder.getRate());
    	// Measure power sent to elevator
    	PCDashboardDiagnostics.SubsystemNumber("Elevator", "EncoderExpectedPower", _expectedPower);
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitSwitchAndEncoderAgreeRetracted", this.encoderAndLimitsMatchRetracted());
    	PCDashboardDiagnostics.SubsystemBoolean("Elevator", "LimitSwitchAndEncoderAgreeExtended", this.encoderAndLimitsMatchExtended());
    }
    
    public boolean encoderAndLimitsMatchRetracted() {
    	boolean match = true;
    	
		if(this.getEncoderPosition() < Calibrations.elevatorLiftEncoderMinimumValue  && this.getRetractionLimitSwitchValue() == false) {
			match = false;
		}
		 
		if(this.getRetractionLimitSwitchValue() == true && this.getEncoderPosition() > Calibrations.elevatorLiftEncoderMinimumValue + Calibrations.elevatorLiftDownwardSafetyMargin) {
			match = false;
		}
		
    	return match;
    }
    
    public boolean encoderAndLimitsMatchExtended() {
    	boolean match = true;
    	
		if(this.getEncoderPosition() > Calibrations.elevatorLiftEncoderMaximumValue  && this.getExtendedLimitSwitchValue() == false) {
			match = false;
		}
		 
		if(this.getExtendedLimitSwitchValue() == true && this.getEncoderPosition() < Calibrations.elevatorLiftEncoderMaximumValue - Calibrations.elevatorLiftUpwardSafetyMargin) {
			match = false;
		}
		
    	return match;
    }
    
    public void checkExpectedSpeedVersusPower() {
    	// Check if elevator is being sent power and not moving at the right speed
    	if (Math.abs(_expectedPower) > Calibrations.elevatorHoldPositionPowerMagnitude) {
    		// The line below only returns as true if the elevator is pushing harder than it needs to not move it 
    		if (Math.abs(encoder.getRate()) < Calibrations.elevatorConsideredMovingEncoderRate) { 
    			burnoutProtection();
    		}
    	}
    }
    
    public void burnoutProtection() {
    	ElevatorStopCommand command = new ElevatorStopCommand();
    	command.start();
    }
    
    public int getElevatorPosition() {
    	int elevatorPosition;
    	elevatorPosition = (this.getEncoderPosition());
    	return elevatorPosition;
    }
    
    public void getIsAtLimits() {
    	System.out.print(" Extension Limit: " + this.getIsAtExtensionLimit() + " Retraction Limit: " + this.getIsAtRetractionLimit());
    }
    
    public void resetEncodersToRetractedLimit() {
    	this.elevatorMotor.setSelectedSensorPosition(Calibrations.elevatorLiftEncoderMinimumValue, 0, 0);
    }
    
    public void resetEncodersToExtendedLimit() {
    	this.elevatorMotor.setSelectedSensorPosition(Calibrations.elevatorLiftEncoderMaximumValue, 0, 0);
    }
    
    private void set(double magnitude) {
    	// System.out.println(rightMotor.get);
    	magnitude = Math.min(magnitude, 1);
    	magnitude = Math.max(magnitude, -1);
    	magnitude *= Calibrations.elevatorMaximumSpeed;
    	
    	if (getIsAtExtensionLimit() == true && Math.signum(magnitude) == 1) {
    		magnitude = 0;
    	}
    	if (getIsAtRetractionLimit() == true && Math.signum(magnitude) == -1) {
    		magnitude = 0;
    	}
    	
    	_expectedPower = magnitude;
    	
    	this.setMotors(magnitude);
    	// leftMotor.set(ControlMode.PercentOutput, -1 * magnitude);
    	
    }
    
    private void setMotors(double magnitude) {
    	PCDashboardDiagnostics.SubsystemNumber("Elevator", "MotorOutputPercent", magnitude);
    	elevatorMotor.set(ControlMode.PercentOutput, magnitude);
    }
    
    public void setMotorsPID(int position) {
    	elevatorMotor.set(ControlMode.Position, position);
    }
    
    // Right now this method just looks at the right limit switch; some combination of both should be used.
    public boolean getIsAtRetractionLimit() {
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	encoderLimit = this.isEncoderAtRetractionLimit();
    	
    	if (this.getRetractionLimitSwitchValue() == true) {
    		switchLimit = true;
    		this.resetEncodersToRetractedLimit();
    	}
    	
    	return Robot.OVERRIDE_SYSTEM_ELEVATOR_RETRACT.getIsAtLimit(encoderLimit, switchLimit);
    }
    
    public void expectElevatorToBeAtRetractionLimit() {
    	boolean isAtLimitSwitch = this.getRetractionLimitSwitchValue();
    	// boolean isEncoderWithinRange = isEncoderAtExtensionLimit();
    	
    	if (isAtLimitSwitch == true) {
    		this.resetEncodersToRetractedLimit();
    	}
    }
    
    public void expectElevatorToBeAtExtensionLimit() {
    	boolean isAtLimitSwitch = this.getExtendedLimitSwitchValue();
    	//boolean isEncoderWithinRange = isEncoderAtRetractionLimit();
    	
    	if (isAtLimitSwitch == true) {
    		this.resetEncodersToExtendedLimit();
    	}
    }
    
    public boolean isEncoderAtExtensionLimit() {
    	boolean encoderLimit = false;
    	
    	if (this.getEncoderPosition() >= Calibrations.elevatorLiftEncoderMaximumValue - Calibrations.elevatorLiftUpwardSafetyMargin) {
    		encoderLimit = true;
    	}
    	
    	return encoderLimit;
    }
    
    public boolean isEncoderAtRetractionLimit() {
    	boolean encoderLimit = false;
    	
    	if (this.getEncoderPosition() <= Calibrations.elevatorLiftEncoderMinimumValue + Calibrations.elevatorLiftDownwardSafetyMargin) {
    		encoderLimit = true;
    	}
    	
    	return encoderLimit;
    }
    
    // Right now this method just looks at the right limit switch; some combination of both should be used.
    public boolean getIsAtExtensionLimit() {
    	boolean isAtLimit = false;
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	encoderLimit = this.isEncoderAtExtensionLimit();
    
    	if (this.getExtendedLimitSwitchValue() == true) {
    		switchLimit = true;
    		this.resetEncodersToExtendedLimit();
    	}
    	
    	isAtLimit = Robot.OVERRIDE_SYSTEM_ELEVATOR_EXTEND.getIsAtLimit(encoderLimit, switchLimit);
    	
    	return isAtLimit;
    }

	public void holdPosition() {
		this.setMotors(Calibrations.elevatorHoldPositionPowerMagnitude);
		
	}
	
	public double getElevatorHeightPercentage() {
		double encoderMax = (double)Calibrations.elevatorLiftEncoderMaximumValue;
		double encoderMin = (double)Calibrations.elevatorLiftEncoderMinimumValue;
		double encoderCurrent = this.getElevatorPosition();
		
		double heightPercentage = (encoderCurrent - encoderMin)/(encoderMax - encoderMin);
		heightPercentage = Math.min(1, heightPercentage);
		heightPercentage = Math.max(0, heightPercentage);
		
		return heightPercentage;
	}
	
	public static double inchesToTicks(double inches) {
		double encoderTicks = inches;
		encoderTicks -= Calibrations.elevatorInchesToEncoderTicksOffsetValue;
		encoderTicks *= Calibrations.elevatorInchesToEncoderTicksConversionValue;
		
		return encoderTicks;
	}
	
	public static double ticksToInches(double encoderTicks) {
		double inches = encoderTicks;
		inches /= Calibrations.elevatorInchesToEncoderTicksConversionValue;
		inches += Calibrations.elevatorInchesToEncoderTicksOffsetValue;
		
		return inches;
	}
	
	public boolean getExtendedLimitSwitchValue() {
		boolean extendedLimitSwitchValue = false;
		
		extendedLimitSwitchValue = !extendedLimitSwitch.get();
		
		return extendedLimitSwitchValue;
	}
	
	public boolean getRetractionLimitSwitchValue() {
		boolean retractionLimitLimitSwitchValue = false;
		
		retractionLimitLimitSwitchValue = !retractedLimitSwitch.get();
		
		return retractionLimitLimitSwitchValue;
	}
	
	public boolean getIsExtendedPastEncoderPosition(int encoderPosition) {
    	boolean isPastMidway = false;
    	
		if (this.getEncoderPosition() > encoderPosition + Calibrations.ELEVATOR_AT_POSITION_BUFFER) {
			isPastMidway = true;
		}
		
    	return isPastMidway;
	}
	
	public boolean getIsRetractedBeforeEncoderPosition(int encoderPosition) {
    	boolean isPastMidway = false;
    	
		if (this.getEncoderPosition() < encoderPosition - Calibrations.ELEVATOR_AT_POSITION_BUFFER) {
			isPastMidway = true;
		}
		
    	return isPastMidway;
	}

	public boolean getIsAtPosition(int encoderPosition) {
		boolean isAtMidway = false;
		
		boolean notOverExtended = this.getIsExtendedPastEncoderPosition(encoderPosition);
		boolean notOverRetracted = this.getIsRetractedBeforeEncoderPosition(encoderPosition);
		
		if (notOverExtended == false && notOverRetracted == false) {
			isAtMidway = true;
		}
			
		return isAtMidway;
	}
	
	public void resetSafetyTimer() {
		_safetyTimer.reset();
	}
	
	public void startSafetyTimer() {
		_safetyTimer.start();
	}
	
	public double getSafetyTimer() {
		return _safetyTimer.get();
	}

}

