package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveFPSCommand;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
	
	public Robot robot;
	Gamepad driveController;
	public RavenTank ravenTank;
	
	public DriveTrainSubsystem(Robot robot, Gamepad driveController) {
		initializeDriveTrain(robot, driveController);
		this.ravenTank = new RavenTank(robot);
	}
	
	public DriveTrainSubsystem(Robot robot, Gamepad driveController, Solenoid lowGearSolenoid, Solenoid highGearSolenoid, Lighting shiftedToLowGearLighting) {
		initializeDriveTrain(robot, driveController);
		this.ravenTank = new RavenTank(robot, lowGearSolenoid, highGearSolenoid, shiftedToLowGearLighting);
	}

	private void initializeDriveTrain(Robot robot, Gamepad driveController) {
		this.robot = robot;
		this.driveController = driveController;
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
     
    	//setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveTrainDriveFPSCommand(this, driveController));
    }
    
}

