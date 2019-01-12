package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainStopCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousRightSwitchMiddlePositionCommand extends CommandGroup{
	
	public AutonomousRightSwitchMiddlePositionCommand() {
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreSwitchMiddlePositionDriveForwardFirstSegmentInches,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90, AutonomousCalibrations.SwitchGyroScaleFactor, 1.5));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreSwitchMiddlePositionLateralDriveForwardInches + AutonomousCalibrations.ExchangeZoneBufferMiddlePositionRightSwitch,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90, AutonomousCalibrations.SwitchGyroScaleFactor, 1.5));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreSwitchMiddlePositionDriveForwardSecondSegmentInches,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward,
    			2.5));

		addSequential(new DriveTrainStopCommand());
		// addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
		addSequential(new ElevatorMoveToHeightCommand(Calibrations.elevatorSwitchEncoderValue));
		addSequential(new ArmExtendFullyCommand());
		addSequential(new IntakeWheelsSpitCommand(AutonomousCalibrations.AutonomousScoreSwitchIntakePushPowerMagnitude));
	}

}
