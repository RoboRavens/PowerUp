package org.usfirst.frc.team1188.robot.commands.intake;

import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.IntakeWheelSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWheelPullCommand extends Command {
	
	IntakeWheelSubsystem intakeWheelSubsystem;

    public IntakeWheelPullCommand (IntakeWheelSubsystem intakeWheelSubsystem) {
         requires(intakeWheelSubsystem); 
         this.intakeWheelSubsystem = intakeWheelSubsystem;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intakeWheelSubsystem.pull();
    	System.out.println("IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();");
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
