package org.usfirst.frc.team1188.robot.commands.elevator;


import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorRetract extends Command {
	Elevator elevator;
	Robot robot;
    Joystick operationController;

    public ElevatorRetract(Elevator elevator, Joystick operationController) {
    	requires(elevator);
    	this.elevator = elevator;
    	this.robot = elevator.robot;
    	this.operationController = operationController;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (elevator.getIsAtRetractionLimit() == false) {
        	elevator.retract();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	if (elevator.getIsAtRetractionLimit()) {
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
