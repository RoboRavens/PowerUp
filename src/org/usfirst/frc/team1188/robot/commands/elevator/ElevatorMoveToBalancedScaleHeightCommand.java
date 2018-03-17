package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorMoveToBalancedScaleHeightCommand extends Command {
	double heightLimitEncoderTicks = ElevatorSubsystem.inchesToTicks(Calibrations.elevatorBalancedScaleHeightInches);
	double speed = Calibrations.elevatorExtensionPowerMagnitude;
    int tolerance = 5;
    int deceleration = 1500;
    
    public ElevatorMoveToBalancedScaleHeightCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ELEVATOR_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ELEVATOR_SUBSYSTEM.resetSafetyTimer();
    	Robot.ELEVATOR_SUBSYSTEM.startSafetyTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distToTarget = heightLimitEncoderTicks - Robot.ELEVATOR_SUBSYSTEM.getElevatorPosition();
    	int direction = (int) Math.signum(distToTarget);
    	//speed = distToTarget / deceleration;
    	// Turn speed into portions of Calibrations.elevatorMaximumSpeed
    	// speed = distToTarget / 8192;
    	    	
    	if (direction > 0) {
    		Robot.ELEVATOR_SUBSYSTEM.extend(speed);
    	} 
    	if (direction < 0) {
    		Robot.ELEVATOR_SUBSYSTEM.retract(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	if (Robot.ELEVATOR_SUBSYSTEM.getSafetyTimer() > Calibrations.ELEVATOR_SAFETY_TIMER_TIMEOUT) {
    		isFinished = true;
    	}
    	
    	//System.out.println("GetPos Count: " + Robot.ELEVATOR_SUBSYSTEM.getEncoderPosition() + " Encoder.get: " + Robot.ELEVATOR_SUBSYSTEM.getEncoderPosition() + " HLI: " + this.heightLimitEncoderTicks);
    	
    	if (Robot.ELEVATOR_SUBSYSTEM.getElevatorPosition() >= this.heightLimitEncoderTicks) {
    		Robot.ELEVATOR_SUBSYSTEM.stop();
    		isFinished = true;		
    	} else {
    		isFinished = false;
    	}
    
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.ELEVATOR_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
