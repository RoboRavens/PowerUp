package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractCommand;
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
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousDriveStraightToScaleForwardElevatorDownSegmentInches,
    			AutonomousCalibrations.AutonomousDriveScaleDriveForwardPowerMagniude,
    			Calibrations.drivingForward));
    	addSequential(new ElevatorExtendCommand());
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
    	addSequential(new ElevatorRetractCommand());
    }
}
