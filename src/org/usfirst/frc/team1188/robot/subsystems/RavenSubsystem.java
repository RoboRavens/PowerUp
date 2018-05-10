package org.usfirst.frc.team1188.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RavenSubsystem  extends Subsystem {
	ElevatorSubsystem2 _implimentation;
	
	public RavenSubsystem(ElevatorSubsystem2 implimentation) {
		_implimentation = implimentation;
	}

	@Override
	protected void initDefaultCommand() {
		Command cmd = _implimentation.getDefaultCommand();
		if (cmd != null) {
			setDefaultCommand(cmd);
		}
	}
	
	@Override
	public void periodic() {
		_implimentation.periodic();
	}

}
