package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.ravenhardware.Lighting;
import org.usfirst.frc.team1188.ravenhardware.RavenTank;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.drivetrain.DriveTrainDriveFPSCommand;

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
    
}

