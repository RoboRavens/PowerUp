package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ForAutonomousArmExtendCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.intake.ForAutonomousIntakeWheelPullCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousScoreLeftSwitchGrabCubePosition1Command extends CommandGroup{
	
	public AutonomousScoreLeftSwitchGrabCubePosition1Command() {
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchPosition1DriveForwardInches,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
		addSequential(new IntakeWheelsSpitCommand(AutonomousCalibrations.AutonomousScoreSwitchIntakePushPowerMagnitude));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchGrabCubePosition1FirstDriveForwardInches,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
		addParallel(new ForAutonomousArmExtendCommand(AutonomousCalibrations.AutonomousArmExtensionEncoderDistance));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousScoreLeftSwitchGrabCubePosition1SecondDriveForwardInches,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
		addSequential(new ForAutonomousIntakeWheelPullCommand(AutonomousCalibrations.AutonomousGrabCubeIntakePullPowerMagnitude));
	}

}
