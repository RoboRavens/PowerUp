package org.usfirst.frc.team1188.robot.commands.intake;


import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.IntakeWheelSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeWheelPullIdle extends Command{
	
	public IntakeWheelPullIdle(IntakeWheelSubsystem intakeWheelSubsystem) {
    	requires(Robot.IntakeWheelSubsystem);
    }


	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.IntakeWheelSubsystem.idle();
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
