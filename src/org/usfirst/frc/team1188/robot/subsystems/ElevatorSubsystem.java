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

