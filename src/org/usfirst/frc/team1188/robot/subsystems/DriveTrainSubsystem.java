package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveFPSCommand;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

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
    	//double elevatorHeightPercentage = Robot.DRIVE_CONTROLLER.getAxis(AxisCode.LEFTSTICKX);
    	double powerSubtractor = (1 - Calibrations.DRIVETRAIN_MAXPOWER_AT_MAX_ELEVEATOR_HEIGHT) * elevatorHeightPercentage;
    	double maxPower = Math.min(1, 1 - powerSubtractor);
    	this.ravenTank.setMaxPower(maxPower);
    	
    	double slewRateDifference = Calibrations.slewRateMaximum - Calibrations.slewRateMinimum;
    	double slewRateSubtraction = slewRateDifference * elevatorHeightPercentage;
    	double slewRate = Calibrations.slewRateMaximum - slewRateSubtraction;
    	slewRate = Math.max(Calibrations.slewRateMinimum, slewRate);
    	slewRate = Math.min(Calibrations.slewRateMaximum, slewRate);
    	this.ravenTank.setSlewRate(slewRate);
    	
    	PCDashboardDiagnostics.SubsystemNumber("DriveTrain", "PowerMax", maxPower);
    	PCDashboardDiagnostics.SubsystemNumber("DriveTrain", "EncoderLeftInchesTraveled", this.ravenTank.leftRavenEncoder.getNetInchesTraveled());
    	PCDashboardDiagnostics.SubsystemNumber("DriveTrain", "EncoderRightInchesTraveled", this.ravenTank.rightRavenEncoder.getNetInchesTraveled());
    	PCDashboardDiagnostics.SubsystemNumber("DriveTrain", "EncoderAvgInchesTraveled", Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled());
    	PCDashboardDiagnostics.SubsystemNumber("DriveTrain", "SlewRate", slewRate);
    }
}

