package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.commandgroups.ScoreOnScaleCommandGroup;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractWhileHeldCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPullCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelStopCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

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
    }
}
