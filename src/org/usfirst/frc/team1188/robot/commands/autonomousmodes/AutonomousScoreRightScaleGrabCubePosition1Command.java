package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPushHardCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousScoreRightScaleGrabCubePosition1Command extends CommandGroup{
	
	public AutonomousScoreRightScaleGrabCubePosition1Command() {
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveRightScalePosition1FirstDriveForwardInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveRightScalePosition1SecondDriveForwardInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveRightScalePosition1ThirdDriveForwardInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
    	addSequential(new DriveTrainDriveInchesCommand(30,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
    	addSequential(new ElevatorExtendWhileHeldCommand());
    	addSequential(new IntakeWheelPushHardCommand());
    	addSequential(new ElevatorRetractWhileHeldCommand());
    	
    
	}

}
