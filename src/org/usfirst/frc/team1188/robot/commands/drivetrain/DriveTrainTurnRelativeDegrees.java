package org.usfirst.frc.team1188.robot.commands.drivetrain;

import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainTurnRelativeDegrees extends Command {
	DriveTrain driveTrain;
	RavenTank ravenTank;
	Timer safetyTimer;
	
	// Negative degrees means turn left; positive means turn right.
	double degreesToTurn;
	double driveTrainOriginalHeading;
	
    public DriveTrainTurnRelativeDegrees(DriveTrain driveTrain, double degreesToTurn) {
        requires(driveTrain);
        this.ravenTank = driveTrain.ravenTank;
        this.degreesToTurn = degreesToTurn;
        this.safetyTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrainOriginalHeading = ravenTank.getCurrentHeading();
    	ravenTank.turnRelativeDegrees(degreesToTurn);
    	safetyTimer.start();
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
    	ravenTank.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
