package org.usfirst.frc.team1188.util;

import org.usfirst.frc.team1188.gamepad.AxisCode;
import org.usfirst.frc.team1188.gamepad.Gamepad;

public class OverrideSystem {
	private boolean _override1;
	private boolean _override2;
	
	public void setOverride1(boolean val) {
		_override1 = val;
	}
	
	public void setOverride2(boolean val) {
		_override2 = val;
	}
	
	public boolean getIsAtLimit(boolean encoderLimit, boolean switchLimit) {
		int limitCount = 0;
		
		// if limits are hit add to limit count
		limitCount += this.boolToInt(encoderLimit);
		limitCount += this.boolToInt(switchLimit);
		
		// if overrides are enabled subtract from limit count
		limitCount -= this.boolToInt(_override1);
		limitCount -= this.boolToInt(_override2);
		
		return limitCount >= 1;
	}
	
	private int boolToInt(boolean val) {
		return val ? 1 : 0;
	}
}
