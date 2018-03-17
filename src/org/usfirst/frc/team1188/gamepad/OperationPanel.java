package org.usfirst.frc.team1188.gamepad;

import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.util.PCDashboardDiagnostics;

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
	
	public boolean getSwitchIsFlipped(AxisCode axis) {
		boolean isFlipped = false;
		double axisValue = this.getAxis(axis);
		
		if (axisValue >= Calibrations.AXIS_IS_PRESSED_VALUE) {
			isFlipped = true;
		}
		
		return isFlipped;
	}
	
	public double getAxis(AxisCode axis) {
		double axisValue;
		// TODO: Fix and find values for switches
		switch (axis) {
			case ARMDOUBLEOVERRIDEDOWN:
				axisValue = joystick.getRawAxis(0);
				break;
			case ARMDOUBLEOVERRIDEUP:
				axisValue = joystick.getRawAxis(1);
				break;
			case ELEVATORDOUBLEOVERRIDEDOWN:
				axisValue = joystick.getRawAxis(2);
				break;
			case ELEVATORDOUBLEOVERRIDEUP:
				axisValue = joystick.getRawAxis(3);
				break;
			default:
				axisValue = 0;
				break;
		}
		return axisValue;
	}
	
	public int getButtonNumber(ButtonCode button) {
		int buttonNumber;
		
		switch (button) {
		case RUNINTAKE:
			buttonNumber = 1;
			break;
		case INTAKEOVERRIDE:
			buttonNumber = 2;
			break;
		case INTAKESPIT:
			buttonNumber = 3;
			break;
		case INTAKEDROP:
			buttonNumber = 4;
			break;
		case ARMMANUALOVERRIDEUP:
			buttonNumber = 5;
			break;
		case ARMMANUALOVERRIDEDOWN:
			buttonNumber = 6;
			break;
		case ARMEXTEND:
			buttonNumber = 7;
			break;
		case ARMRETRACT:
			buttonNumber = 8;
			break;
		case ELEVATORMANUALOVERRIDEUP:
			buttonNumber = 9;
			break;
		case ELEVATORMANUALOVERRIDEDOWN:
			buttonNumber = 10;
			break;
		case ELEVATOREXTEND:
			buttonNumber = 11;
			break;
		case ELEVATORMIDRANGE:
			buttonNumber = 12;
			break;
		case ELEVATORRETRACT:
			buttonNumber = 13;
			break;
		default:
			throw new IllegalArgumentException("Invalid Button Code");
		}
		
		return buttonNumber;
	}
}
