package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHoldPositionCommand extends Command {
    int targetPosition;
    
    public ElevatorHoldPositionCommand() {
    	requires(Robot.ELEVATOR_SUBSYSTEM);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	// Set the target position to be whatever the elevator's position is when the command begins.
    	this.targetPosition = Robot.ELEVATOR_SUBSYSTEM.getElevatorPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// System.out.println("Holding elevator at " + Calibrations.elevatorHoldPositionPowerMagnitude + " power.");
    	// The goal of this command is to send a very small amount of power to the elevator motors
    	// to fight against gravity - NOT to move the elevator, at all. 
    	if (Robot.ELEVATOR_SUBSYSTEM.getIsAtExtensionLimit() == false) {
    		Robot.ELEVATOR_SUBSYSTEM.holdPosition();
    	}
    	else {
    		Robot.ELEVATOR_SUBSYSTEM.stop();
    	}
    }

	protected boolean isFinished() {
		// This command is never finished; once it is called, the only way it stops is
		// if another command overrides it.
		return false;
	}

}
