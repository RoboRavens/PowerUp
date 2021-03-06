package org.usfirst.frc.team1188.robot.commands.arm;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmExtendFullyCommand extends Command {
	
    public ArmExtendFullyCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ARM_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("ArmExtendFullyCommandArmExtendFullyCommandArmExtendFullyCommandArmExtendFullyCommandArmExtendFullyCommandArmExtendFullyCommandArmExtendFullyCommandArmExtendFullyCommand");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ARM_SUBSYSTEM.extend();
    	//System.out.println("EXTENDING ARM FULLY");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	if (Robot.ARM_SUBSYSTEM.getIsAtExtensionLimit()) {
    		Robot.ARM_SUBSYSTEM.stop();
    		isFinished = true;
    	} 
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ARM_SUBSYSTEM.expectArmToBeAtExtensionLimit();
    	Robot.ARM_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
