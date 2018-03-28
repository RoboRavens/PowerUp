package org.usfirst.frc.team1188.gamepad;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperationPanel {
	Joystick joystick;
	
	public OperationPanel(int port) {
		joystick = new Joystick(port);
	}
	
	public boolean getButtonValue(ButtonCode button) {	
		return joystick.getRawButton(getButtonNumber(button));
	}
	
	public JoystickButton getButton(ButtonCode button) {
		return new JoystickButton(joystick, getButtonNumber(button));
	}
	
	public int getButtonNumber(ButtonCode button) {
		int buttonNumber;
		
		switch (button) {
		case ELEVATORDOUBLEOVERRIDEDOWN:
			buttonNumber = 1;
			break;
		case ELEVATORDOUBLEOVERRIDEUP:
			buttonNumber = 2;
			break;
		case ARMDOUBLEOVERRIDERETRACT:
			buttonNumber = 3;
			break;
		case ARMDOUBLEOVERRIDEEXTEND:
			buttonNumber = 4;
			break;
		case ELEVATORMANUALOVERRIDEDOWN:
			buttonNumber = 5;
			break;
		case ELEVATORMANUALOVERRIDEUP:
			buttonNumber = 6;
			break;
		case ARMMANUALOVERRIDERETRACT:
			buttonNumber = 7;
			break;
		case ARMMANUALOVERRIDEEXTEND:
			buttonNumber = 8;
			break;
		case ELEVATOREXTEND:
			buttonNumber = 9;
			break;
		case ELEVATORMIDRANGE:
			buttonNumber = 10;
			break;
		case ELEVATORRETRACT:
			buttonNumber = 11;
			break;
		case ELEVATORSWITCHHEIGHT:
			buttonNumber = 12;
			break;
		default:
			throw new IllegalArgumentException("Invalid Button Code");
		}
		
		return buttonNumber;
	}
}
