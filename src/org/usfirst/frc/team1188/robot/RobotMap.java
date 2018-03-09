package org.usfirst.frc.team1188.robot;

public class RobotMap {
	// Drive motors
	public static int leftDriveChannel = 0;
	public static int rightDriveChannel = 1;
	
	public static int armExtensionLimitSwitch = 8;
	public static int armRetractionLimitSwitch = 9;

	// Drive encoders
	public static final int leftDriveEncoder1 = 0;
	public static final int leftDriveEncoder2 = 1;
	public static final int rightDriveEncoder1 = 2;
	public static final int rightDriveEncoder2 = 3;
	
	public static final int shiftToLowGearSolenoid = 0;
	public static final int shiftToHighGearSolenoid = 1;
	
	//Relays 
	public static final int hasCubeLEDLightRelay = 0;
	public static final int underglowLightRelay = 1;
	
	// Elevator System
	public static final int elevatorEncoder1 = 20;
	public static final int elevatorEncoder2 = 21;
	
	public static final int elevatorMotorLeft = 3;
	public static final int elevatorMotorRight = 2;
	
	public static final int topLimitSwitch = 7;
	public static final int bottomLimitSwitch = 6;
	
	
	// Intake System 
	public static final int intakeMotorLeft = 0;
	public static final int intakeMotorRight = 1;
	public static final int leftIntakeClampSolenoid = 2;
	public static final int rightIntakeClampSolenoid = 3;

	public static final int intakeSensor = 5;
	
	// Arm System
	public static final int armMotorLeft = 5;
	public static final int armMotorRight = 4;
	
	public static final int carriageStalledLightRelay = 1;
	
	public static final int armHoldBackSolenoid = 6;
	
}
