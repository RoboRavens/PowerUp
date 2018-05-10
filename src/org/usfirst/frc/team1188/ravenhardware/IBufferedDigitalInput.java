package org.usfirst.frc.team1188.ravenhardware;

public interface IBufferedDigitalInput {
	void maintainState();
	boolean get();
}
