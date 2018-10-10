package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.ravenhardware.BufferedDigitalInput;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.arm.ArmHoldPositionCommand;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmSubsystem extends Subsystem {
	TalonSRX armMotor;
	BufferedDigitalInput extensionLimitSwitch;
	BufferedDigitalInput retractionLimitSwitch;
	private Timer _safetyTimer = new Timer();
	
	public ArmSubsystem() {
		this.armMotor = new TalonSRX(RobotMap.armMotor);
		this.retractionLimitSwitch = new BufferedDigitalInput(RobotMap.armRetractionLimitSwitch);
		this.extensionLimitSwitch = new BufferedDigitalInput(RobotMap.armExtensionLimitSwitch);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ArmHoldPositionCommand());
    }
    
    public void periodic() {
    	retractionLimitSwitch.maintainState();
    	extensionLimitSwitch.maintainState();
    	
    	PCDashboardDiagnostics.SubsystemNumber("Arm", "Encoder", this.getEncoderPosition());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitEncoderExtension", this.isEncoderAtExtensionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitEncoderRetraction", this.isEncoderAtRetractionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitSwitchExtension", this.getExtensionLimitSwitchValue());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitSwitchRetraction", this.getRetractionLimitSwitchValue());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitFinalExtension", this.getIsAtExtensionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitFinalRetraction", this.getIsAtRetractionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitSwitchAndEncoderAgreeExtended", this.encoderAndLimitsMatchExtended());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitSwitchAndEncoderAgreeRetracted", this.encoderAndLimitsMatchRetracted());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "OverrideExtend", Robot.OVERRIDE_SYSTEM_ARM_EXTEND.getOverride1());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "OverrideRetract", Robot.OVERRIDE_SYSTEM_ARM_RETRACT.getOverride1());
    }
    
    public boolean encoderAndLimitsMatchExtended() {
    	boolean match = true;
    	
		if(this.getEncoderPosition() > Calibrations.armEncoderValueExtended  && this.getExtensionLimitSwitchValue() == false) {
			match = false;
		}
		 
		if(this.getExtensionLimitSwitchValue() == true && this.getEncoderPosition() < Calibrations.armEncoderValueExtended - Calibrations.ARM_ENCODER_BUFFER) {
			match = false;
		}
		
    	return match;
    }
    
    public boolean encoderAndLimitsMatchRetracted() {
    	boolean match = true;
    	
		if(this.getEncoderPosition() < Calibrations.armEncoderValueRetracted  && this.getRetractionLimitSwitchValue() == false) {
			match = false;
		}
		 
		if(this.getRetractionLimitSwitchValue() == true && this.getEncoderPosition() > Calibrations.armEncoderValueRetracted + Calibrations.ARM_ENCODER_BUFFER) {
			match = false;
		}
		
    	return match;
    }
    
    
	public boolean getExtensionLimitSwitchValue() {
		boolean extensionLimitSwitchValue = false;
		
		extensionLimitSwitchValue = !extensionLimitSwitch.get();
		
		return extensionLimitSwitchValue;
	}
	
	public boolean getRetractionLimitSwitchValue() {
		boolean retractionLimitSwitchValue = false;
		
		retractionLimitSwitchValue = !retractionLimitSwitch.get();
		
		return retractionLimitSwitchValue;
	}
	
	public boolean getIsExtendedPastEncoderPosition(int encoderPosition) {
    	boolean isPastPosition = false;
    	
		if (this.getEncoderPosition() > encoderPosition - Calibrations.ARM_ENCODER_BUFFER) {
			isPastPosition = true;
		}
		
    	return isPastPosition;
	}
	
	public boolean getIsRetractedBeforeEncoderPosition(int encoderPosition) {
    	boolean isPastPosition = false;
    	
		if (this.getEncoderPosition() < encoderPosition + Calibrations.ARM_ENCODER_BUFFER) {
			isPastPosition = true;
		}
		
    	return isPastPosition;
	}

	public boolean getIsAtPosition(int encoderPosition) {
		boolean isAtPosition = false;
		
		boolean notOverExtended = this.getIsExtendedPastEncoderPosition(encoderPosition);
		boolean notOverRetracted = this.getIsRetractedBeforeEncoderPosition(encoderPosition);
		
		if (notOverExtended == false && notOverRetracted == false) {
			isAtPosition = true;
		}
		return isAtPosition;
	}
	
    /*
    public boolean isAtBottomLimit() {
    	return this.getEncoderPosition() <= Calibrations.armEncoderValueAtBottom + Calibrations.ARM_ENCODER_BUFFER;
    }
    
    public boolean isAtTopLimit() {
    	return this.getEncoderPosition() >= Calibrations.armEncoderValueAtTop - Calibrations.ARM_ENCODER_BUFFER;
    }
    */
    
    
	 public boolean getIsAtRetractionLimit() {
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	encoderLimit = this.isEncoderAtRetractionLimit();
    	
    	if (this.getRetractionLimitSwitchValue() == true) {
    		switchLimit = true;
    		this.resetEncodersToRetractionLimit();
    	}
    	
    	return Robot.OVERRIDE_SYSTEM_ARM_RETRACT.getIsAtLimit(encoderLimit, switchLimit);
	 }
    
    public void expectArmToBeAtExtensionLimit() {
    	boolean isAtLimitSwitch = this.getExtensionLimitSwitchValue();
    	boolean isEncoderWithinRange = isEncoderAtExtensionLimit();
    	
    	if (isEncoderWithinRange == false && isAtLimitSwitch == true) {
    		this.resetEncodersToExtendedLimit();
    	}
    }
    
    public void expectArmToBeAtRetractionLimit() {
    	boolean isAtLimitSwitch = this.getRetractionLimitSwitchValue();
    	boolean isEncoderWithinRange = isEncoderAtRetractionLimit();
    	
    	if (isEncoderWithinRange == false && isAtLimitSwitch == true) {
    		this.resetEncodersToRetractionLimit();
    	}
    }
    
    public boolean isEncoderAtExtensionLimit() {
    	boolean encoderLimit = false;
    	if (this.getEncoderPosition() >= Calibrations.armEncoderValueExtended - Calibrations.ARM_ENCODER_BUFFER) {
    		encoderLimit = true;
    	}
    	return encoderLimit;
    }
    
    public boolean isEncoderAtRetractionLimit() {
    	boolean encoderLimit = false;
    	if (this.getEncoderPosition() <= Calibrations.armEncoderValueRetracted + Calibrations.ARM_ENCODER_BUFFER) {
    		encoderLimit = true;
    	}
    	return encoderLimit;
    }
    
    public boolean getIsAtExtensionLimit() {
    	boolean isAtLimit = false;
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	encoderLimit = this.isEncoderAtExtensionLimit();
    
    	if (this.getExtensionLimitSwitchValue() == true) {
    		switchLimit = true;
    		this.resetEncodersToExtendedLimit();
    	}
    	
    	isAtLimit = Robot.OVERRIDE_SYSTEM_ARM_EXTEND.getIsAtLimit(encoderLimit, switchLimit);
    	
    	return isAtLimit;
    }
    
    public int getEncoderPosition() {
    	return (armMotor.getSelectedSensorPosition(0));
    }
    
    public void resetEncodersToRetractionLimit() {
    	this.armMotor.setSelectedSensorPosition(Calibrations.armEncoderValueRetracted, 0, 0);
    	
    }
    
    public void resetEncodersToExtendedLimit() {
    	this.armMotor.setSelectedSensorPosition(Calibrations.armEncoderValueExtended, 0, 0);
    }
    
    /*
    public void resetEncodersTopLimitSwitch() {
    	boolean retractionLimitSwitchValue = false;
    	if(retractionLimitSwitchValue = retractionLimitSwitch.get()) {
    		this.resetEncodersToTop();
    	}
    }
    
    public void resetEncodersBottomLimitSwitch() {
    	boolean extensionLimitSwitchValue = false;
    	if(extensionLimitSwitchValue = retractionLimitSwitch.get()) {
    		this.resetEncodersToBottom();
    	}
    }
    */
    
    public void extend() {
    	this.extend(Calibrations.armExtensionPowerMagnitude);
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
    	this.retract(Calibrations.armRetractionPowerMagnitude);
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
    	// System.out.println("STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.");
    }
    
    
    private void set(double magnitude) {
    	magnitude = Math.min(magnitude, 1);
    	magnitude = Math.max(magnitude, -1);
    	magnitude *= Calibrations.armMaximumSpeed;
    	
    	PCDashboardDiagnostics.SubsystemNumber("Arm", "MotorOutputPercent", magnitude);
    	this.armMotor.set(ControlMode.PercentOutput, magnitude);
    	
    	//System.out.println("RMO: " + this.SSSMotor.getMotorOutputPercent() + " LMO: " + this.leftMotor.getMotorOutputPercent());
    }

	public boolean getIsExtendedPastMidway() {
    	boolean isPastMidway = false;
    	
		if (this.getEncoderPosition() > Calibrations.armEncoderValueMidway + Calibrations.ARM_MIDWAY_SINGLE_SIDE_BUFFER) {
			isPastMidway = true;
		}
		
    	return isPastMidway;
	}
	
	public boolean getIsRetractedBeforeMidway() {
    	boolean isPastMidway = false;
    	
		if (this.getEncoderPosition() < Calibrations.armEncoderValueMidway - Calibrations.ARM_MIDWAY_SINGLE_SIDE_BUFFER) {
			isPastMidway = true;
		}
		
    	return isPastMidway;
	}
	
	public boolean getIsAtMidway() {
		boolean isAtMidway = false;
		
		boolean notOverExtended = this.getIsExtendedPastMidway();
		boolean notOverRetracted = this.getIsRetractedBeforeMidway();
		
		if (notOverExtended == false && notOverRetracted == false) {
			isAtMidway = true;
		}
			
		return isAtMidway;
	}
	
	public boolean getIsExtendedPastHighScale() {
    	boolean isPastHighScale = false;
    	
		if (this.getEncoderPosition() > Calibrations.armEncoderValueHighScale + Calibrations.ARM_MIDWAY_SINGLE_SIDE_BUFFER) {
			isPastHighScale = true;
		}
		
    	return isPastHighScale;
	}
	
	public boolean getIsRetractedBeforeHighScale() {
    	boolean isPastHighScale = false;
    	
		if (this.getEncoderPosition() < Calibrations.armEncoderValueHighScale - Calibrations.ARM_MIDWAY_SINGLE_SIDE_BUFFER) {
			isPastHighScale = true;
		}
		
    	return isPastHighScale;
	}

	public boolean getIsAtHighScale() {
		boolean isAtHighScale = false;
		
		boolean notOverExtended = this.getIsExtendedPastHighScale();
		boolean notOverRetracted = this.getIsRetractedBeforeHighScale();
		
		if (notOverExtended == false && notOverRetracted == false) {
			isAtHighScale = true;
		}
			
		return isAtHighScale;
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

	public void holdPosition() {
		this.retract(Calibrations.armHoldPositionPowerMagnitude);
			
	}
		
}
