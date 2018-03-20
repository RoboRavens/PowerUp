package org.usfirst.frc.team1188.robot.commands.misc;

import org.usfirst.frc.team1188.util.OverrideSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetOverride2Command extends Command {
	private OverrideSystem _overrideSystem;
	private boolean _value;
	
    public SetOverride2Command(OverrideSystem overrideSystem, boolean value) {
    	_overrideSystem = overrideSystem;
    	_value = value;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	_overrideSystem.setOverride1(_value);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
