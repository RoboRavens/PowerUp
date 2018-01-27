package org.usfirst.frc.team1188.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick driveController;
	Joystick operationController;
	Joystick buttonPanel;
	
	public OI(Joystick driveController, Joystick operationController, Joystick buttonPanel) {
		this.driveController = driveController;
		this.operationController = operationController;
		this.buttonPanel = buttonPanel;
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
}
