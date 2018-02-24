package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmMoveToBottomCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ForAutonomousArmExtendCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.intake.ForAutonomousIntakeWheelPullCommand;
import org.usfirst.frc.team1188.robot.commands.intake.ForAutonomousIntakeWheelPushCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousScoreLeftSwitchGrabCubePosition1Command extends CommandGroup{
	
	public AutonomousScoreLeftSwitchGrabCubePosition1Command() {
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchPosition1DriveForwardInches,
				AutonomousCalibrations.AutonomousScoreLeftSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
		addSequential(new ForAutonomousIntakeWheelPushCommand(AutonomousCalibrations.AutonomousScoreSwitchIntakePushPowerMagnitude));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchGrabCubePosition1FirstDriveForwardInches,
				AutonomousCalibrations.AutonomousScoreLeftSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
		// addSequential(new ArmMoveToBottomCommand(AutonomousCalibrations.AutonomousArmExtensionEncoderDistance));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchGrabCubePosition1SecondDriveForwardInches,
				AutonomousCalibrations.AutonomousScoreLeftSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
		addSequential(new ForAutonomousIntakeWheelPullCommand(AutonomousCalibrations.AutonomousGrabCubeIntakePullPowerMagnitude));
	}

}
