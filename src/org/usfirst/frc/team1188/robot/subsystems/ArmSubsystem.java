package org.usfirst.frc.team1188.robot.subsystems;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.RobotMap;
import org.usfirst.frc.team1188.robot.commands.arm.ArmStopCommand;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmSubsystem extends Subsystem {
	TalonSRX armMotor;
	DigitalInput extensionLimitSwitch;
	DigitalInput retractionLimitSwitch;
	
	public ArmSubsystem() {
		this.armMotor = new TalonSRX(RobotMap.armMotor);
		this.retractionLimitSwitch = new DigitalInput(RobotMap.armRetractionLimitSwitch);
		this.extensionLimitSwitch = new DigitalInput(RobotMap.armExtensionLimitSwitch);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ArmStopCommand(this));
    }
    
    public void periodic() {
    	PCDashboardDiagnostics.SubsystemNumber("Arm", "Encoder", this.getEncoderPosition());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitEncoderExtension", this.isEncoderAtExtensionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitEncoderRetraction", this.isEncoderAtRetractionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitSwitchExtension", this.getExtensionLimitSwitchValue());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitSwitchRetraction", this.getRetractionLimitSwitchValue());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitFinalExtension", this.getIsAtExtensionLimit());
    	PCDashboardDiagnostics.SubsystemBoolean("Arm", "LimitFinalRetraction", this.getIsAtRetractionLimit());
    }
    
	public boolean getExtensionLimitSwitchValue() {
		boolean extensionLimitSwitchValue = false;
		
		extensionLimitSwitchValue = !extensionLimitSwitch.get();
		
		return extensionLimitSwitchValue;
	}
	
	public boolean getRetractionLimitSwitchValue() {
		boolean retractionLimitSwitchValue = false;
		
		retractionLimitSwitchValue = !retractionLimitSwitch.get();
		
		return retractionLimitSwitchValue;
	}
	
    /*
    public boolean isAtBottomLimit() {
    	return this.getEncoderPosition() <= Calibrations.armEncoderValueAtBottom + Calibrations.ARM_ENCODER_BUFFER;
    }
    
    public boolean isAtTopLimit() {
    	return this.getEncoderPosition() >= Calibrations.armEncoderValueAtTop - Calibrations.ARM_ENCODER_BUFFER;
    }
    */
    
    
    public boolean getIsAtRetractionLimit() {
    	boolean isAtLimit = false;
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	encoderLimit = this.isEncoderAtRetractionLimit();
    	
    	if (this.getRetractionLimitSwitchValue() == true) {
    		switchLimit = true;
    	}
    	
    	if (encoderLimit == false && switchLimit == true) {
    		this.resetEncodersToTop();
    	}
    	
    	isAtLimit = Robot.OVERRIDE_SYSTEM_ELEVATOR_RETRACT.getIsAtLimit(encoderLimit, switchLimit);
    	
    	return isAtLimit;
    }
    
    public void expectArmToBeAtBottom() {
    	boolean isAtLimitSwitch = this.getExtensionLimitSwitchValue();
    	boolean isEncoderWithinRange = isEncoderAtExtensionLimit();
    	
    	if (isEncoderWithinRange == false && isAtLimitSwitch == true) {
    		this.resetEncodersToBottom();
    	}
    }
    
    public void expectArmToBeAtTop() {
    	boolean isAtLimitSwitch = this.getRetractionLimitSwitchValue();
    	boolean isEncoderWithinRange = isEncoderAtRetractionLimit();
    	
    	if (isEncoderWithinRange == false && isAtLimitSwitch == true) {
    		this.resetEncodersToTop();
    	}
    }
    
    public boolean isEncoderAtExtensionLimit() {
    	boolean encoderLimit = false;
    	if (this.getEncoderPosition() <= Calibrations.armEncoderValueExtended + Calibrations.ARM_ENCODER_BUFFER) {
    		encoderLimit = true;
    	}
    	return encoderLimit;
    }
    
    public boolean isEncoderAtRetractionLimit() {
    	boolean encoderLimit = false;
    	if (this.getEncoderPosition() <= Calibrations.armEncoderValueRetracted + Calibrations.ARM_ENCODER_BUFFER) {
    		encoderLimit = true;
    	}
    	return encoderLimit;
    }
    
    public boolean getIsAtExtensionLimit() {
    	boolean isAtLimit = false;
    	boolean encoderLimit = false;
    	boolean switchLimit = false;
    	
    	encoderLimit = this.isEncoderAtExtensionLimit();
    	
    	if (this.getExtensionLimitSwitchValue() == true) {
    		switchLimit = true;
    	}
    	
    	if (encoderLimit == false && switchLimit == true) {
    		this.resetEncodersToBottom();
    	}
    	
    	isAtLimit = Robot.OVERRIDE_SYSTEM_ELEVATOR_EXTEND.getIsAtLimit(encoderLimit, switchLimit);
    	
    	return isAtLimit;
    }
    
    
    public int getEncoderPosition() {
    	return (armMotor.getSelectedSensorPosition(0));
    }
    
    public void resetEncodersToTop() {
    	this.armMotor.setSelectedSensorPosition(Calibrations.armEncoderValueRetracted, 0, 0);
    	
    }
    
    public void resetEncodersToBottom() {
    	this.armMotor.setSelectedSensorPosition(Calibrations.armEncoderValueExtended, 0, 0);
    }
    
    /*
    public void resetEncodersTopLimitSwitch() {
    	boolean retractionLimitSwitchValue = false;
    	if(retractionLimitSwitchValue = retractionLimitSwitch.get()) {
    		this.resetEncodersToTop();
    	}
    }
    
    public void resetEncodersBottomLimitSwitch() {
    	boolean extensionLimitSwitchValue = false;
    	if(extensionLimitSwitchValue = retractionLimitSwitch.get()) {
    		this.resetEncodersToBottom();
    	}
    }
    */
    
    public void extend() {
    	this.extend(Calibrations.armExtensionPowerMagnitude);
    }
    
    public void extend(double magnitude) {
    	if (this.getIsAtExtensionLimit()) {
    		this.stop();
    	}
    	else {
        	this.set(-1 * magnitude);	
    	}
    }
    
    public void retract() {
    	this.retract(Calibrations.armRetractionPowerMagnitude);
    }
    
    public void retract(double magnitude) {
    	if (this.getIsAtRetractionLimit()) {
    		this.stop();
    	}
    	else {
    		this.set(magnitude);
    	}
    }
    
    public void stop() {
    	this.set(0);
    	// System.out.println("STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.STOPPING ARM.");
    }
    
    
    private void set(double magnitude) {
    	magnitude = Math.min(magnitude, 1);
    	magnitude = Math.max(magnitude, -1);
    	magnitude *= Calibrations.armMaximumSpeed;
    	
    	PCDashboardDiagnostics.SubsystemNumber("Arm", "MotorOutputPercent", magnitude);
    	this.armMotor.set(ControlMode.PercentOutput, magnitude);
    	
    	//System.out.println("RMO: " + this.SSSMotor.getMotorOutputPercent() + " LMO: " + this.leftMotor.getMotorOutputPercent());
    }
}