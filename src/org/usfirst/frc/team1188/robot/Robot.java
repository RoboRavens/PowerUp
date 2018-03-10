/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1188.robot;


import org.usfirst.frc.team1188.gamepad.ButtonCode;
import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.ravenhardware.RavenLighting;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousCrossAutoLineCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousDoNothingCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousDriveStraightScoreInSwitchCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftScaleLeftPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftScaleRightPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftSwitchMiddlePositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftSwitchRightPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousRightScaleLeftPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousRightScaleRightPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousRightSwitchMiddlePositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousScoreRightSwitchLeftPositionCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToBalancedScaleHeightCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToMinimumScaleHeightCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPullCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPushCommand;
import org.usfirst.frc.team1188.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.IntakeClampSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.IntakeWheelSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.LEDSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.LightSubsystem;
import org.usfirst.frc.team1188.util.LoggerOverlord;
import org.usfirst.frc.team1188.util.OverrideSystem;
import org.usfirst.frc.team188.robot.commands.LED.LEDBlinkFor2SecondsCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public DriverStation driverStation;
	public PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	Diagnostics diagnostics = new Diagnostics();
	public static final LoggerOverlord LOGGER_OVERLORD = new LoggerOverlord(1f);
	
	public static final Gamepad DRIVE_CONTROLLER = new Gamepad(0);
	public static final Gamepad OPERATION_CONTROLLER = new Gamepad(1);
	// public static final Gamepad buttonPanel = new Gamepad(2);	
			
	public static final DriveTrainSubsystem DRIVE_TRAIN_SUBSYSTEM = new DriveTrainSubsystem();
	public static final ElevatorSubsystem ELEVATOR_SUBSYSTEM = new ElevatorSubsystem();
	public static final ArmSubsystem ARM_SUBSYSTEM = new ArmSubsystem();
	public static final IntakeClampSubsystem INTAKE_CLAMP_SUBSYSTEM = new IntakeClampSubsystem();
	public static final IntakeWheelSubsystem INTAKE_WHEEL_SUBSYSTEM = new IntakeWheelSubsystem();
	public static final LightSubsystem LIGHT_SUBSYSTEM = new LightSubsystem();
	public static final LEDSubsystem LED_SUBSYSTEM = new LEDSubsystem();
	
	public static final Relay HAS_CUBE_LEDS_RELAY = new Relay(RobotMap.hasCubeLEDLightRelay);
	public static final Relay UNDERGLOW_RELAY = new Relay(RobotMap.underglowLightRelay);
	public static final RavenLighting HAS_CUBE_LEDS = new RavenLighting(HAS_CUBE_LEDS_RELAY);
	public static final RavenLighting UNDERGLOW = new RavenLighting(UNDERGLOW_RELAY);
	
	// public static final ArmJoystickControlCommand ARM_JOYSTICK_CONTROL_COMMAND = new ArmJoystickControlCommand();
	
	public static final OverrideSystem OVERRIDE_SYSTEM = new OverrideSystem();
	
	public static final Solenoid ARM_HOLD_BACK = new Solenoid(RobotMap.armHoldBackSolenoid);


	Command autonomousCommand;
	
	public boolean isRedAlliance;	

	String autoFromDashboard;
	String positionFromDashboard;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		driverStation = DriverStation.getInstance();
		//NetworkTable table = NetworkTable.getTable("limelight");
		//table.putDouble("ledMode", 1);
		// System.out.println("disable limelight");
		
		// m_chooser.addDefault("Default Auto", new Command());
		// m_chooser.addObject(name, object);
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		driverStation.getMatchTime();
		// Zero the elevator encoders; the robot should always start with the elevator down.
		// Note that this may not be true in practice, so we should later integrate the reset with limit switch code.
		Robot.ELEVATOR_SUBSYSTEM.resetEncodersToBottom();
		Robot.ARM_SUBSYSTEM.resetEncodersToTop();
		

		// this.elevator.getPosition();
		// this.elevator.getIsAtLimits();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.LED_SUBSYSTEM.setDisabledPattern();

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		autoFromDashboard = SmartDashboard.getString("DB/String 0", "myDefaultData");
		outputAutoModeToDashboardStringOne(autoFromDashboard);
		
		
		// autoFromDashboard = SmartDashboard.getString("DB/String 0", "myDefaultData");
		positionFromDashboard = SmartDashboard.getString("DB/String 2", "myDefaultData");
		
		outputPositionToDashboardStringThree(positionFromDashboard);
		// outputAutoModeToDashboardStringOne(autoFromDashboard);
		
		// System.out.println("Deployed");

		// DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetOrientationGyro();
		
		Alliance alliance = driverStation.getAlliance();
		
		String allianceString = "";
		
		if (alliance.compareTo(Alliance.Blue) == 0) {
			allianceString = "Blue alliance";
			this.isRedAlliance = false;
		}
		else if (alliance.compareTo(Alliance.Red) == 0) {
			allianceString = "Red alliance";
			this.isRedAlliance = true;
		}
		else {
			allianceString = "Alliance not identified.";
			this.isRedAlliance = false;
		}
	
		SmartDashboard.putString("DB/String 4", allianceString);
		

		Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeadingToCurrentHeading();
		
		diagnostics.outputDisabledDiagnostics();

		// this.elevator.getPosition();
		// this.elevator.getIsAtLimits();
		//this.arm.getPosition();
		
		if (DRIVE_CONTROLLER.getButtonValue(ControlsMap.driveShiftToHighGearButton)) {
			Robot.ELEVATOR_SUBSYSTEM.resetEncodersToBottom();
		}
	}

	public void outputAutoModeToDashboardStringOne(String autoMode) {
		String autonomousModeConfirmation = "Confirmed - auto mode: ";
		String autonomousModeName = "";
	
		switch (autoFromDashboard.toUpperCase()) {
			case AutonomousCalibrations.DoNothing:
				autonomousModeName += "Do nothing.";
			break;
			case AutonomousCalibrations.CrossLine:
				autonomousModeName += "Cross auto line.";
				break;
			case AutonomousCalibrations.Switch:
				autonomousModeName += "Score in switch.";
				break;
			case AutonomousCalibrations.Scale:
				autonomousModeName += "Score on scale.";
				break;
				
			default:
				autonomousModeConfirmation = "ERROR!";
				autonomousModeName = "Mode not recognized.";
				break;	
		}
		
		putSmartDashboardAutonomousMode(autonomousModeConfirmation, autonomousModeName);
	}
	
	public void putSmartDashboardAutonomousMode(String autonomousModeConfirmation, String autonomousModeName) {
		SmartDashboard.putString("DB/String 1", autonomousModeConfirmation);
		SmartDashboard.putString("DB/String 6", autonomousModeName);
	}
	
	
	public void outputPositionToDashboardStringThree(String position) {
		String positionConfirmation = "Confirmed - position: ";
		String startingPosition = "";
	
		switch (position.toUpperCase()) {
			case "LEFT":
				startingPosition += "Left.";
				break;
			case "MIDDLE":
				startingPosition += "Middle.";
				break;
			case "RIGHT":
				startingPosition += "Right.";
				break;
			default:
				positionConfirmation = "ERROR!";
				startingPosition = "Position not recognized.";
				break;	
		}
		
		putSmartDashboardStartingPosition(positionConfirmation, startingPosition);
	}
	
	public void putSmartDashboardStartingPosition(String positionConfirmation, String startingPosition) {
		SmartDashboard.putString("DB/String 3", positionConfirmation);
		SmartDashboard.putString("DB/String 8", startingPosition);
	}
	
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Robot.ARM_SUBSYSTEM.resetEncodersToTop();
		Robot.LED_SUBSYSTEM.setAutonomousPattern();
		
		m_autonomousCommand = m_chooser.getSelected();
		// Zero the gyro, grab the selected autonomous mode, and get to work.
		Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeadingToCurrentHeading();
		autonomousCommand = getAutonomousCommand();
		
		// DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetOrientationGyro();
		DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeadingToCurrentHeading();
		autonomousCommand = new DriveTrainDriveInchesCommand(120, .75, Calibrations.drivingForward);
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	
	public Command getAutonomousCommand() {
		// Three possibilities:
		// Drive forward and score in switch,
		// move left to score in switch,
		// and move right to score in switch.
		
		
		autonomousCommand = new AutonomousCrossAutoLineCommand();
		

		switch (autoFromDashboard.toUpperCase()) {
			case AutonomousCalibrations.DoNothing:
				autonomousCommand = new AutonomousDoNothingCommand();
				break;
			case AutonomousCalibrations.CrossLine:
				autonomousCommand = new AutonomousCrossAutoLineCommand();
				break;
			case AutonomousCalibrations.Switch:
				autonomousCommand = this.getAutonomousSwitchCommand();
				break;
			case AutonomousCalibrations.Scale:
				autonomousCommand = this.getAutonomousScaleCommand();
				break;
					
		}
		
		
		
	
		return autonomousCommand;
	}
	
	private Command getAutonomousSwitchCommand() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		Command switchCommand = new AutonomousCrossAutoLineCommand();
		
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Left switch, left position. Drive forward and then score on the switch.
					switchCommand = new AutonomousDriveStraightScoreInSwitchCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Left switch, middle position. Drive diagonally and then score on the switch.
					switchCommand = new AutonomousLeftSwitchMiddlePositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Left switch, right position. UGUUU
					switchCommand = new AutonomousLeftSwitchRightPositionCommand();
				}
			}
			else {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Right switch, left position. Ugh.
					switchCommand = new AutonomousScoreRightSwitchLeftPositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Right switch, middle position. Drive diagonally and then score on the switch.
					switchCommand = new AutonomousRightSwitchMiddlePositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Right switch, right position. Easy.
					switchCommand = new AutonomousDriveStraightScoreInSwitchCommand();
				}
			}
		}
		
		return switchCommand;
	}
	
	private Command getAutonomousScaleCommand() {

		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		Command scaleCommand = new AutonomousCrossAutoLineCommand();
		
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Left switch, left position. Drive forward and then score on the switch.
					scaleCommand = new AutonomousLeftScaleLeftPositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Left switch, middle position. Drive diagonally and then score on the switch.
					// scaleCommand = new AutonomousScoreLeftSwitchMiddlePositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Left switch, right position. UGUUU
					scaleCommand = new AutonomousLeftScaleRightPositionCommand();
				}
			}
			else {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Right switch, left position. Ugh.
					scaleCommand = new AutonomousRightScaleLeftPositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Right switch, middle position. Drive diagonally and then score on the switch.
					// scaleCommand = new AutonomousScoreRightSwitchMiddlePositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Right switch, right position. Easy.
					scaleCommand = new AutonomousRightScaleRightPositionCommand();
				}
			}
		}
		
		return scaleCommand;
	}
	
	
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		diagnostics.outputAutonomousDiagnostics();
	}

	@Override
	public void teleopInit() {
		ARM_HOLD_BACK.set(true); // retract support solenoid
		DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeadingToCurrentHeading();
		DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetGyroAdjustmentScaleFactor();
		// DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetOrientationGyro();
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		Robot.LED_SUBSYSTEM.setEnabledPattern();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		runOperatorControls();
		
		//System.out.println("teleop setting on");
		//this.HAS_CUBE_LEDS.set(Value.kForward);
		
		 // this.elevator.getPosition();
		// this.elevator.getIsAtLimits();
		//Robot.ARM_SUBSYSTEM.getPosition();
		 
		 diagnostics.outputTeleopDiagnostics();
		 // System.out.println(PDP.getVoltage());
		 
		 if (driverStation.getMatchTime() == 90 || driverStation.getMatchTime() == 60 || driverStation.getMatchTime() == 30 || driverStation.getMatchTime() == 10) {
			LEDBlinkFor2SecondsCommand command = new LEDBlinkFor2SecondsCommand();
			command.start();
		 }
	}
	
	public boolean getMatchIsAtTime(int matchSecond) {
		boolean isFinished = false;
		double matchTime = driverStation.getMatchTime();
		if (matchTime > matchSecond - .5 && matchTime < matchSecond + .5) {
			isFinished = true;
		}
		
		return isFinished;
	}
	
	public void runOperatorControls() {
		// Drive Train
		 
		/*
		if (DRIVE_CONTROLLER.getButtonValue(ControlsMap.driveShiftToHighGearButton)) {
			DRIVE_TRAIN_SUBSYSTEM.ravenTank.shiftToLowGear();
		}
		
		if (DRIVE_CONTROLLER.getButtonValue(ControlsMap.driveShiftToLowGearButton)) {
			DRIVE_TRAIN_SUBSYSTEM.ravenTank.shiftToHighGear();
		}
		
		
	    if (DRIVE_TRAIN_SUBSYSTEM.ravenTank.userControlOfCutPower) {
	      if (DRIVE_CONTROLLER.getAxis(ControlsMap.driveCutPowerAxis) > .25) {
	        DRIVE_TRAIN_SUBSYSTEM.ravenTank.setCutPower(true);
	      }
	      else {
	        DRIVE_TRAIN_SUBSYSTEM.ravenTank.setCutPower(false);
	      }		
	    }
	    */
		
	    if (DRIVE_CONTROLLER.getButtonValue(ButtonCode.A)) {
	    	DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeading(180);
	    }
	    
	    if (DRIVE_CONTROLLER.getButtonValue(ButtonCode.B)) {
	    	DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeading(90);
	    }
	    
	    if (DRIVE_CONTROLLER.getButtonValue(ButtonCode.X)) {
	    	DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeading(270);
	    }
	    
	    if (DRIVE_CONTROLLER.getButtonValue(ButtonCode.Y)) {
	    	DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeading(0);
	    }
	   
	    
		//driveController.getButton(ButtonCode.A).whenPressed(new ElevatorMoveToHeightCommand(elevator, operationController, elevatorEncoder, 22194));
	    //OPERATION_CONTROLLER.getButton(ControlsMap.elevatorExtendButton).WhileHeld(new ElevatorExtendCommand());
		//OPERATION_CONTROLLER.getButton(ControlsMap.elevatorRetractButton).whileHeld(new ElevatorRetractCommand());
		
		/*if (OPERATION_CONTROLLER.getAxis(ControlsMap.elevatorExtendButton) > .25) {
			ELEVATOR_SUBSYSTEM.elevatorExtendCommand();
		}*/

		//DRIVE_CONTROLLER.getButton(ButtonCode.B).whenPressed(new ElevatorHoldPositionCommand());
		
		//LIGHTLINK LED
		//OPERATION_CONTROLLER.getButton(ButtonCode.A).whenPressed(new LEDRainbowCommand());
	    
		// driveController.getButton(ButtonCode.Y).whenPressed(new ElevatorExtendAndHoldCommand(elevator, operationController, elevatorEncoder));
	    
		
		/*
		if(OPERATION_CONTROLLER.getButtonValue(ButtonCode.RIGHTBUMPER)) {
			new IntakeWheelPullCommand().start();
			if(ELEVATOR_SUBSYSTEM.getEncoderValue() < ElevatorSubsystem.inchesToTicks(Calibrations.elevatorCubePickupMaximumHeight)) {
				new ArmExtendCommand().start();
				new ElevatorRetractCommand().start();
			}
		}
			//.whileHeld(new IntakeWheelPullCommand());
		
		
		if(OPERATION_CONTROLLER.getButtonValue(ButtonCode.LEFTBUMPER)) {
			new IntakeWheelPushHardCommand().start();
			if(ELEVATOR_SUBSYSTEM.getEncoderValue() > ElevatorSubsystem.inchesToTicks(Calibrations.elevatorMinimumScaleHeightInches)) {
				new ArmRetractCommand().start();
			}
		}
		*/
	    
	    /*
	    double armPowerValue = Robot.DRIVE_CONTROLLER.getAxis(AxisCode.RIGHTSTICKY);
	    Robot.ARM_JOYSTICK_CONTROL_COMMAND.setPowerValue(armPowerValue);
	    if (Robot.OPERATION_CONTROLLER.getAxisIsPressed(AxisCode.RIGHTTRIGGER)) {
	    	if (Math.abs(armPowerValue) > .1 && Robot.ARM_JOYSTICK_CONTROL_COMMAND.isRunning() == false) {
	    		Robot.ARM_JOYSTICK_CONTROL_COMMAND.start();
	    	}
	    } else {
	    	Robot.ARM_JOYSTICK_CONTROL_COMMAND.cancel();
	    }
	    */
	    
	    DRIVE_CONTROLLER.getButton(ButtonCode.A).whenPressed(new DriveTrainTurnRelativeDegreesCommand(DRIVE_TRAIN_SUBSYSTEM, 90, Calibrations.driveTrainTurnRelativeDegreesGyroAdjustmentScaleFactor));
	    
		OPERATION_CONTROLLER.getButton(ButtonCode.LEFTBUMPER).whileHeld(new IntakeWheelPullCommand());
		OPERATION_CONTROLLER.getButton(ButtonCode.RIGHTBUMPER).whileHeld(new IntakeWheelPushCommand());		
		
		OPERATION_CONTROLLER.getButton(ButtonCode.X).whenPressed(new ElevatorMoveToMinimumScaleHeightCommand());
		OPERATION_CONTROLLER.getButton(ButtonCode.B).whenPressed(new ElevatorMoveToBalancedScaleHeightCommand());
		OPERATION_CONTROLLER.getButton(ButtonCode.Y).whenPressed(new ElevatorExtendCommand());
		OPERATION_CONTROLLER.getButton(ButtonCode.A).whenPressed(new ElevatorRetractCommand());
		
		OPERATION_CONTROLLER.getButton(ButtonCode.BACK).whileHeld(new ArmRetractCommand());
		OPERATION_CONTROLLER.getButton(ButtonCode.START).whileHeld(new ArmExtendCommand());
		
		if(OPERATION_CONTROLLER.getButtonValue(ButtonCode.LEFTSTICK)) {
			Robot.ARM_SUBSYSTEM.resetEncodersToTop();
		}
		
		if(OPERATION_CONTROLLER.getButtonValue(ButtonCode.RIGHTSTICK)) {
			Robot.ARM_SUBSYSTEM.resetEncodersToBottom();
		}
		
		//DRIVE_CONTROLLER.getButton(ButtonCode.A).whenPressed(new DriveTrainDriveInchesCommand(100, .5, Calibrations.drivingForward));
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
