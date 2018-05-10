package org.usfirst.frc.team1188.wpiwrappers;

import edu.wpi.first.wpilibj.Encoder;

public class REncoder implements IEncoder {
	Encoder _encoder;
	
	  /**
	   * Encoder constructor. Construct a Encoder given a and b channels.
	   *
	   * <p>The encoder will start counting immediately.
	   *
	   * @param channelA The a channel digital input channel.
	   * @param channelB The b channel digital input channel.
	   */
	public REncoder(final int channelA, final int channelB) {
		_encoder = new Encoder(channelA, channelB);
	}
	
	@Override
	public double getRate() {
		return _encoder.getRate();
	}

}
