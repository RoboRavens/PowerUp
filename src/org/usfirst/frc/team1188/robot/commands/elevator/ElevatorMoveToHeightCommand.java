package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorMoveToHeightCommand extends Command {
	ElevatorSubsystem elevator;
	Robot robot;
    Gamepad operationController;
    Encoder encoder;
    int heightLimit;
    double speed;
    int tolerance = 5;
    int deceleration = 1500;
    
    public ElevatorMoveToHeightCommand(ElevatorSubsystem elevator, Gamepad operationController, Encoder encoder, int heightLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    	this.elevator = elevator;
    	this.robot = robot;
    	this.operationController = operationController;
    	this.encoder = encoder;
    	this.heightLimit = heightLimit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int distToTarget = heightLimit - encoder.get();
    	int direction = (int) Math.signum(distToTarget);
    	//speed = distToTarget / deceleration;
    	// Turn speed into portions of Calibrations.elevatorMaximumSpeed
    	double keyValue = heightLimit / Calibrations.elevatorMaximumSpeed;
    	speed = distToTarget / keyValue;
    	
    	if (direction > 0) {
    		elevator.extend(speed);
    	} 
    	if (direction < 0) {
    		elevator.retract(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
