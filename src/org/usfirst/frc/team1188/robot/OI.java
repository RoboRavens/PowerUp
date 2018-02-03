package org.usfirst.frc.team1188.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick driveController;
	Joystick operationController;
	Joystick buttonPanel;
	
	// Elevator
		public Button elevatorExtendButton;
		public Button elevatorRetractButton;
	
	public OI(Joystick driveController, Joystick operationController, Joystick buttonPanel) {
		this.driveController = driveController;
		this.operationController = operationController;
		this.buttonPanel = buttonPanel;
		initializeButtons();
	}
	
	protected void initializeButtons() {
		elevatorExtendButton = new JoystickButton(operationController, ControlsMap.elevatorExtendButton);
		elevatorRetractButton = new JoystickButton(operationController, ControlsMap.elevatorRetractButton);
	}
	
	// Shifters
	public boolean getDriveShiftLowButton() {
		boolean shiftLow = driveController.getRawButton(ControlsMap.driveShiftToLowGearButton) || operationController.getRawButton(ControlsMap.operationShiftToLowGearButton);  
		
		return shiftLow; 
	}
	
	public boolean getDriveShiftHighButton() {
		return driveController.getRawButton(ControlsMap.driveShiftToHighGearButton);
	}
	
	public boolean getDriveCutPowerMode() {
		return driveController.getRawAxis(ControlsMap.driveCutPowerAxis) > .25;
	}
	
	public boolean getGearCarriageExtendButton() {
		return operationController.getRawButton(ControlsMap.elevatorExtendButton);
	}
	
	public boolean getGearCarriageRetractButton() {
		return operationController.getRawButton(ControlsMap.elevatorRetractButton);
	}
}
