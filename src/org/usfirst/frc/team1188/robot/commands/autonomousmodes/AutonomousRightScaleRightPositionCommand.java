package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.commandgroups.ScoreOnScaleCommandGroup;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainStopCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/* From the right position, drive straight forward to score in the scale.
 * Steps:
 * 	Drive forward partway
	Extend Elevator and retract the arm, just in case of drift
	Drive forward 2
	Turn left
	Drive forward 3
	Spit
	Drive backward
	Retract Elevator
	Retract arm
 */

public class AutonomousRightScaleRightPositionCommand extends CommandGroup {
	public AutonomousRightScaleRightPositionCommand() {
		
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.LengthBetweenDriverWallAndScale,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			10));
    	
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90, AutonomousCalibrations.SwitchGyroScaleFactor, 1.5));

    	addSequential(new DriveTrainDriveInchesCommand(10,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingBackward,
    			1));
    	addSequential(new DriveTrainStopCommand());
    	addSequential(new ScoreOnScaleCommandGroup());
		
		
		/*
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.LengthBetweenDriverWallAndScale,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			10));
    	
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90, .012, 1.5));

    	addSequential(new ScoreOnScaleCommandGroup());
    	
    	
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90, .012, 1.5));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveFromScaleToSwitchCubeStraightInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			10));
    	
    	
    	
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 45, .012, .75));
    	
    	addSequential(new ArmExtendFullyCommand());
    	addParallel(new IntakeWheelPullCommand());
    	
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveFromScaleToSwitchCubeAngledInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			10));
    	
    	addSequential(new IntakeWheelStopCommand());
    	addSequential(new ArmRetractFullyCommand());
    	
    	*/
    }
}
