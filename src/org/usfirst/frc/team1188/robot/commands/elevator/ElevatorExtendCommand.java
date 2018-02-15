package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorExtendCommand extends Command {
	ElevatorSubsystem elevator;
	Robot robot;
    Gamepad operationController;
    Encoder encoder;

    public ElevatorExtendCommand(ElevatorSubsystem elevator, Gamepad driveController, Encoder encoder) {
    	requires(elevator);
    	this.elevator = elevator;
    	this.operationController = driveController;
    	this.encoder = encoder;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (elevator.getIsAtExtensionLimit() == false) {
        	elevator.extend();
    	}
    	else {
    		elevator.stop();
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
