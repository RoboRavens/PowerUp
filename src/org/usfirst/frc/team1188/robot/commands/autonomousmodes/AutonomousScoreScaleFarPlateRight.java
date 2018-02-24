package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.*;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousScoreScaleFarPlateRight extends CommandGroup {

    public AutonomousScoreScaleFarPlateRight() {
    	addSequential(new DriveTrainDriveInchesCommand(Calibrations.AutonomousCrossAutoLineDriveForwardInches,
    			Calibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
    	addSequential(new DriveTrainDriveInchesCommand(30,
    			Calibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
    	addSequential(new DriveTrainDriveInchesCommand(30,
    			Calibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    
    }
}
