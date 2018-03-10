package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.ravenhardware.RavenLighting;
import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveFPSCommand;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

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
	
	public DriveTrainSubsystem() {
		this.ravenTank = new RavenTank(_shiftToLowGearSolenoid, _shiftToHighGearSolenoid);
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
    	this.ravenTank.setMaxPower(maxPower);
    	
    	PCDashboardDiagnostics.SubsystemData("DriveTrain", "PowerMax", "" + maxPower);
    	PCDashboardDiagnostics.SubsystemData("DriveTrain", "EncoderLeftInchesTraveled", "" + this.ravenTank.leftEncoder.getNetInchesTraveled() * -1);
    	PCDashboardDiagnostics.SubsystemData("DriveTrain", "EncoderRightInchesTraveled", "" + this.ravenTank.rightEncoder.getNetInchesTraveled());
    	PCDashboardDiagnostics.SubsystemData("DriveTrain", "EncoderAvgInchesTraveled", "" + this.ravenTank.rightEncoder.getNetInchesTraveled());
    }
}

