package org.usfirst.frc.team1188.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1188.robot.subsystems.*;
import org.usfirst.frc.team1188.gamepad.AxisCode;
import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.*;

public class DriveTrainDriveFPSCommand extends Command {
	Robot robot;
    DriveTrainSubsystem driveTrain;
    Gamepad driveController;

    public DriveTrainDriveFPSCommand(DriveTrainSubsystem driveTrain, Gamepad driveController) {
    	requires(driveTrain);
    	this.driveTrain = driveTrain;
    	this.robot = driveTrain.robot;
    	this.driveController = driveController;
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// System.out.println("Executing tank drive command.");
        double leftYAxisValue = driveController.getAxis(AxisCode.LEFTSTICKY);
    	double rightYAxisValue = driveController.getAxis(AxisCode.RIGHTSTICKY);
    	double rightXAxisValue = driveController.getAxis(AxisCode.RIGHTSTICKX);
    	
    	
    	driveTrain.ravenTank.drive(leftYAxisValue, rightYAxisValue, rightXAxisValue);
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
