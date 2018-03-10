package org.usfirst.frc.team1188.ravenhardware;

import org.usfirst.frc.team1188.robot.Calibrations;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Timer;

public class RavenLighting {
	private Relay mainArray;
	Timer timer;
	boolean toggling;
	boolean onForSeconds = false;
	double secondsDuration = 0;
	
	public RavenLighting (Relay relay) {
		mainArray = relay;
		timer = new Timer();
		toggling = false;
	}
	
	public void turnOn() {
		cancelToggle();
		mainArray.set(Value.kOn);
		// System.out.println("TURNING ON");
	}
	
	public void turnOff() {
		cancelToggle();
		mainArray.set(Value.kOff);
		// System.out.println("TURNING OFF");
	}
	
	public void quickToggle() {
		// If not already toggling, initialize a toggle sequence.
		// If already toggling, no need to do anything.
		if (toggling == false) {
			timer.reset();
			timer.start();
			toggling = true;
		}
	}
	
	public void turnOnForSeconds(double seconds) {
		this.turnOn();
		timer.start();
		onForSeconds = true;
		secondsDuration = seconds;
	}
	
	public void maintainSecondsState() {
		if (onForSeconds == false) {
			return;
		}
		
		if (timer.get() < secondsDuration) {
			this.turnOn();
		}
		else {
			this.turnOff();
			onForSeconds = false;
		}
	}
	
	public void cancelToggle() {
		toggling = false;
		timer.stop();
		timer.reset();
	}
	
	public void maintainState() {
		maintainSecondsState();
		
		// If not toggling, this method does nothing.
		if (toggling == false) {
			return;
		}
		
		double timerMs = timer.get() * 1000;
		
		if (timerMs > Calibrations.lightingFlashTotalDurationMs) {
			cancelToggle();
			return;
		}
		
		Value lightsValue;
		
		// If we haven't returned yet, we're toggling.
		// The duration of each on/off cycle is the total flash duration,
		// divided by the number of flashes. An individual on/off is half of that.
		double cycleDuration = Calibrations.lightingFlashTotalDurationMs / Calibrations.lightingFlashes;
		
		// Modding the timer by the cycle duration gives us the time elapsed in the current cycle.
		double msElapsedInCurrentCycle = timerMs % cycleDuration;
		
		// The lights should be on for the first half of each cycle, and off for the second half.
		if (msElapsedInCurrentCycle * 2 < cycleDuration) {
			lightsValue = Value.kOn;
		}
		else {
			lightsValue = Value.kOff;
		}
		
		mainArray.set(lightsValue);
	}
}