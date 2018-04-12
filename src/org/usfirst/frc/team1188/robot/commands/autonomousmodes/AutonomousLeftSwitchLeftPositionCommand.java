package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmMoveToMidwayCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLeftSwitchLeftPositionCommand extends CommandGroup{
	
	public AutonomousLeftSwitchLeftPositionCommand() {
		
		
		// 148 inches forward; turn right, drive 40 inches, spit cube.
		// At any point, move the arm into position.
		// double driveForwardDistance = AutonomousCalibrations.LengthBetweenDriverWallAndSwitch + AutonomousCalibrations.LengthOfSwitch / 2;
				
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.StraightSwitchDriveForwardFromWallInches,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward,
				AutonomousCalibrations.SideSwitchDriveForwardFromWallTimeoutSeconds));
		addSequential(new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, 90));
		addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.StraightSwitchDriveForwardToSwitchInches,
				AutonomousCalibrations.AutonomousScoreSwitchDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
		addSequential(new ElevatorMoveToHeightCommand(Calibrations.elevatorSwitchEncoderValue));
		addSequential(new ArmExtendFullyCommand());
		addSequential(new IntakeWheelsSpitCommand(AutonomousCalibrations.AutonomousScoreSwitchIntakePushPowerMagnitude));
	}

}
