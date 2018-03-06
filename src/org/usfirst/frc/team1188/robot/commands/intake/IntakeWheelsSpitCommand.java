package org.usfirst.frc.team1188.robot.commands.intake;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeWheelsSpitCommand extends Command{
	double magnitude;
	Timer durationTimer;

	public IntakeWheelsSpitCommand(double magnitude) {
		requires(Robot.INTAKE_WHEEL_SUBSYSTEM);
		// TODO Auto-generated constructor stub
		this.magnitude = magnitude;
		this.durationTimer = new Timer();
		this.durationTimer.start();
	}
	// Called just before this Command runs the first time
    protected void initialize() {
    	this.durationTimer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Robot.INTAKE_WHEEL_SUBSYSTEM.push(magnitude);
    	System.out.println("IntakeWheelsSpitCommand execute@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    	Robot.INTAKE_WHEEL_SUBSYSTEM.pull();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	if (durationTimer.get() > Calibrations.IntakeSpitTimer) {
    		isFinished = true;
    	}
    	
        return isFinished;
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

