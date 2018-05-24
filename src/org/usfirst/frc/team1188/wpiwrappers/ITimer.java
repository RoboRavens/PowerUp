package org.usfirst.frc.team1188.wpiwrappers;

public interface ITimer {
	
	/**
	 * Start the timer running. Just set the running flag to true indicating that all time requests
	 * should be relative to the system clock.
	 */
	void start();

	/**
	 * Reset the timer by setting the time to 0. Make the timer startTime the current time so new
	 * requests will be relative now.
	 */
	void reset();

	/**
	 * Get the current time from the timer. If the clock is running it is derived from the current
	 * system clock the start time stored in the timer class. If the clock is not running, then
	 * return the time when it was last stopped.
	 * @return Current time value for this timer in seconds.
	 */
	double get();

}