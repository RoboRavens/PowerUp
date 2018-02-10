package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorStopCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {
	public Robot robot;
	Gamepad operatorController;	
	TalonSRX extensionMotor;
	DigitalInput extensionLimit;
	DigitalInput retractionLimit;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ElevatorSubsystem() {
		this.extensionMotor = new TalonSRX(RobotMap.elevatorMotor);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorStopCommand(this));
    }
    
    public void extend() {
		this.extend(Calibrations.elevatorExtensionPowerMagnitude); 
	}
    
    public void extend(double magnitude) {
		this.set(magnitude); 
	}
    
    public void retract() {
		this.retract(Calibrations.elevatorRetractionPowerMagnitude); 
	}
    
    public void retract(double magnitude) {
		this.set(-1 * magnitude); 
	}
    
    
    public void stop() {
		this.set(0);
	}
    
    private void set(double magnitude) {
    	magnitude = Math.min(magnitude, 1);
    	magnitude = Math.max(magnitude, -1);
    	magnitude *= Calibrations.elevatorMaximumSpeed;
    	
    	if (getIsAtExtensionLimit() == true && Math.signum(magnitude) == 1) {
    		magnitude = 0;
    	}
    	if (getIsAtRetractionLimit() == true && Math.signum(magnitude) == -1) {
    		magnitude = 0;
    	}
    	extensionMotor.set(ControlMode.PercentOutput, magnitude);
    }
    
    public boolean getIsAtExtensionLimit() {
    	return extensionLimit.get();
    }
    
    public boolean getIsAtRetractionLimit() {
    	return retractionLimit.get();
    }
}

