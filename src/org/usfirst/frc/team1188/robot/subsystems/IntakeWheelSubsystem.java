package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelStopCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeWheelSubsystem extends Subsystem {
	TalonSRX intakeMotorRight;
	TalonSRX intakeMotorLeft;
		
	public IntakeWheelSubsystem() {
		this.intakeMotorLeft = new TalonSRX(RobotMap.intakeMotorLeft);
		this.intakeMotorRight = new TalonSRX(RobotMap.intakeMotorRight);
		this.intakeMotorLeft.setInverted(true);
	}
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       setDefaultCommand(new IntakeWheelStopCommand(this));
    }
    
    public void pull() {
		this.set(Calibrations.intakeWheelPullPowerMagnitude); 
	}
    
    public void pull(double magnitude) {
		this.set(magnitude); 
	}
    
    public void push() {
		this.set(-1 * Calibrations.intakeWheelPushSoftPowerMagnitude); 
	}
    
    public void push(double magnitude) {
		this.set(-1 * magnitude); 
	}
    
    public void idle() {
    	this.set(0.1);
    }
    
    public void stop() {
		this.set(0);
	}
    
    private void set(double magnitude) {
    	// System.out.println("Setting intake motors: " + magnitude);
    	intakeMotorLeft.set(ControlMode.PercentOutput, -1 * magnitude);
    	intakeMotorRight.set(ControlMode.PercentOutput, magnitude);
    }


	
}

