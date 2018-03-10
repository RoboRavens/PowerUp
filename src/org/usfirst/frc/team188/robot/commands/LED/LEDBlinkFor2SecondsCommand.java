package org.usfirst.frc.team188.robot.commands.LED;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LEDBlinkFor2SecondsCommand extends Command {
	Timer _timer = new Timer();

    public LEDBlinkFor2SecondsCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis); 
    	requires(Robot.LED_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.LED_SUBSYSTEM.blink(7, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false; 	
    	if (_timer.get() > 2) {
    		isFinished = true;
    	} 

        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	_timer.stop();
    	_timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
