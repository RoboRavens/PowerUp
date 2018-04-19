package tests.autonomous;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.usfirst.frc.team1188.autonomous.AutonomousCommandProvider;
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

class AutonomousCommandProviderTests {
	
	private AutonomousCommandProvider _atc = new AutonomousCommandProvider();
	
	@Test
	void getAutonomousCommand_WhenDoNothingAuto_ReturnsCorrectResult() {
		assertEquals(AutonomousDoNothingCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.DoNothing, "", ""));
	}
	
	@Test
	void getAutonomousCommand_WhenCrossLineAuto_ReturnsCorrectResult() {
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.CrossLine, "", ""));
	}
	
	@Test
	void getAutonomousCommand_SWITCH_ReturnsCorrectResult() {
		assertEquals(AutonomousLeftSwitchLeftPositionCommand.class,   _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "LEFT","LLL"));
		assertEquals(AutonomousLeftSwitchLeftPositionCommand.class,   _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "LEFT","LRL"));
		assertEquals(AutonomousCrossAutoLineCommand.class,            _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "LEFT","RLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class,            _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "LEFT","RRL"));
		
		assertEquals(AutonomousLeftSwitchMiddlePositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "MIDDLE","LLL"));
		assertEquals(AutonomousLeftSwitchMiddlePositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "MIDDLE","LRL"));
		assertEquals(AutonomousRightSwitchMiddlePositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "MIDDLE","RLL"));
		assertEquals(AutonomousRightSwitchMiddlePositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "MIDDLE","RRL"));
		
		assertEquals(AutonomousCrossAutoLineCommand.class,            _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "RIGHT","LLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class,            _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "RIGHT","LRL"));
		assertEquals(AutonomousRightSwitchRightPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "RIGHT","RLL"));
		assertEquals(AutonomousRightSwitchRightPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Switch, "RIGHT","RRL"));
	}
	
	@Test
	void getAutonomousCommand_SCALE_ReturnsCorrectResult() {
		assertEquals(AutonomousLeftScaleLeftPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "LEFT","LLL"));
		assertEquals(AutonomousRightScaleLeftPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "LEFT","LRL"));
		assertEquals(AutonomousLeftScaleLeftPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "LEFT","RLL"));
		assertEquals(AutonomousRightScaleLeftPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "LEFT","RRL"));
		
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "MIDDLE","LLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "MIDDLE","LRL"));
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "MIDDLE","RLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "MIDDLE","RRL"));
		
		assertEquals(AutonomousLeftScaleRightPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "RIGHT","LLL"));
		assertEquals(AutonomousRightScaleRightPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "RIGHT","LRL"));
		assertEquals(AutonomousLeftScaleRightPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "RIGHT","RLL"));
		assertEquals(AutonomousRightScaleRightPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.Scale, "RIGHT","RRL"));
	}
	
	@Test
	void getAutonomousCommand_FLEXSCALE_ReturnsCorrectResult() {
		assertEquals(AutonomousLeftScaleLeftPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "LEFT","LLL"));
		assertEquals(AutonomousLeftSwitchLeftPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "LEFT","LRL"));
		assertEquals(AutonomousLeftScaleLeftPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "LEFT","RLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class,          _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "LEFT","RRL"));
		
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "MIDDLE","LLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "MIDDLE","LRL"));
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "MIDDLE","RLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "MIDDLE","RRL"));
		
		assertEquals(AutonomousCrossAutoLineCommand.class,            _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "RIGHT","LLL"));
		assertEquals(AutonomousRightScaleRightPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "RIGHT","LRL"));
		assertEquals(AutonomousRightSwitchRightPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "RIGHT","RLL"));
		assertEquals(AutonomousRightScaleRightPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.FlexScale, "RIGHT","RRL"));
	}
	
	@Test
	void getAutonomousCommand_FLEXSWITCH_ReturnsCorrectResult() {
		assertEquals(AutonomousLeftSwitchLeftPositionCommand.class,   _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "LEFT","LLL"));
		assertEquals(AutonomousLeftSwitchLeftPositionCommand.class,   _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "LEFT","LRL"));
		assertEquals(AutonomousLeftScaleLeftPositionCommand.class,    _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "LEFT","RLL"));
		assertEquals(AutonomousCrossAutoLineCommand.class,            _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "LEFT","RRL"));
		
		assertEquals(AutonomousLeftSwitchMiddlePositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "MIDDLE","LLL"));
		assertEquals(AutonomousLeftSwitchMiddlePositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "MIDDLE","LRL"));
		assertEquals(AutonomousRightSwitchMiddlePositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "MIDDLE","RLL"));
		assertEquals(AutonomousRightSwitchMiddlePositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "MIDDLE","RRL"));
		
		assertEquals(AutonomousCrossAutoLineCommand.class,            _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "RIGHT","LLL"));
		assertEquals(AutonomousRightScaleRightPositionCommand.class,  _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "RIGHT","LRL"));
		assertEquals(AutonomousRightSwitchRightPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "RIGHT","RLL"));
		assertEquals(AutonomousRightSwitchRightPositionCommand.class, _atc.getAutonomousCommand(AutonomousCalibrations.FlexSwitch, "RIGHT","RRL"));
	}
}
