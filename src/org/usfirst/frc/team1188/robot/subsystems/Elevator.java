package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtend;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorStop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	public Robot robot;
	Joystick driveController;	
	TalonSRX extensionMotor;
	DigitalInput extensionLimit;
	DigitalInput retractionLimit;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Elevator(Robot robot, Joystick driveController, TalonSRX extensionMotor) {
		this.robot = robot;
		this.driveController = driveController;
		this.extensionMotor = extensionMotor;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorStop(this));
    }
    
    public void extend() {
		this.set(Calibrations.elevatorExtensionPowerMagnitude); 
	}
    
    public void retract() {
		this.set(-1 * Calibrations.elevatorRetractionPowerMagnitude); 
	}
    
    public void stop() {
		this.set(0);
	}
    
    private void set(double magnitude) {
    	extensionMotor.set(ControlMode.PercentOutput, magnitude);
    }
    
    public boolean getIsAtExtensionLimit() {
    	return extensionLimit.get();
    }
    
    public boolean getIsAtRetractionLimit() {
    	return retractionLimit.get();
    }
}

