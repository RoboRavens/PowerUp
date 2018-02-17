package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.arm.ArmStopCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmSubsystem extends Subsystem {
	TalonSRX leftMotor;
	TalonSRX rightMotor;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ArmSubsystem() {
		this.leftMotor = new TalonSRX(RobotMap.armMotorLeft);
		this.rightMotor = new TalonSRX(RobotMap.armMotorRight);
		this.leftMotor.setInverted(true);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArmStopCommand(this));
    }
    
    public void extend() {
    	this.set(Calibrations.armExtensionPowerMagnitude);
    }
    
    public void extend(double magnitude) {
    	this.set(magnitude);
    }
    
    public void retract() {
    	this.set(-1 * Calibrations.armRetractionPowerMagnitude);
    }
    
    public void retract(double magnitude) {
    	this.set(-1 * magnitude);
    }
    
    public void stop() {
    	this.set(0);
    	// System.out.println("STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.");
    }
    
    private void set(double magnitude) {
    	magnitude = Math.min(magnitude, 1);
    	magnitude = Math.max(magnitude, -1);
    	magnitude *= Calibrations.armMaximumSpeed;
    	
    	this.leftMotor.set(ControlMode.PercentOutput, magnitude);
    	this.rightMotor.set(ControlMode.PercentOutput, magnitude);
    	
    	System.out.println("RMO: " + this.rightMotor.getMotorOutputPercent() + " LMO: " + this.leftMotor.getMotorOutputPercent());
    }
}