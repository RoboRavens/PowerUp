package org.usfirst.frc.team1188.wpiwrappers;

public interface IEncoder {
  /**
   * Get the current rate of the encoder. Units are distance per second as scaled by the value from
   * setDistancePerPulse().
   *
   * @return The current rate of the encoder.
   */
	double getRate();
}
