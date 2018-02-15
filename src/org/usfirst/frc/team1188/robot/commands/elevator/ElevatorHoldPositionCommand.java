package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHoldPositionCommand extends Command {
	ElevatorSubsystem elevator;
	Robot robot;
    Gamepad operationController;
    Encoder encoder;
    int targetPosition;
    
    public ElevatorHoldPositionCommand(ElevatorSubsystem elevator, Gamepad driveController, Encoder encoder) {
    	requires(elevator);
    	this.elevator = elevator;
    	this.operationController = driveController;
    	this.encoder = encoder;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	// Set the target position to be whatever the elevator's position is when the command begins.
    	this.targetPosition = elevator.getElevatorPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Holding elevator at " + Calibrations.elevatorHoldPositionPowerMagnitude + " power.");
    	// The goal of this command is to send a very small amount of power to the elevator motors
    	// to fight against gravity - NOT to move the elevator, at all. 
    	if (elevator.getIsAtExtensionLimit() == false) {
        	elevator.holdPosition();
    	}
    	else {
    		elevator.stop();
    	}
    }

	protected boolean isFinished() {
		// This command is never finished; once it is called, the only way it stops is
		// if another command overrides it.
		return false;
	}

}
