package org.usfirst.frc.team1188.robot;

public final class Calibrations {
	// Drive calibration
	// Slew rate of .2 seems to work well for when the lift is lowered, though more testing
	// is necessary - might turn it up or down slightly for increased performance.
	// public static final double slewRate = .2;
	public static final double slewRateMinimum = .3;
	public static final double slewRateMaximum = .35;
	
	// The safe slew rate changes based upon a few variables:
	// 		- What gear we are in
	//		- How high the lift is
	//		- What direction the robot is moving (forward or backward.)
	//			(backwards to forwards seems worse - but that's with no arm or cube, and a broken chassis)
	//		- A number low enough to be safe for all scenarios will negatively impact normal operation.
	
	public static final double cutPowerModeMovementRatio = .3;
	public static final double cutPowerModeTurnRatio = .5;
	//public static final double gyroAdjustmentScaleFactor = .011; // Could possibly cause gyro issues
	public static final double gyroAdjustmentDefaultScaleFactor = .03;
	public static final double driveTrainTurnRelativeDegreesGyroAdjustmentScaleFactor = .009;
	public static final double gyroCooldownTimerTime = .5;
	public static final double translationMaxTurnScaling = .5;
	public static final double gyroAutoTurnAcceptableErrorDegrees = 1;
	public static final boolean driveTrainStartingIsInHighGear = false;
	
	// Drive collision
	public static final double DriveTrainCollisionJerkThreshold = 4;
	
	// Drive and gyro modes
	public static final int bulldozerTank = 0;
	public static final int fpsTank = 1;
	
	public static final int gyroDisabled = 0;
	public static final int gyroEnabled = 1;
	
	// Any turn taking too long to complete (e.g. wheel scrub has halted the turn) will abandon after this number of seconds.
	public static final double DriveTrainTurnRelativeDegreesSafetyTimerSeconds = 1;
	
	// Deadband
	public static final double deadbandMagnitude = .1;
	
	// Default drive and gyro modes
	public static final int defaultDriveMode = Calibrations.fpsTank;
	public static final int defaultGyroMode = Calibrations.gyroEnabled;
	
	//public static final int defaultGyroMode = Calibrations.gyroDisabled;
	
	
	// Drive encoders
	// The *3 is for low gear. In high gear, it would just be 4096. Run all autonomous modes in low gear.
	public static final int encoderCUI103CyclesPerRevolution = 4096 * 3;
	// public static final int encoderE4TCyclesPerRevolution = 360;
	// public static final int encoderE4PCyclesPerRevolution = 250;
	public static final double driveWheelDiameterInches = 6;
	public static final double driveWheelCircumferenceInches = Calibrations.driveWheelDiameterInches * Math.PI;
	//public static final double driveEncoderE4TCyclesPerInch = (double) Calibrations.encoderE4TCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	//public static final double driveEncoderE4PCyclesPerInch = (double) Calibrations.encoderE4PCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	
	// We're using CUI 103 encoders on both sides of the drivetrain.
	public static final int leftEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	public static final int rightEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	
	// Direction magic numbers
	public static final int drivingForward = -1;
	public static final int drivingBackward = 1;
	
	// Adjust max power based on elevator height
	public static final double DRIVETRAIN_MAXPOWER_AT_MAX_ELEVEATOR_HEIGHT = .4;
	
	// Elevator Lift
	public static final double elevatorExtensionPowerMagnitude = 1;
	public static final double elevatorRetractionPowerMagnitude = 1; // when no 3rd stage
	// public static final double elevatorRetractionPowerMagnitude = .1;
	public static final double elevatorMaximumSpeed = 1.0;
	//public static final double elevatorHoldPositionPowerMagnitude = .15;
	public static final double elevatorHoldPositionPowerMagnitude = .1;
	public static final int elevatorLiftEncoderMinimumValue = 0;
	public static final int elevatorLiftEncoderMaximumValue = 30000;
	
	public static final double elevatorConsideredMovingEncoderRate = 0;
	
	
	public static final double elevatorMinimumScaleHeightInches = 51;
	public static final double elevatorBalancedScaleHeightInches = 67;
	public static final double elevatorMaximumScaleHeightInches = 77;
	
	// 
	public static final double elevatorCubePickupMaximumHeight = 15;
	
	public static final int elevatorInchesToEncoderTicksConversionValue = 411;
	public static final int elevatorInchesToEncoderTicksOffsetValue = 10;

	
	// The safety margin is how far away from the end of travel the encoders will stop the lift.
	// At low speeds (max of .3), and a lift max value of 30k, 1500 maxes out the elevator.
	// At higher speeds, a higher value is needed because the elevator will overshoot the target until we have PID.
	//public static final int elevatorLiftUpwardSafetyMargin = 2500;
	public static final int elevatorLiftUpwardSafetyMargin = 1000;
	
	//public static final int elevatorLiftDownwardSafetyMargin = 1500;
	public static final int elevatorLiftDownwardSafetyMargin = 1000;
	
	public static final double ELEVATOR_SAFETY_TIMER_TIMEOUT = 3.5;
	
	
	
	//Intake Wheel
	public static final double intakeWheelPullPowerMagnitude = 1;
	public static final double intakeWheelPushSoftPowerMagnitude = 1;
	public static final double intakeWheelPushHardPowerMagnitude = 1;

	
	
	public static final double AXIS_IS_PRESSED_VALUE = .25;

	public static final double IntakeSpitTimer = 1;
	public static final double IntakeSpitPowerMagnitude = 1;

	
	
	
	// Lighting
	public static double lightingFlashTotalDurationMs = 1000;
	public static double lightingFlashes = 10;
	
	
	public static double armExtensionPowerMagnitude = 1;
	public static double armRetractionPowerMagnitude = 1;
	public static double armMaximumSpeed = 1;
	public static int armEncoderValueExtended = -2048;
	public static int armEncoderValueRetracted = 0;
	public static int ARM_ENCODER_BUFFER = 300;
}
