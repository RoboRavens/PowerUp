package org.usfirst.frc.team1188.robot.commands.arm;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmExtendToHighScaleCommand  extends Command {
	
    public ArmExtendToHighScaleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ARM_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ARM_SUBSYSTEM.resetSafetyTimer();
    	Robot.ARM_SUBSYSTEM.startSafetyTimer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// We either need to extend or retract, based on where the arm currently is.
    	// Robot.ARM_SUBSYSTEM.extend();
    	
    	if (Robot.ARM_SUBSYSTEM.getIsExtendedPastHighScale()) {
    		Robot.ARM_SUBSYSTEM.retract();
    	}
    	else {
    		Robot.ARM_SUBSYSTEM.extend();
    	}
    	
    }

    // This command should return true when the arm encoder value is within a safety margin of the target.
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	if (Robot.ARM_SUBSYSTEM.getSafetyTimer() > Calibrations.ARM_SAFETY_TIMER_TIMEOUT) {
    		isFinished = true;
    	}
    	
    	if (Robot.ARM_SUBSYSTEM.getIsAtHighScale()) {
    		Robot.ARM_SUBSYSTEM.stop();
    		isFinished = true;
    	}
    	
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ARM_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
