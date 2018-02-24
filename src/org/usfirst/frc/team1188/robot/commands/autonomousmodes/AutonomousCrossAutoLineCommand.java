package org.usfirst.frc.team1188.robot.commands.autonomousmodes;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCrossAutoLineCommand extends CommandGroup {

    public AutonomousCrossAutoLineCommand() {
    	addSequential(new DriveTrainDriveInchesCommand(AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardInches,
    			AutonomousCalibrations.AutonomousCrossAutoLineDriveForwardPowerMagnitude,
    			Calibrations.drivingForward));
    }
}
