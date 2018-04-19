package org.usfirst.frc.team1188.autonomous;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousCrossAutoLineCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousDoNothingCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftScaleLeftPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftScaleRightPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftSwitchLeftPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousLeftSwitchMiddlePositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousRightScaleLeftPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousRightScaleRightPositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousRightSwitchMiddlePositionCommand;
import org.usfirst.frc.team1188.robot.commands.autonomousmodes.AutonomousRightSwitchRightPositionCommand;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousCommandProvider {
	public Class<? extends Command> getAutonomousCommand(String autoFromDashboard, String positionFromDashboard, String gameData) {
		// Three possibilities:
		// Drive forward and score in switch,
		// move left to score in switch,
		// and move right to score in switch.
		
		Class<? extends Command> autonomousCommand = AutonomousCrossAutoLineCommand.class;

		switch (autoFromDashboard.toUpperCase()) {
			case AutonomousCalibrations.DoNothing:
				autonomousCommand = AutonomousDoNothingCommand.class;
				break;
			case AutonomousCalibrations.CrossLine:
				autonomousCommand = AutonomousCrossAutoLineCommand.class;
				break;
			case AutonomousCalibrations.Switch:
				autonomousCommand = this.getAutonomousSwitchCommand(autoFromDashboard, positionFromDashboard, gameData);
				break;
			case AutonomousCalibrations.Scale:
				autonomousCommand = this.getAutonomousScaleCommand(autoFromDashboard, positionFromDashboard, gameData);
				break;
			case AutonomousCalibrations.FlexScale:
				autonomousCommand = this.getAutonomousFlexScaleCommand(autoFromDashboard, positionFromDashboard, gameData);
				break;
			case AutonomousCalibrations.FlexSwitch:
				autonomousCommand = this.getAutonomousFlexSwitchCommand(autoFromDashboard, positionFromDashboard, gameData);
				break;
					
		}
	
		return autonomousCommand;
	}
	
	private Class<? extends Command> getAutonomousSwitchCommand(String autoFromDashboard, String positionFromDashboard, String gameData) {
		Class<? extends Command> switchCommand = AutonomousCrossAutoLineCommand.class;
		
		if (gameData.length() > 0) {
			if (gameData.charAt(0) == 'L') {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Left switch, left position. Drive forward and then score on the switch.
					switchCommand = AutonomousLeftSwitchLeftPositionCommand.class;
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Left switch, middle position. Drive diagonally and then score on the switch.
					switchCommand = AutonomousLeftSwitchMiddlePositionCommand.class;
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Left switch, right position. UGUUU
					switchCommand = AutonomousCrossAutoLineCommand.class;
				}
			}
			else {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Right switch, left position. Ugh.
					switchCommand = AutonomousCrossAutoLineCommand.class;
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Right switch, middle position. Drive diagonally and then score on the switch.
					switchCommand = AutonomousRightSwitchMiddlePositionCommand.class;
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Right switch, right position. Easy.
					switchCommand = AutonomousRightSwitchRightPositionCommand.class;
				}
			}
		}
		
		return switchCommand;
	}
	
	private Class<? extends Command> getAutonomousScaleCommand(String autoFromDashboard, String positionFromDashboard, String gameData) {
		
		Class<? extends Command> scaleCommand = AutonomousCrossAutoLineCommand.class;
		
		if (gameData.length() > 0) {
			if (gameData.charAt(1) == 'L') {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Left switch, left position. Drive forward and then score on the switch.
					scaleCommand = AutonomousLeftScaleLeftPositionCommand.class;
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Left switch, middle position. Drive diagonally and then score on the switch.
					// scaleCommand = new AutonomousScoreLeftSwitchMiddlePositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Left switch, right position. UGUUU
					scaleCommand = AutonomousLeftScaleRightPositionCommand.class;
				}
			}
			else {
				if (positionFromDashboard.toUpperCase().equals("LEFT")) {
					// Right switch, left position. Ugh.
					scaleCommand = AutonomousRightScaleLeftPositionCommand.class;
				}
				else if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
					// Right switch, middle position. Drive diagonally and then score on the switch.
					// scaleCommand = new AutonomousScoreRightSwitchMiddlePositionCommand();
				}
				else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
					// Right switch, right position. Easy.
					scaleCommand = AutonomousRightScaleRightPositionCommand.class;
				}
			}
		}
		
		return scaleCommand;
	}
	
	private Class<? extends Command> getAutonomousFlexScaleCommand(String autoFromDashboard, String positionFromDashboard, String gameData) {
		Class<? extends Command> flexCommand = AutonomousCrossAutoLineCommand.class;
		
		if (gameData.length() <= 1) {
			return flexCommand;
		}
		
		if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
			return getAutonomousScaleCommand(autoFromDashboard, positionFromDashboard, gameData);
		}
		
		if (positionFromDashboard.toUpperCase().equals("LEFT")) {
			if (gameData.charAt(1) == 'L') {
				// robot left position, scale left position
				flexCommand = getAutonomousScaleCommand(autoFromDashboard, positionFromDashboard, gameData);
			} else if (gameData.charAt(0) == 'L') {
				// robot left position, switch left position
				flexCommand = getAutonomousSwitchCommand(autoFromDashboard, positionFromDashboard, gameData);
			}
		} else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
			if (gameData.charAt(1) == 'R') {
				// robot right position, scale right position
				flexCommand = getAutonomousScaleCommand(autoFromDashboard, positionFromDashboard, gameData);
			} else if (gameData.charAt(0) == 'R') {
				// robot right position, switch right position
				flexCommand = getAutonomousSwitchCommand(autoFromDashboard, positionFromDashboard, gameData);
			}
		}
		
		return flexCommand;
	}
	
	private Class<? extends Command> getAutonomousFlexSwitchCommand(String autoFromDashboard, String positionFromDashboard, String gameData) {
		Class<? extends Command> flexCommand = AutonomousCrossAutoLineCommand.class;
		
		if (gameData.length() <= 1) {
			return flexCommand;
		}
		
		if (positionFromDashboard.toUpperCase().equals("MIDDLE")) {
			return getAutonomousSwitchCommand(autoFromDashboard, positionFromDashboard, gameData);
		}
		
		if (positionFromDashboard.toUpperCase().equals("LEFT")) {
			if (gameData.charAt(0) == 'L') {
				// robot left position, switch left position
				flexCommand = getAutonomousSwitchCommand(autoFromDashboard, positionFromDashboard, gameData);
			} else if (gameData.charAt(1) == 'L') {
				// robot left position, scale left position
				flexCommand = getAutonomousScaleCommand(autoFromDashboard, positionFromDashboard, gameData);
			}
		} else if (positionFromDashboard.toUpperCase().equals("RIGHT")) {
			if (gameData.charAt(0) == 'R') {
				flexCommand = getAutonomousSwitchCommand(autoFromDashboard, positionFromDashboard, gameData);
				// TODO
			} else if (gameData.charAt(1) == 'R') {
				// robot right position, scale right position
				flexCommand = getAutonomousScaleCommand(autoFromDashboard, positionFromDashboard, gameData);
			}
		}
		
		return flexCommand;
	}
}
