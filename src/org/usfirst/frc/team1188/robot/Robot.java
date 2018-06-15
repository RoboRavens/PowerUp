/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1188.robot;


import org.usfirst.frc.team1188.autonomous.AutonomousCommandProvider;
import org.usfirst.frc.team1188.gamepad.ButtonCode;
import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.gamepad.OperationPanel;
import org.usfirst.frc.team1188.gamepad.OperationPanel2;
import org.usfirst.frc.team1188.ravenhardware.RavenLighting;
import org.usfirst.frc.team1188.robot.commands.LED.LEDBlinkFor2SecondsCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendToHighScaleCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmMoveToMidwayCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.SetGyroTargetHeading;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendQuarterWithPIDCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPullCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPushHardCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPushSoftCommand;
import org.usfirst.frc.team1188.robot.commands.misc.SetOverride1Command;
import org.usfirst.frc.team1188.robot.commands.misc.SetOverride2Command;
import org.usfirst.frc.team1188.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.IntakeClampSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.IntakeWheelSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.LEDSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.LightSubsystem;
import org.usfirst.frc.team1188.util.LoggerOverlord;
import org.usfirst.frc.team1188.util.OverrideSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
	public static final OperationPanel OPERATION_PANEL = new OperationPanel(2);	
	public static final OperationPanel2 OPERATION_PANEL2 = new OperationPanel2(3);
			
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
	
	public static final OverrideSystem OVERRIDE_SYSTEM_ELEVATOR_EXTEND = new OverrideSystem();
	public static final OverrideSystem OVERRIDE_SYSTEM_ARM_EXTEND = new OverrideSystem();
	public static final OverrideSystem OVERRIDE_SYSTEM_ELEVATOR_RETRACT = new OverrideSystem();
	public static final OverrideSystem OVERRIDE_SYSTEM_ARM_RETRACT = new OverrideSystem();
	public static final OverrideSystem OVERRIDE_SYSTEM_INTAKE = new OverrideSystem();
	
	public static final Solenoid ARM_HOLD_BACK = new Solenoid(RobotMap.armHoldBackSolenoid);
	
	public static final AutonomousCommandProvider AUTONOMOUS_COMMAND_PROVIDER = new AutonomousCommandProvider();


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
		
		setupDriveController();
		setupOperationPanel();
		

		// this.elevator.getPosition();
		// this.elevator.getIsAtLimits();
		
		ELEVATOR_SUBSYSTEM.elevatorMotor.config_kF(TalonSRXConstants.kPIDLoopIdx, 0.0, TalonSRXConstants.kTimeoutMs);
		ELEVATOR_SUBSYSTEM.elevatorMotor.config_kP(TalonSRXConstants.kPIDLoopIdx, 8.4, TalonSRXConstants.kTimeoutMs);
		ELEVATOR_SUBSYSTEM.elevatorMotor.config_kI(TalonSRXConstants.kPIDLoopIdx, 0.0, TalonSRXConstants.kTimeoutMs);
		ELEVATOR_SUBSYSTEM.elevatorMotor.config_kD(TalonSRXConstants.kPIDLoopIdx, 1.25, TalonSRXConstants.kTimeoutMs);
		
		int absolutePosition = ELEVATOR_SUBSYSTEM.elevatorMotor.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (TalonSRXConstants.kSensorPhase)
			absolutePosition *= -1;
		if (TalonSRXConstants.kMotorInvert)
			absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		ELEVATOR_SUBSYSTEM.elevatorMotor.setSelectedSensorPosition(absolutePosition, TalonSRXConstants.kPIDLoopIdx, TalonSRXConstants.kTimeoutMs);
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
		Robot.LED_SUBSYSTEM.setDisabledPattern();
		
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
		
		SmartDashboard.putString("DB/String 5", "TBD - Awaiting plates");
		
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
			case AutonomousCalibrations.FlexSwitch:
				autonomousModeName += "Flex - switch priority.";
				break;
			case AutonomousCalibrations.FlexScale:
				autonomousModeName += "Flex - scale priority.";
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
		Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetDriveEncoders();
		
		m_autonomousCommand = m_chooser.getSelected();
		// Zero the gyro, grab the selected autonomous mode, and get to work.
		Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeadingToCurrentHeading();
		Class<? extends Command> autonomousCommandClass = AUTONOMOUS_COMMAND_PROVIDER.getAutonomousCommand(autoFromDashboard, positionFromDashboard, DriverStation.getInstance().getGameSpecificMessage());
		try {
			autonomousCommand = autonomousCommandClass.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String autoCommandName = autonomousCommand.getClass().getSimpleName();
		SmartDashboard.putString("DB/String 5", autoCommandName);
		
		// DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetOrientationGyro();
		DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroTargetHeadingToCurrentHeading();
		// autonomousCommand = new DriveTrainDriveInchesCommand(120, .5, Calibrations.drivingForward);
		// autonomousCommand = new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90);
		/*autonomousCommand = new DriveTrainDriveInchesCommand(240,
    			1,
    			Calibrations.drivingForward,
    			20);*/
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
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
		
	    if (DRIVE_TRAIN_SUBSYSTEM.ravenTank.userControlOfCutPower) {
	      if (DRIVE_CONTROLLER.getAxis(ControlsMap.driveCutPowerAxis) > .25) {
	    	  System.out.println("CUT POWER TRUE");
	        DRIVE_TRAIN_SUBSYSTEM.ravenTank.setCutPower(true);
	      }
	      else {
	        DRIVE_TRAIN_SUBSYSTEM.ravenTank.setCutPower(false);
	      }		
	    }
		 
		 diagnostics.outputTeleopDiagnostics();

		 if (getMatchIsAtTime(90)) {
			 LEDBlinkFor2SecondsCommand command = new LEDBlinkFor2SecondsCommand(4);
			    command.start();
		 } else if (getMatchIsAtTime(60)) {
			 LEDBlinkFor2SecondsCommand command = new LEDBlinkFor2SecondsCommand(3);
				command.start();
		 } else if (getMatchIsAtTime(30)) {
			 LEDBlinkFor2SecondsCommand command = new LEDBlinkFor2SecondsCommand(2);
				command.start();
		 } else if (getMatchIsAtTime(10)) {
			 LEDBlinkFor2SecondsCommand command = new LEDBlinkFor2SecondsCommand(1);
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
	
	public void setupDriveController() {
		
		DRIVE_CONTROLLER.getButton(ButtonCode.X).whenPressed(new SetGyroTargetHeading(270));
		DRIVE_CONTROLLER.getButton(ButtonCode.B).whenPressed(new SetGyroTargetHeading(90));
		DRIVE_CONTROLLER.getButton(ButtonCode.Y).whenPressed(new SetGyroTargetHeading(0));
		DRIVE_CONTROLLER.getButton(ButtonCode.A).whenPressed(new SetGyroTargetHeading(180));
	}
		
	public void setupOperationPanel() {
		System.out.println("Operation PANEL CONFIGURED!!! Operation PANEL CONFIGURED!!!Operation PANEL CONFIGURED!!!Operation PANEL CONFIGURED!!!Operation PANEL CONFIGURED!!!Operation PANEL CONFIGURED!!!");
		OPERATION_PANEL2.getButton(ButtonCode.ARMEXTEND).whenPressed(new ArmExtendFullyCommand());
		OPERATION_PANEL2.getButton(ButtonCode.ARMMIDRANGE).whenPressed(new ArmMoveToMidwayCommand()); 
		OPERATION_PANEL.getButton(ButtonCode.ARMMANUALOVERRIDEEXTEND).whileHeld(new ArmExtendWhileHeldCommand());
		//OPERATION_PANEL.getButton(ButtonCode.ARMMANUALOVERRIDEEXTEND).whenPressed(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ARM_EXTEND, true));
		//OPERATION_PANEL.getButton(ButtonCode.ARMMANUALOVERRIDEEXTEND).whenReleased(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ARM_EXTEND, false));
		OPERATION_PANEL.getButton(ButtonCode.ARMDOUBLEOVERRIDEEXTEND).whenPressed(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ARM_EXTEND, true));
		OPERATION_PANEL.getButton(ButtonCode.ARMDOUBLEOVERRIDEEXTEND).whenReleased(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ARM_EXTEND, false));
		
		OPERATION_PANEL.getButton(ButtonCode.ARMMANUALOVERRIDERETRACT).whileHeld(new ArmRetractWhileHeldCommand());
		//OPERATION_PANEL.getButton(ButtonCode.ARMMANUALOVERRIDERETRACT).whenPressed(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ARM_RETRACT, true));
		//OPERATION_PANEL.getButton(ButtonCode.ARMMANUALOVERRIDERETRACT).whenReleased(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ARM_RETRACT, false));
		OPERATION_PANEL.getButton(ButtonCode.ARMDOUBLEOVERRIDERETRACT).whenPressed(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ARM_RETRACT, true));
		OPERATION_PANEL.getButton(ButtonCode.ARMDOUBLEOVERRIDERETRACT).whenReleased(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ARM_RETRACT, false));
		OPERATION_PANEL2.getButton(ButtonCode.ARMRETRACT).whenPressed(new ArmRetractFullyCommand());
		
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMANUALOVERRIDEDOWN).whileHeld(new ElevatorRetractWhileHeldCommand());
		
		
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMANUALOVERRIDEDOWN).whenPressed(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_RETRACT, true));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMANUALOVERRIDEDOWN).whenReleased(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_RETRACT, false));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORDOUBLEOVERRIDEDOWN).whenPressed(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_RETRACT, true));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORDOUBLEOVERRIDEDOWN).whenReleased(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_RETRACT, false));
		
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMANUALOVERRIDEUP).whileHeld(new ElevatorExtendWhileHeldCommand());
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMANUALOVERRIDEUP).whenPressed(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_EXTEND, true));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMANUALOVERRIDEUP).whenReleased(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_EXTEND, false));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORDOUBLEOVERRIDEUP).whenPressed(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_EXTEND, true));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORDOUBLEOVERRIDEUP).whenReleased(new SetOverride2Command(Robot.OVERRIDE_SYSTEM_ELEVATOR_EXTEND, false));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATOREXTEND).whenPressed(new ElevatorExtendFullyCommand());
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMIDRANGE).whenPressed(new ElevatorMoveToHeightCommand(Calibrations.elevatorMidwayEncoderValue));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORMIDRANGE).whenPressed(new ArmMoveToMidwayCommand());
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORSWITCHHEIGHT).whenPressed(new ElevatorMoveToHeightCommand(Calibrations.elevatorSwitchEncoderValue));
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORSWITCHHEIGHT).whenPressed(new ArmExtendFullyCommand());
		OPERATION_PANEL.getButton(ButtonCode.ELEVATORRETRACT).whenPressed(new ElevatorRetractFullyCommand());
		
		
		// CZB Shepherd Friday: stealing this button to make a one-press, elevator to high scale button.
		OPERATION_PANEL2.getButton(ButtonCode.INTAKEOVERRIDE).whenPressed(new ArmExtendToHighScaleCommand());
		OPERATION_PANEL2.getButton(ButtonCode.INTAKEOVERRIDE).whenPressed(new ElevatorExtendFullyCommand());		
		
		
		OPERATION_PANEL2.getButton(ButtonCode.INTAKEDROP).whileHeld(new IntakeWheelPushSoftCommand());
		//OPERATION_PANEL2.getButton(ButtonCode.INTAKEOVERRIDE).whileHeld(new IntakeWheelPullCommand());
		//OPERATION_PANEL2.getButton(ButtonCode.INTAKEOVERRIDE).whenPressed(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_INTAKE, true));
		//OPERATION_PANEL2.getButton(ButtonCode.INTAKEOVERRIDE).whenReleased(new SetOverride1Command(Robot.OVERRIDE_SYSTEM_INTAKE, false));
		OPERATION_PANEL2.getButton(ButtonCode.INTAKESPIT).whileHeld(new IntakeWheelPushHardCommand());
		OPERATION_PANEL2.getButton(ButtonCode.RUNINTAKE).whileHeld(new IntakeWheelPullCommand());
		
		//PCDashboardDiagnostics.SubsystemBoolean("Elevator", "RUNINTAKE", OPERATION_PANEL.getButtonValue(ButtonCode.ELEVATORRETRACT));		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

