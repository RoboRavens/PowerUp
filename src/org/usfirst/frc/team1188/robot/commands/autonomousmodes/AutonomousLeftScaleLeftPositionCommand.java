package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmMoveToMidwayCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.commandgroups.ScoreOnScaleCommandGroup;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPullCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelStopCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftScaleLeftPositionCommand extends CommandGroup {

	/* From the left position, drive straight forward to score in the scale.
	 * Steps:
	 * 	Drive forward partway
		Extend Elevator and retract the arm, just in case of drift
		Drive forward 2
		Turn right
		Drive forward 3
		Spit
		Drive backward
		Retract Elevator
		Retract arm
	 */
	
    public AutonomousLeftScaleLeftPositionCommand() {
    	
  	
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.LengthBetweenDriverWallAndScale,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			10));
    	
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90, AutonomousCalibrations.SwitchGyroScaleFactor, 1.5));

    	addSequential(new DriveTrainDriveInchesCommand(10,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingBackward,
    			1));
    	
    	addSequential(new ScoreOnScaleCommandGroup());
    	
    	/*
    	
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90, .012, 1.5));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveFromScaleToSwitchCubeStraightInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			10));
    	
    	
    	
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -45, .012, .75));
    	
    	addSequential(new ArmExtendFullyCommand());
    	addParallel(new IntakeWheelPullCommand());
    	
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveFromScaleToSwitchCubeAngledInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			10));
    	
    	addSequential(new IntakeWheelStopCommand());
    	addSequential(new ArmRetractFullyCommand());
    	
    	*/
    	
    	/*
    	addSequential(new ElevatorExtendWhileHeldCommand());
    	addParallel(new ArmRetractFullyCommand());
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveStraightToScaleForwardElevatorLiftingSegmentInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveStraightToScaleApproachScaleInches,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new IntakeWheelsSpitCommand(AutonomousCalibrations.AutonomousScoreSwitchIntakePushPowerMagnitude));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveStraightToScaleApproachScaleInches,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingBackward));
    	addSequential(new ElevatorRetractWhileHeldCommand());
    	*/
    }
}
