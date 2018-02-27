package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveFPSCommand;
import org.usfirst.frc.team1188.util.LoggerOverlordLogID;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {	
	public Robot robot;
	Gamepad driveController;
	public RavenTank ravenTank;
	private Solenoid _shiftToLowGearSolenoid = new Solenoid(RobotMap.shiftToLowGearSolenoid);
	private Solenoid _shiftToHighGearSolenoid = new Solenoid(RobotMap.shiftToHighGearSolenoid);
	private Relay _carriageStalledRelay = new Relay(RobotMap.carriageStalledLightRelay);
	private Lighting _carriageStalledLighting = new Lighting(_carriageStalledRelay);
	
	public DriveTrainSubsystem() {
		this.ravenTank = new RavenTank(_shiftToLowGearSolenoid, _shiftToHighGearSolenoid, _carriageStalledLighting);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
     
    	//setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveTrainDriveFPSCommand());
    }
    
    public void periodic() {
    	double elevatorHeightPercentage = Robot.ELEVATOR_SUBSYSTEM.getElevatorHeightPercentage();
    	double powerSubtractor = (1 - Calibrations.DRIVETRAIN_MAXPOWER_AT_MAX_ELEVEATOR_HEIGHT) * elevatorHeightPercentage;
    	double maxPower = Math.min(1, 1 - powerSubtractor);
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.DriveMaxPower, "" + maxPower);
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.ElevatorHeightPercent, "" + elevatorHeightPercentage);
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.DrivePowerSubtractor, "" + powerSubtractor);
    	this.ravenTank.setMaxPower(maxPower);
    	
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.DriveEncoderLeftInches, "" + this.ravenTank.leftEncoder.getNetInchesTraveled() * -1);
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.DriveEncoderRightInches, "" + this.ravenTank.rightEncoder.getNetInchesTraveled());
    	Robot.LOGGER_OVERLORD.log(LoggerOverlordLogID.DriveNetInchesTraveled, "" + this.ravenTank.getNetInchesTraveled());
    	
    }
}

