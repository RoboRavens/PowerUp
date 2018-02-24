package org.usfirst.frc.team1188.robot.commands.arm;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmExtendCommand extends Command {
	
	private Timer _safetyTimer = new Timer();
	
    public ArmExtendCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ARM_SUBSYSTEM);
    	_safetyTimer.start();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ARM_SUBSYSTEM.extend();
    	// System.out.println("EXTENDING ARM.EXTENDING ARM.EXTENDING ARM.EXTENDING ARM.EXTENDING ARM.EXTENDING ARM.EXTENDING ARM.EXTENDING ARM.");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _safetyTimer.get() > .1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ARM_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
