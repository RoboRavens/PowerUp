package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.intake.ForAutonomousIntakeWheelPushCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousScoreRightSwitchPosition1Command extends CommandGroup{
	
	public AutonomousScoreRightSwitchPosition1Command() {
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchPosition3FirstDriveForwardInches,
				AutonomousCalibrations.AutonomousScoreLeftSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchPosition3SecondDriveForwardInches,
				AutonomousCalibrations.AutonomousScoreLeftSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchPosition3ThirdDriveForwardInches,
				AutonomousCalibrations.AutonomousScoreLeftSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
		addSequential(new ForAutonomousIntakeWheelPushCommand(AutonomousCalibrations.AutonomousScoreSwitchIntakePushPowerMagnitude));
	}

}
