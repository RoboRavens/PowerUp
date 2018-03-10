package org.usfirst.frc.team1188.robot.commands.arm;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmJoystickControlCommand extends Command {
	private double _power;
	
    public ArmJoystickControlCommand() {
    	requires(Robot.ARM_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(_power >= 0 && Robot.ARM_SUBSYSTEM.getIsAtExtensionLimit() == false) {
    		Robot.ARM_SUBSYSTEM.extend(_power);
    	}
    	
    	if(_power <= 0 && Robot.ARM_SUBSYSTEM.getIsAtRetractionLimit() == false){
    		Robot.ARM_SUBSYSTEM.retract(Math.abs(_power));
    	}
    }
    
    public void setPowerValue(double power) {
    	_power = power;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean isFinished = false;
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
