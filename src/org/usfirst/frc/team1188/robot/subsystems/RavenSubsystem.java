package org.usfirst.frc.team1188.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;

public abstract class RavenSubsystem {
	private Command _defaultCommand = null;
	
	public final Command getDefaultCommand() {
		return _defaultCommand;
	}
	
	public void setDefaultCommand(Command defaultCommand) {
		_defaultCommand = defaultCommand;
	}
	
	public abstract void periodic();

}
