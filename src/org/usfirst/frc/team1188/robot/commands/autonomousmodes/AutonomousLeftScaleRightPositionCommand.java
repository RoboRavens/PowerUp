package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.commandgroups.ScoreOnScaleCommandGroup;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLeftScaleRightPositionCommand extends CommandGroup {
    public AutonomousLeftScaleRightPositionCommand() {
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousOppositeSideScaleForwardMovementtInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			7));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, -90, Calibrations.driveTrainTurnRelativeDegreesGyroAdjustmentScaleFactor, 1.7));
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousOppositeSideScaleLateralMovementInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward,
    			8));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90, Calibrations.driveTrainTurnRelativeDegreesGyroAdjustmentScaleFactor, 1.7));
    	
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousOppositeSideScaleMoveIntoNullZoneInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90, Calibrations.driveTrainTurnRelativeDegreesGyroAdjustmentScaleFactor, 1.7));
    	
    	addSequential(new ScoreOnScaleCommandGroup());
    }
}
