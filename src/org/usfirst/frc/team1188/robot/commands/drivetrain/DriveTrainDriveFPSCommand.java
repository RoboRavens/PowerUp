package org.usfirst.frc.team1188.robot.commands.drivetrain;

import org.usfirst.frc.team1188.gamepad.AxisCode;
import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainDriveFPSCommand extends Command {

    public DriveTrainDriveFPSCommand() {
    	requires(Robot.DRIVE_TRAIN_SUBSYSTEM);
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// System.out.println("Executing tank drive command.");
        double leftYAxisValue = Robot.DRIVE_CONTROLLER.getAxis(AxisCode.LEFTSTICKY);
    	double rightYAxisValue = Robot.DRIVE_CONTROLLER.getAxis(AxisCode.RIGHTSTICKY);
    	double rightXAxisValue = Robot.DRIVE_CONTROLLER.getAxis(AxisCode.RIGHTSTICKX);
    	
    	Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.drive(leftYAxisValue, rightYAxisValue, rightXAxisValue);
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
