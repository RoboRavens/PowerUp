package org.usfirst.frc.team1188.robot.commands.drivetrain;

import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainTurnRelativeDegreesCommand extends Command {
	DriveTrainSubsystem driveTrain;
	RavenTank ravenTank;
	Timer safetyTimer;
	
	// Negative degrees means turn left; positive means turn right.
	double degreesToTurn;
	double driveTrainOriginalHeading;
	double temporaryGyroScaleFactor;
	double previousGyroScaleFactor;
	
    public DriveTrainTurnRelativeDegreesCommand(DriveTrainSubsystem driveTrain, double degreesToTurn, double gyroScaleFactor) {
        requires(driveTrain);
        this.ravenTank = driveTrain.ravenTank;
        this.degreesToTurn = degreesToTurn;
        this.safetyTimer = new Timer();
        this.previousGyroScaleFactor = Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getGyroAdjustmentScaleFactor();
        this.temporaryGyroScaleFactor = gyroScaleFactor;
    }

    public DriveTrainTurnRelativeDegreesCommand(DriveTrainSubsystem driveTrain, double degreesToTurn) {
    	this(driveTrain, degreesToTurn, Calibrations.driveTrainTurnRelativeDegreesGyroAdjustmentScaleFactor);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrainOriginalHeading = ravenTank.getCurrentHeading();
    	ravenTank.turnRelativeDegrees(degreesToTurn);
    	safetyTimer.start();
    	Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroAdjustmentScaleFactor(temporaryGyroScaleFactor);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// System.out.println(" turning relative degrees.");
    	ravenTank.fpsTank(0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double currentHeading = ravenTank.getCurrentHeading();
    	double degreesTurned = currentHeading - driveTrainOriginalHeading;
    	
    	double degreesAwayFromTarget = Math.abs(degreesToTurn - degreesTurned);
    	boolean turnComplete = (degreesAwayFromTarget < Calibrations.gyroAutoTurnAcceptableErrorDegrees);
    	
    	if (safetyTimer.get() > Calibrations.DriveTrainTurnRelativeDegreesSafetyTimerSeconds) {
    		turnComplete = true;
    	}
    	
        return turnComplete;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ravenTank.setGyroTargetHeadingToCurrentHeading();
    	Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.setGyroAdjustmentScaleFactor(previousGyroScaleFactor);
    	ravenTank.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
