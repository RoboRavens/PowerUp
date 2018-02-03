package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeClampCloseCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeClampSubsystem extends Subsystem {
	Solenoid leftIntakeClamp = new Solenoid(RobotMap.leftIntakeClampSolenoid);
	Solenoid rightIntakeClamp = new Solenoid(RobotMap.rightIntakeClampSolenoid);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeClampCloseCommand(this));
    }
    
    public void close() {
    	leftIntakeClamp.set(false);
    	rightIntakeClamp.set(false);
    }
    
    public void open() {
    	leftIntakeClamp.set(true);
    	rightIntakeClamp.set(true);
    }
}

