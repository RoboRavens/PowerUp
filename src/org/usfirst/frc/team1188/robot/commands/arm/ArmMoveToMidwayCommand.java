package org.usfirst.frc.team1188.robot.commands.arm;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmMoveToMidwayCommand extends Command {
	
    public ArmMoveToMidwayCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ARM_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// We either need to extend or retract, based on where the arm currently is.
    	// Robot.ARM_SUBSYSTEM.extend();
    	
    	if (Robot.ARM_SUBSYSTEM.getIsExtendedPastMidway()) {
    		Robot.ARM_SUBSYSTEM.retract();
    	}
    	else {
    		Robot.ARM_SUBSYSTEM.extend();
    	}
    	
    }

    // This command should return true when the arm encoder value is within a safety margin of the target.
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	if (Robot.ARM_SUBSYSTEM.getIsAtMidway()) {
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
