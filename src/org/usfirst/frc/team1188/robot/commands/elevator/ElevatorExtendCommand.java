package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorExtendCommand extends Command {
	
	private Timer _safetyTimer = new Timer();

    public ElevatorExtendCommand() {
    	requires(Robot.ELEVATOR_SUBSYSTEM);
    	_safetyTimer.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ElevatorExtendCommand init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("extending extending extending");
    	if (Robot.ELEVATOR_SUBSYSTEM.getIsAtExtensionLimit() == false) {
    		Robot.ELEVATOR_SUBSYSTEM.extend();
    	}
    	else {
    		Robot.ELEVATOR_SUBSYSTEM.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	if (_safetyTimer.get() > Calibrations.ELEVATOR_SAFETY_TIMER_TIMEOUT) {
    		isFinished = true;
    	}
    	
    	if (Robot.ELEVATOR_SUBSYSTEM.getIsAtExtensionLimit()) {
    		isFinished = true;
    	}
    	
    	return isFinished;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.ELEVATOR_SUBSYSTEM.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
