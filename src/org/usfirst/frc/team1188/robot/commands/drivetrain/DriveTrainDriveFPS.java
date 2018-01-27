package org.usfirst.frc.team1188.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1188.robot.subsystems.*;
import org.usfirst.frc.team1188.robot.*;

public class DriveTrainDriveFPS extends Command {
	Robot robot;
    DriveTrain driveTrain;
    Joystick driveController;

    public DriveTrainDriveFPS(DriveTrain driveTrain, Joystick driveController) {
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
        double leftYAxisValue = driveController.getRawAxis(1);
    	double rightYAxisValue = driveController.getRawAxis(5);
    	double rightXAxisValue = driveController.getRawAxis(4);
    	
    	
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
