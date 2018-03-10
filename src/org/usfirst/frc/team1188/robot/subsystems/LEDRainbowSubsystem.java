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
		
		if (Robot.DRIVE_CONTROLLER.getButtonValue(ButtonCode.A)) { 
			led.off(0);
			led.off(1);
			led.off(2);
			led.off(3);
		}
		
		if (Robot.ELEVATOR_SUBSYSTEM.getIsAtExtensionLimit() == true) {
			led.rainbow(0, 0);
			//System.out.println("Lighting UP Lighting UP Lighting UP Lighting UP Lighting UP Lighting UP Lighting UP");
		}
	}
	
	public void setDisabledPattern() {	
		led.breathe(1, 0, 0);
		led.breathe(1, 0, 5);
		led.breathe(1, 0, 2);
		led.breathe(1, 0, 3);
	}
	
	public void setEnabledPattern() {
		led.breathe(5, 0, 0);
		led.breathe(5, 0, 1);
		led.breathe(5, 0, 2);
		led.breathe(5, 0, 3);
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
