package org.usfirst.frc.team1188.robot.commands.arm;

import org.usfirst.frc.team1188.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmStopCommand extends Command {
	ArmSubsystem arm;
	
    public ArmStopCommand(ArmSubsystem arm) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(arm);
    	this.arm = arm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	arm.stop();
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