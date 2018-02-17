package org.usfirst.frc.team1188.robot;

import org.usfirst.frc.team1188.gamepad.AxisCode;
import org.usfirst.frc.team1188.gamepad.ButtonCode;

public class ControlsMap {
	// Drive controller
	public static final ButtonCode driveShiftToLowGearButton = ButtonCode.LEFTBUMPER;
	public static final ButtonCode driveShiftToHighGearButton = ButtonCode.RIGHTBUMPER;
	public static final AxisCode driveCutPowerAxis = AxisCode.RIGHTTRIGGER;
	public static final AxisCode operatorOverrideAxis = AxisCode.LEFTTRIGGER;
	
	/*
	
	// Button pad
	// Operation controller
	public static final int fuelIntakeCollectButton = 6;
	public static final int fuelIntakeReverseButton = 7;
	public static final int fuelPumpPumpButton = 3;
	public static final int fuelPumpReverseButton = 4;
	public static final int shooterRevButton = 5;
	public static final int shooterShootButton = 5;
	// public static final int shooterStopButton = 4;
	// public static final int shooterOverrideShootButton = 5;
	public static final int gearIntakeExtendButton = 4;
	// public static final int gearIntakeRetractButton = 5;
	public static final int gearCarriageExtendButton = 2;
	public static final int gearCarriageRetractButton = 1;
	public static final int climberClimbButton = 3;
	*/
	
	// Xbox controller
	
	// Operation controller
	public static final int fuelIntakeCollectButton = 1;
	public static final int fuelIntakeReverseButton = 2;
	public static final int fuelPumpPumpButton = 3;
	public static final int fuelPumpReverseButton = 4;
	public static final int shooterRevButton = 9;
	public static final int shooterShootButton = 9;
	// public static final int shooterStopButton = 4;
	// public static final int shooterOverrideShootButton = 5;
	public static final int gearIntakeExtendButton = 6;
	// public static final int gearIntakeRetractButton = 5;
	public static final ButtonCode elevatorExtendButton = ButtonCode.START;
	public static final ButtonCode elevatorRetractButton = ButtonCode.BACK;
	public static final int climberClimbButton = 10;
	
	
	
	public static final ButtonCode operationShiftToLowGearButton = ButtonCode.LEFTBUMPER;
	public static final int operationFlashlightAimAxis = 4;
	
	// Custom buttons
	
	public static final int fuelPumpOverrideShooterIsSpinningButton = 11;
	public static final int climberOverrideStallProtectionButton = 12;
	public static final int climberOverrideClimberDirectionButton = 13;
	public static final int gearCarriageOverrideExtensionLimitButton = 2;
	public static final int gearCarriageOverrideRetractionLimitButton = 1;
	public static final int shooterOverrideShootButton = 16;
	
	// buttonPanel
	public static final int redLight = 1;
	public static final int greenLight = 2;
	public static final int blueLight = 3;
	public static final int whiteLight = 4;
}
