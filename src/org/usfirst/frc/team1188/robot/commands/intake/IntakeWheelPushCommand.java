package org.usfirst.frc.team1188.robot.commands.intake;

import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.IntakeWheelSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWheelPushCommand extends Command {
	
	IntakeWheelSubsystem intakeWheelSubsystem;

    public IntakeWheelPushCommand() {
        requires(Robot.IntakeWheelSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.IntakeWheelSubsystem.push();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intakeWheelSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
