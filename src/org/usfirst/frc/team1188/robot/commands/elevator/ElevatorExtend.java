package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorExtend extends Command {
	Elevator elevator;
	Robot robot;
    Gamepad driveController;

    public ElevatorExtend(Elevator elevator, Gamepad driveController) {
    	requires(elevator);
    	this.elevator = elevator;
    	this.driveController = driveController;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (elevator.getIsAtExtensionLimit() == false) {
        	elevator.extend();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	if (elevator.getIsAtExtensionLimit()) {
    		isFinished = true;
    	} 	    	
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
