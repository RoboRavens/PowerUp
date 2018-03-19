package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorExtendCommand extends Command {

    public ElevatorExtendCommand() {
    	requires(Robot.ELEVATOR_SUBSYSTEM);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("ElevatorExtendCommand init");
    	Robot.ELEVATOR_SUBSYSTEM.resetSafetyTimer();
    	Robot.ELEVATOR_SUBSYSTEM.startSafetyTimer();
    	Robot.ELEVATOR_SUBSYSTEM.setTargetEncoderPosition(Calibrations.elevatorLiftEncoderMaximumValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.ELEVATOR_SUBSYSTEM.getIsAtExtensionLimit() == false) {
    		//System.out.println("extending extending extending");
    		Robot.ELEVATOR_SUBSYSTEM.extend();
    	}
    	else {
    		//System.out.println("stopping stopping stopping");
    		Robot.ELEVATOR_SUBSYSTEM.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
    	
    	if (Robot.ELEVATOR_SUBSYSTEM.getSafetyTimer() > Calibrations.ELEVATOR_SAFETY_TIMER_TIMEOUT) {
    		isFinished = true;
    	}
    	
    	if (Robot.ELEVATOR_SUBSYSTEM.getIsAtExtensionLimit()) {
    		isFinished = true;
    	}
    	return isFinished;
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	Robot.ELEVATOR_SUBSYSTEM.expectElevatorToBeAtTop();
    	Robot.ELEVATOR_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
