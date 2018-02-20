package org.usfirst.frc.team1188.robot.subsystems;

import org.team401.LightLink;
import org.usfirst.frc.team1188.gamepad.ButtonCode;
import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team188.robot.commands.LED.LEDRainbowCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDRainbowSubsystem extends Subsystem{
	
	public static final Gamepad CONTROLLER = Robot.OPERATION_CONTROLLER;
	public LightLink led;
	
	public LEDRainbowSubsystem() {
		this.led = new LightLink();
	}
	
	public void run() {
		
		if (CONTROLLER.getButtonValue(ButtonCode.A)) { 
			led.breathe(4);
		}
	}
	
	public void setDisabledPattern() {	
		led.breathe(1);
	}
	
	public void setEnabledPattern() {
		led.breathe(5);
	}
	
	public void setAutonomousPattern() {	
		led.breathe(2);
	}

	@Override
	protected void initDefaultCommand() {
		 // Set the default command for a subsystem here.
		setDefaultCommand(new LEDRainbowCommand());
		
	}

}
