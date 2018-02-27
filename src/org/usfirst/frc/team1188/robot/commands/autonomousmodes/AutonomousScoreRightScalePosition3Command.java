package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPushHardCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousScoreRightScalePosition3Command extends CommandGroup {

    public AutonomousScoreRightScalePosition3Command() {
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveRightScalePosition3DriveForwardInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
    	addSequential(new DriveTrainDriveInchesCommand(30,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
    	addSequential(new ElevatorExtendCommand());
    	addSequential(new IntakeWheelPushHardCommand());
    	addSequential(new ElevatorRetractCommand());
    }
}
