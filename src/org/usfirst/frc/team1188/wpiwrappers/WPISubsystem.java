package org.usfirst.frc.team1188.wpiwrappers;

import org.usfirst.frc.team1188.robot.subsystems.RavenSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WPISubsystem extends Subsystem {
	public final RavenSubsystem RavenSubsystem;
	
	public WPISubsystem(RavenSubsystem implimentation) {
		RavenSubsystem = implimentation;
	}

	@Override
	protected void initDefaultCommand() {
		Command cmd = RavenSubsystem.getDefaultCommand();
		if (cmd != null) {
			setDefaultCommand(cmd);
		}
	}
	
	@Override
	public void periodic() {
		RavenSubsystem.periodic();
	}
}
