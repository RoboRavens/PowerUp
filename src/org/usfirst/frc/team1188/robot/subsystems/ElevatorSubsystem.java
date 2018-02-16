package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorHoldPositionCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorStopCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
	public Robot robot;
	Gamepad operatorController;	
	TalonSRX leftMotor;
	TalonSRX rightMotor;
	DigitalInput extensionLimit;
	DigitalInput retractionLimit;
	Encoder encoder;
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ElevatorSubsystem(Robot robot, Gamepad operatorController, Encoder encoder) {
		this.rightMotor = new TalonSRX(RobotMap.elevatorMotorRight);
		this.leftMotor = new TalonSRX(RobotMap.elevatorMotorLeft);
		this.leftMotor.setInverted(true);
		
		this.operatorController = operatorController;
		this.encoder = encoder;
		this.robot = robot;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorHoldPositionCommand(this, operatorController, encoder));
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
    	return rightMotor.getSelectedSensorPosition(0);
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
}

