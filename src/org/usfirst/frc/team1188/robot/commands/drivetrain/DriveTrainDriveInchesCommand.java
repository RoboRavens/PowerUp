package org.usfirst.frc.team1188.robot.commands.drivetrain;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainDriveInchesCommand extends Command {	
	double powerMagnitude;
	double totalInchesToTravel;
	double driveTrainNetInchesTraveledAtStart;
	double netInchesTraveledSoFar = 0;
	int direction;
	Timer timeoutTimer;
	double timeoutSeconds = 9999999;
	
    public DriveTrainDriveInchesCommand(double inchesToTravel, double powerMagnitude, int direction) {
    	requires(Robot.DRIVE_TRAIN_SUBSYSTEM);
    	this.totalInchesToTravel = inchesToTravel;
    	this.powerMagnitude = powerMagnitude *= direction;
    	this.direction = direction;
    	this.timeoutTimer = new Timer();
    }
    
    public DriveTrainDriveInchesCommand(double inchesToTravel, double powerMagnitude, int direction, double timeoutSeconds) {
    	requires(Robot.DRIVE_TRAIN_SUBSYSTEM);
    	this.totalInchesToTravel = inchesToTravel;
    	this.powerMagnitude = powerMagnitude *= direction;
    	this.direction = direction;
    	this.timeoutTimer = new Timer();
    	this.timeoutSeconds = timeoutSeconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("RT NIT:" + Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled());
    	driveTrainNetInchesTraveledAtStart = Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled();
    	timeoutTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.fpsTank(powerMagnitude, 0);
    	
    	double driveTrainTotalInchesTraveled = Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled();
    	netInchesTraveledSoFar = driveTrainTotalInchesTraveled - driveTrainNetInchesTraveledAtStart;
    	System.out.println(" driving inches: " + driveTrainTotalInchesTraveled);
    	
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
    	Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
