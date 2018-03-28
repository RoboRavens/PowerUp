package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.ravenhardware.BufferedDigitalInput;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelStopCommand;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeWheelSubsystem extends Subsystem {
	TalonSRX intakeMotorRight;
	TalonSRX intakeMotorLeft;
	BufferedDigitalInput intakeSensor;
	private Timer _hasCubeDurationTimer = new Timer();
		
	public IntakeWheelSubsystem() {
		this.intakeMotorLeft = new TalonSRX(RobotMap.intakeMotorLeft);
		this.intakeMotorRight = new TalonSRX(RobotMap.intakeMotorRight);
		this.intakeSensor = new BufferedDigitalInput(RobotMap.intakeSensor);
		_hasCubeDurationTimer.start();
	}
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       setDefaultCommand(new IntakeWheelStopCommand());
    }
    
    public void pull() {
		this.pull(Calibrations.intakeWheelSuckPowerMagnitude); 
	}
    
    public void pull(double magnitude) { 
		//if(this.hasCubePullTimeout() == true) {
		//	this.stop();
		//} else {
			this.set(-1 * magnitude);
		//}
	}
    
    public void push() {
		this.push(Calibrations.intakeWheelDropPowerMagnitude); 
	}
    
    public void push(double magnitude) {
		this.set(magnitude); 
	}
    
    public void idle() {
    	this.set(0.1);
    }
    
    public void stop() {
		this.set(0);
	}
    
    private void set(double magnitude) {
    	// System.out.println("Setting intake motors: " + magnitude);
    	double leftMotorMagnitude = -1 * magnitude;
    	double rightMotorMagnitude = magnitude;
    	
    	PCDashboardDiagnostics.SubsystemNumber("IntakeWheel", "MotorLeftOutputPercent", leftMotorMagnitude);
    	PCDashboardDiagnostics.SubsystemNumber("IntakeWheel", "MotorRightOutputPercent", rightMotorMagnitude);
    	intakeMotorLeft.set(ControlMode.PercentOutput, leftMotorMagnitude);
    	intakeMotorRight.set(ControlMode.PercentOutput, rightMotorMagnitude);
    }
    
    public boolean hasCube() {
    	boolean otherLimit = false;
    	boolean hasCube = intakeSensor.get() == false;
    	
    	return Robot.OVERRIDE_SYSTEM_INTAKE.getIsAtLimit(hasCube, otherLimit);
    }
    
    public void periodic() {
    	intakeSensor.maintainState();
    	
    	PCDashboardDiagnostics.SubsystemBoolean("IntakeWheel", "HasCube", this.hasCube());
    	PCDashboardDiagnostics.SubsystemBoolean("IntakeWheel", "HasCubeSensorRaw", intakeSensor.get());
    	
    	if (this.hasCube() == false) {
    		_hasCubeDurationTimer.reset();
    	}
    	
    	if (this.hasCube() == false) {
    		Robot.HAS_CUBE_LEDS_RELAY.set(Value.kForward);
    	}
    	else {
    		Robot.HAS_CUBE_LEDS_RELAY.set(Value.kOff);
    	}
    }
    
    private boolean hasCubePullTimeout() {
    	return _hasCubeDurationTimer.get() > .5;
    }
}

