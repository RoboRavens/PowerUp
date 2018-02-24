package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorHoldPositionCommand;
import org.usfirst.frc.team1188.util.LoggerOverlordLogID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
	TalonSRX leftMotor;
	TalonSRX rightMotor;
	DigitalInput extensionLimit;
	DigitalInput retractionLimit;
	Encoder encoder;
	private Timer _safetyTimer = new Timer();

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ElevatorSubsystem() {
		this.rightMotor = new TalonSRX(RobotMap.elevatorMotorRight);
		this.leftMotor = new TalonSRX(RobotMap.elevatorMotorLeft);
		this.leftMotor.setInverted(true);
		this.encoder = new Encoder(RobotMap.elevatorEncoder1, RobotMap.elevatorEncoder2);
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
    	System.out.print("Right: " + this.getRightEncoderPosition());
    	System.out.println("Left: " + this.getLeftEncoderPosition());
    }
    
    public int getLeftEncoderPosition() {
    	return leftMotor.getSelectedSensorPosition(0);
    }
    
    public int getRightEncoderPosition() {
    	int rightEncoderPosition = this.rightMotor.getSelectedSensorPosition(0);
    	
    	return rightEncoderPosition;
    }
    
    public int getElevatorPosition() {
    	int elevatorPosition;
    	
    	elevatorPosition = (rightMotor.getSelectedSensorPosition(0) + leftMotor.getSelectedSensorPosition(0)) / 2;
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ElevatorRI, "" + rightMotor.getSelectedSensorPosition(0));
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ElevatorLI, "" + leftMotor.getSelectedSensorPosition(0));
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ElevatorAvg, "" + elevatorPosition);
    	return elevatorPosition;
    }
    
    public void getIsAtLimits() {
    	System.out.print(" Extension Limit: " + this.getIsAtExtensionLimit() + " Retraction Limit: " + this.getIsAtRetractionLimit());
    }
    
    public void resetEncoders() {
    	this.rightMotor.setSelectedSensorPosition(Calibrations.elevatorLiftEncoderMinimumValue, 0, 0);
    	this.leftMotor.setSelectedSensorPosition(Calibrations.elevatorLiftEncoderMinimumValue, 0, 0);
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
    	
    	this.setMotors(magnitude);
    	// leftMotor.set(ControlMode.PercentOutput, -1 * magnitude);
    }
    
    private void setMotors(double magnitude) {
    	leftMotor.set(ControlMode.PercentOutput, magnitude);
    	rightMotor.set(ControlMode.PercentOutput, magnitude);
    }
    
    // Right now this method just looks at the right limit switch; some combination of both should be used.
    public boolean getIsAtRetractionLimit() {
    	boolean isAtRetractionLimit = false;
    	
    	if (this.getRightEncoderPosition() - Calibrations.elevatorLiftDownwardSafetyMargin < Calibrations.elevatorLiftEncoderMinimumValue) {
    		isAtRetractionLimit = true;
    	}
    	
    	
    	return isAtRetractionLimit;
    	// return retractionLimit.get();
    }
    
    // Right now this method just looks at the right limit switch; some combination of both should be used.
    public boolean getIsAtExtensionLimit() {
    	boolean isAtExtensionLimit = false;
    	
    	if (this.getRightEncoderPosition() + Calibrations.elevatorLiftUpwardSafetyMargin > Calibrations.elevatorLiftEncoderMaximumValue) {
    		isAtExtensionLimit = true;
    	}
    	
    	return isAtExtensionLimit;
    	// return extensionLimit.get();
    }

	public void holdPosition() {
		this.setMotors(Calibrations.elevatorHoldPositionPowerMagnitude);
		
	}
	
	public double getElevatorHeightPercentage() {
		return (double)this.getElevatorPosition() / (double)Calibrations.elevatorLiftEncoderMaximumValue;
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
	
	public double getEncoderValue() {
		return encoder.get();
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

