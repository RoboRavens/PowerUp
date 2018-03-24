package org.usfirst.frc.team1188.robot.commands;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainTurnRelativeDegreesCommand;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LimeLightFaceCube extends Command {

    public LimeLightFaceCube() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.LIMELIGHT_SUBSYSTEM);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double angleOffHorizontal = Robot.LIMELIGHT_SUBSYSTEM.angleOffHorizontal();
    	PCDashboardDiagnostics.AdHocNumber("AngleOffHorizontal", angleOffHorizontal);
    	DriveTrainTurnRelativeDegreesCommand cmd = new DriveTrainTurnRelativeDegreesCommand(Robot.DRIVE_TRAIN_SUBSYSTEM, angleOffHorizontal, Calibrations.gyroAdjustmentDefaultScaleFactor);
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
