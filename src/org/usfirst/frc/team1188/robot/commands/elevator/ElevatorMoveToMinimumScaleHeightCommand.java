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
public class ElevatorMoveToMinimumScaleHeightCommand extends Command {
	ElevatorSubsystem elevator;
	Robot robot;
    Gamepad operationController;
    Encoder encoder;
	double heightLimitEncoderTicks = ElevatorSubsystem.inchesToTicks(Calibrations.elevatorMinimumScaleHeightInches);
    double speed;
    int tolerance = 5;
    int deceleration = 1500;
    
    public ElevatorMoveToMinimumScaleHeightCommand(ElevatorSubsystem elevator, Gamepad operationController, Encoder encoder) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    	this.elevator = elevator;
    	this.robot = robot;
    	this.operationController = operationController;
    	this.encoder = encoder;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distToTarget = heightLimitEncoderTicks - elevator.getElevatorPosition();
    	int direction = (int) Math.signum(distToTarget);
    	//speed = distToTarget / deceleration;
    	// Turn speed into portions of Calibrations.elevatorMaximumSpeed
    	// speed = distToTarget / 8192;
    	
    	speed = .5;
    	
    	if (direction > 0) {
    		elevator.extend(speed);
    	} 
    	if (direction < 0) {
    		elevator.retract(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	
    	System.out.println("GetPos Count: " + elevator.getLeftEncoderPosition() + " Encoder.get: " + encoder.get() + " HLI: " + this.heightLimitEncoderTicks);
    	
    	if (elevator.getElevatorPosition() >= this.heightLimitEncoderTicks) {
    		elevator.stop();
    		isFinished = true;		
    	} else {
    		isFinished = false;
    	}
    
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
