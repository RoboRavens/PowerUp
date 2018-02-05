package org.usfirst.frc.team1188.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public final class Calibrations {
	// Drive calibration
	public static final double slewRate = 1; // Previously: .35.
	public static final double cutPowerModeMovementRatio = .3;
	public static final double cutPowerModeTurnRatio = .5;
	public static final double gyroAdjustmentScaleFactor = .03;
	public static final double gyroCooldownTimerTime = .5;
	public static final double translationMaxTurnScaling = .5;
	public static final double gyroAutoTurnAcceptableErrorDegrees = 4;
	public static final boolean DriveTrainStartingIsInHighGear = false;
	
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
	public static final double driveWheelDiameterInches = 4;
	public static final double driveWheelCircumferenceInches = Calibrations.driveWheelDiameterInches * Math.PI;
	//public static final double driveEncoderE4TCyclesPerInch = (double) Calibrations.encoderE4TCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	//public static final double driveEncoderE4PCyclesPerInch = (double) Calibrations.encoderE4PCyclesPerRevolution / Calibrations.driveWheelCircumferenceInches;
	
	// We're using CUI 103 encoders on both sides of the drivetrain.
	public static final int leftEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	public static final int rightEncoderCyclesPerRevolution = Calibrations.encoderCUI103CyclesPerRevolution;
	
	// Direction magic numbers
	public static final int drivingForward = -1;
	public static final int drivingBackward = 1;
	
	// Elevator Lift
	public static final double elevatorExtensionPowerMagnitude = 0.5;
	public static final double elevatorRetractionPowerMagnitude = 0.3;
	
	//Intake Wheel
	public static double intakeWheelPullPowerMagnitude = 0.4;
	public static final double intakeWheelPushPowerMagnitude = 0.5;
	
	// Lighting
	public static double lightingFlashTotalDurationMs = 1000;
	public static double lightingFlashes = 10;
	
	

}
