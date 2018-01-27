package org.usfirst.frc.team1188.robot.commands.drivetrain;

import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainDriveInches extends Command {
	Robot robot;
	DriveTrain driveTrain;
	RavenTank ravenTank;
	
	double powerMagnitude;
	double totalInchesToTravel;
	double driveTrainNetInchesTraveledAtStart;
	double netInchesTraveledSoFar = 0;
	int direction;
	Timer timeoutTimer;
	double timeoutSeconds = 9999999;
	
    public DriveTrainDriveInches(DriveTrain driveTrain, double inchesToTravel, double powerMagnitude, int direction) {
    	requires(driveTrain);
    	this.driveTrain = driveTrain;
    	this.ravenTank = driveTrain.ravenTank;
    	this.robot = driveTrain.robot;
    	this.totalInchesToTravel = inchesToTravel;
    	this.powerMagnitude = powerMagnitude *= direction;
    	this.direction = direction;
    	this.timeoutTimer = new Timer();
    }
    
    public DriveTrainDriveInches(DriveTrain driveTrain, double inchesToTravel, double powerMagnitude, int direction, double timeoutSeconds) {
    	requires(driveTrain);
    	this.driveTrain = driveTrain;
    	this.ravenTank = driveTrain.ravenTank;
    	this.robot = driveTrain.robot;
    	this.totalInchesToTravel = inchesToTravel;
    	this.powerMagnitude = powerMagnitude *= direction;
    	this.direction = direction;
    	this.timeoutTimer = new Timer();
    	this.timeoutSeconds = timeoutSeconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("RT NIT:" + ravenTank.getNetInchesTraveled());
    	driveTrainNetInchesTraveledAtStart = ravenTank.getNetInchesTraveled();
    	timeoutTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(" driving inches.");
    	ravenTank.fpsTank(powerMagnitude, 0);
    	
    	double driveTrainTotalInchesTraveled = ravenTank.getNetInchesTraveled();
    	netInchesTraveledSoFar = driveTrainTotalInchesTraveled - driveTrainNetInchesTraveledAtStart;
    	
    	if (direction == Calibrations.drivingBackward) {
    		netInchesTraveledSoFar = driveTrainNetInchesTraveledAtStart - driveTrainTotalInchesTraveled;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean hasTraveledTargetDistance = (netInchesTraveledSoFar >= totalInchesToTravel); 
        
        if (timeoutTimer.get() > timeoutSeconds) {
        	hasTraveledTargetDistance = true;
        }
    	
    	return hasTraveledTargetDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	ravenTank.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
