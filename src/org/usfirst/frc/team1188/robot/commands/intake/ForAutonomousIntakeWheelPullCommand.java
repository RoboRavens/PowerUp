package org.usfirst.frc.team1188.robot.commands.intake;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ForAutonomousIntakeWheelPullCommand extends Command{
	double magnitude;

	public ForAutonomousIntakeWheelPullCommand(double magnitude) {
		// TODO Auto-generated constructor stub
		this.magnitude = magnitude;
	}
	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.INTAKE_WHEEL_SUBSYSTEM.pull(magnitude);
    	//System.out.println("IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();IntakeWheelSubsystem.push();");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Robot.INTAKE_WHEEL_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}


