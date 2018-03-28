package org.usfirst.frc.team1188.gamepad;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperationPanel2 {
	Joystick joystick;
	
	public OperationPanel2(int port) {
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
		case ARMEXTEND:
			buttonNumber = 1;
			break;
		case ARMMIDRANGE:
			buttonNumber = 2;
			break;
		case ARMRETRACT:
			buttonNumber = 3;
			break;
		case INTAKEOVERRIDE:
			buttonNumber = 4;
			break;
		case INTAKEDROP:
			buttonNumber = 5;
			break;
		case INTAKESPIT:
			buttonNumber = 6;
			break;
		case RUNINTAKE:
			buttonNumber = 7;
			break;
		default:
			throw new IllegalArgumentException("Invalid Button Code");
		}
		
		return buttonNumber;
	}
}
