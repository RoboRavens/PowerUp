package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.arm.ArmStopCommand;
import org.usfirst.frc.team1188.util.LoggerOverlordLogID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmSubsystem extends Subsystem {
	TalonSRX leftMotor;
	TalonSRX rightMotor;
	DigitalInput extensionLimitSwitch;
	DigitalInput retractionLimitSwitch;
	
	public ArmSubsystem() {
		this.leftMotor = new TalonSRX(RobotMap.armMotorLeft);
		this.rightMotor = new TalonSRX(RobotMap.armMotorRight);
		this.leftMotor.setInverted(true);
		this.retractionLimitSwitch = new DigitalInput(RobotMap.armRetractionLimitSwitch);
		this.extensionLimitSwitch = new DigitalInput(RobotMap.armExtensionLimitSwitch);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ArmStopCommand(this));
    }
    
    public void periodic() {
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ArmRI, "" + this.rightMotor.getSelectedSensorPosition(0));
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ArmLI, "" + this.leftMotor.getSelectedSensorPosition(0));
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ArmAvg, "" + this.getEncoderPosition());
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ArmUpperLimitSwitch, "" + this.getExtensionLimitSwitchValue());
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ArmLowerLimitSwitch, "" + this.getRetractionLimitSwitchValue());
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ArmIsAtExtensionLimit, "" + this.getIsAtExtensionLimit());
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ArmIsAtRetractionLimit, "" + this.getIsAtRetractionLimit());
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
	
    /*
    public boolean isAtBottomLimit() {
    	return this.getEncoderPosition() <= Calibrations.armEncoderValueAtBottom + Calibrations.ARM_ENCODER_BUFFER;
    }
    
    public boolean isAtTopLimit() {
    	return this.getEncoderPosition() >= Calibrations.armEncoderValueAtTop - Calibrations.ARM_ENCODER_BUFFER;
    }
    */
    
    
    public boolean getIsAtRetractionLimit() {
    	boolean isAtLimit = false;
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	if (this.getEncoderPosition() >= Calibrations.armEncoderValueRetracted - Calibrations.ARM_ENCODER_BUFFER) {
    		encoderLimit = true;
    	}
    	
    	if (this.getRetractionLimitSwitchValue() == true) {
    		switchLimit = true;
    	}
    	
    	isAtLimit = Robot.OVERRIDE_SYSTEM.getIsAtLimit(encoderLimit, switchLimit, Robot.OPERATION_CONTROLLER);
    	
    	return isAtLimit;
    }
    
    public boolean getIsAtExtensionLimit() {
    	boolean isAtLimit = false;
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	
    	if (this.getEncoderPosition() <= Calibrations.armEncoderValueExtended + Calibrations.ARM_ENCODER_BUFFER) {
    		encoderLimit = true;
    	}
    	
    	if (this.getExtensionLimitSwitchValue() == true) {
    		switchLimit = true;
    	}
    	
    	isAtLimit = Robot.OVERRIDE_SYSTEM.getIsAtLimit(encoderLimit, switchLimit, Robot.OPERATION_CONTROLLER);
    	
    	return isAtLimit;
    }
    
    
    public int getEncoderPosition() {
    	return (rightMotor.getSelectedSensorPosition(0) + leftMotor.getSelectedSensorPosition(0)) / 2;
    }
    
    public void resetEncodersToTop() {
    	this.rightMotor.setSelectedSensorPosition(Calibrations.armEncoderValueRetracted, 0, 0);
    	this.leftMotor.setSelectedSensorPosition(Calibrations.armEncoderValueRetracted, 0, 0);
    }
    
    public void resetEncodersToBottom() {
    	this.rightMotor.setSelectedSensorPosition(Calibrations.armEncoderValueExtended, 0, 0);
    	this.leftMotor.setSelectedSensorPosition(Calibrations.armEncoderValueExtended, 0, 0);
    }
    
    public void extend() {
    	this.extend(Calibrations.armExtensionPowerMagnitude);
    }
    
    public void extend(double magnitude) {
    	if (this.getIsAtExtensionLimit()) {
    		this.stop();
    	}
    	else {
        	this.set(-1 * magnitude);	
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
    		this.set(magnitude);
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
    	
    	this.leftMotor.set(ControlMode.PercentOutput, magnitude);
    	this.rightMotor.set(ControlMode.PercentOutput, magnitude);
    	
    	//System.out.println("RMO: " + this.rightMotor.getMotorOutputPercent() + " LMO: " + this.leftMotor.getMotorOutputPercent());
    }
}