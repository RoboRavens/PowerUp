package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousScoreScaleFarPlateRight extends CommandGroup {

    public AutonomousScoreScaleFarPlateRight() {
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardInches,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
    	addSequential(new DriveTrainDriveInchesCommand(30,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
    	addSequential(new DriveTrainDriveInchesCommand(30,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    
    }
}
