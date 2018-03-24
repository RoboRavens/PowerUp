package org.usfirst.frc.team1188.robot.commands;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveInchesCommand;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimeLightDriveToCube extends Command {

    public LimeLightDriveToCube() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.LIMELIGHT_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double limeLightDistance = Robot.LIMELIGHT_SUBSYSTEM.limeLightDistance();
    	DriveTrainDriveInchesCommand cmd = new DriveTrainDriveInchesCommand(limeLightDistance, .5, 1);
    	cmd.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}