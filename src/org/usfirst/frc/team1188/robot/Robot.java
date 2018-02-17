/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1188.robot;

import org.usfirst.frc.team1188.gamepad.ButtonCode;
import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorHoldPositionCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToBalancedScaleHeightCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToMaximumScaleHeightCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToMinimumScaleHeightCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPullCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPushCommand;
import org.usfirst.frc.team1188.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.IntakeClampSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.IntakeWheelSubsystem;
import org.usfirst.frc.team1188.robot.subsystems.LightSubsystem;

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
	
	Diagnostics diagnostics = new Diagnostics();
	
	public static final Gamepad DRIVE_CONTROLLER = new Gamepad(0);
	public static final Gamepad OPERATION_CONTROLLER = new Gamepad(1);
	// public static final Gamepad buttonPanel = new Gamepad(2);	
			
	public static final DriveTrainSubsystem DRIVE_TRAIN_SUBSYSTEM = new DriveTrainSubsystem();
	public static final ElevatorSubsystem ELEVATOR_SUBSYSTEM = new ElevatorSubsystem();
	public static final ArmSubsystem ARM_SUBSYSTEM = new ArmSubsystem();
	public static final IntakeClampSubsystem INTAKE_CLAMP_SUBSYSTEM = new IntakeClampSubsystem();
	public static final IntakeWheelSubsystem INTAKE_WHEEL_SUBSYSTEM = new IntakeWheelSubsystem();
	public static final LightSubsystem LIGHT_SUBSYSTEM = new LightSubsystem();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//NetworkTable table = NetworkTable.getTable("limelight");
		//table.putDouble("ledMode", 1);
		System.out.println("disable limelight");
		
		// m_chooser.addDefault("Default Auto", new Command());
		// m_chooser.addObject(name, object);
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		// Zero the elevator encoders; the robot should always start with the elevator down.
		// Note that this may not be true in practice, so we should later integrate the reset with limit switch code.
		Robot.ELEVATOR_SUBSYSTEM.resetEncoders();
		

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

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetOrientationGyro();
		
		diagnostics.outputDisabledDiagnostics();

		// this.elevator.getPosition();
		// this.elevator.getIsAtLimits();
		//this.arm.getPosition();
		
		if (DRIVE_CONTROLLER.getButtonValue(ControlsMap.driveShiftToHighGearButton)) {
			Robot.ELEVATOR_SUBSYSTEM.resetEncoders();
		}
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
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
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

		DRIVE_TRAIN_SUBSYSTEM.ravenTank.resetOrientationGyro();
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		runOperatorControls();
		
		 // this.elevator.getPosition();
		// this.elevator.getIsAtLimits();
		Robot.ARM_SUBSYSTEM.getPosition();
		 
		 diagnostics.outputTeleopDiagnostics();
	}
	
	public void runOperatorControls() {
		// Drive Train
		if (DRIVE_CONTROLLER.getButtonValue(ControlsMap.driveShiftToHighGearButton) || OPERATION_CONTROLLER.getButtonValue(ControlsMap.operationShiftToLowGearButton)) {
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
	    
		//driveController.getButton(ButtonCode.A).whenPressed(new ElevatorMoveToHeightCommand(elevator, operationController, elevatorEncoder, 22194));
	    DRIVE_CONTROLLER.getButton(ControlsMap.elevatorExtendButton).whenPressed(new ElevatorExtendCommand());
		DRIVE_CONTROLLER.getButton(ControlsMap.elevatorRetractButton).whenPressed(new ElevatorRetractCommand());

		DRIVE_CONTROLLER.getButton(ButtonCode.B).whileHeld(new ElevatorHoldPositionCommand());
	    
		// driveController.getButton(ButtonCode.Y).whenPressed(new ElevatorExtendAndHoldCommand(elevator, operationController, elevatorEncoder));
	    
		DRIVE_CONTROLLER.getButton(ButtonCode.X).whileHeld(new ArmExtendCommand());
		DRIVE_CONTROLLER.getButton(ButtonCode.Y).whileHeld(new ArmRetractCommand());
		
		DRIVE_CONTROLLER.getButton(ButtonCode.LEFTSTICK).whileHeld(new IntakeWheelPullCommand());
		DRIVE_CONTROLLER.getButton(ButtonCode.RIGHTSTICK).whileHeld(new IntakeWheelPushCommand());
		
		OPERATION_CONTROLLER.getButton(ButtonCode.X).whenPressed(new ElevatorMoveToMinimumScaleHeightCommand());
		OPERATION_CONTROLLER.getButton(ButtonCode.Y).whenPressed(new ElevatorMoveToBalancedScaleHeightCommand());
		OPERATION_CONTROLLER.getButton(ButtonCode.B).whenPressed(new ElevatorMoveToMaximumScaleHeightCommand());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
