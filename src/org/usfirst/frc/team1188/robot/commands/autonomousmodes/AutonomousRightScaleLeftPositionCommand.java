package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRightScaleLeftPositionCommand extends CommandGroup {

    public AutonomousRightScaleLeftPositionCommand() {
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousOppositeSideScaleElevatorDownSegmentInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousOppositeSideScaleLateralMovementInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
    	
    	
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveOppositeSideScaleElevatorUpSegmentInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90));
    	
    	addSequential(new ElevatorExtendWhileHeldCommand());
    	addParallel(new ArmRetractFullyCommand());
    	
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveStraightToScaleApproachScaleInches,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    	addSequential(new IntakeWheelsSpitCommand(AutonomousCalibrations.AutonomousScoreSwitchIntakePushPowerMagnitude));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveStraightToScaleApproachScaleInches,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingBackward));
    	addSequential(new ElevatorRetractWhileHeldCommand());
    	
    
    }
}
