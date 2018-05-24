package org.usfirst.frc.team1188.wpiwrappers;

import edu.wpi.first.wpilibj.Timer;

public class RTimer implements ITimer {
	private Timer _timer = new Timer();
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1188.wpiwrappers.ITimer#start()
	 */
	@Override
	public void start() {
		_timer.start();
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1188.wpiwrappers.ITimer#reset()
	 */
	@Override
	public void reset() {
		_timer.reset();
	}
	
	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1188.wpiwrappers.ITimer#get()
	 */
	@Override
	public double get() {
		return _timer.get();
	}
}
