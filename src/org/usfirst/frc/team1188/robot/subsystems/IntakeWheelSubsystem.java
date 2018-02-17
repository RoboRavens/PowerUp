package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelPullIdleCommand;

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
        setDefaultCommand(new IntakeWheelPullIdleCommand(this));
    }
    
    public void pull() {
		this.set(Calibrations.intakeWheelPullPowerMagnitude); 
	}
    
    public void push() {
		this.set(-1 * Calibrations.intakeWheelPushPowerMagnitude); 
	}
    
    public void idle() {
    	this.set(0.1);
    }
    
    public void stop() {
		this.set(0);
	}
    
    private void set(double magnitude) {
    	intakeMotorLeft.set(ControlMode.PercentOutput, magnitude);
    	intakeMotorRight.set(ControlMode.PercentOutput, magnitude);
    }


	
}

