package tests.subsystems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.usfirst.frc.team1188.ravenhardware.IBufferedDigitalInput;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem2;
import org.usfirst.frc.team1188.wpiwrappers.IEncoder;
import org.usfirst.frc.team1188.wpiwrappers.ITimer;

import com.ctre.phoenix.motorcontrol.IMotorController;

// http://www.baeldung.com/mockito-junit-5-extension

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ElevatorSubsystemTests {
	
	
	private IMotorController _elevatorMotor;
	private IEncoder _encoder;
	private IBufferedDigitalInput _bottomLimitSwitch;
	private IBufferedDigitalInput _topLimitSwitch;
	private ITimer _safetyTimer;
	
	private ElevatorSubsystem2 _subsystem;
	
	@BeforeEach
	void reset(@Mock IMotorController elevatorMotor,
			@Mock IEncoder encoder,
			@Mock IBufferedDigitalInput bottomLimitSwitch,
			@Mock IBufferedDigitalInput topLimitSwitch,
			@Mock ITimer safetyTimer) {
		
		_elevatorMotor = elevatorMotor;
		_encoder = encoder;
		_bottomLimitSwitch = bottomLimitSwitch;
		_topLimitSwitch = topLimitSwitch;
		_safetyTimer = safetyTimer;
		
		_subsystem = new ElevatorSubsystem2(_elevatorMotor, _encoder, _bottomLimitSwitch, _topLimitSwitch, _safetyTimer);
	}
	
	@Test
	void getElevatorPosition_WhenValueKnown_ReturnsValue() {
		int value = Mockito.anyInt();
		Mockito.when(_elevatorMotor.getSelectedSensorPosition(0)).thenReturn(value);
		assertEquals(value, _elevatorMotor.getSelectedSensorPosition(0), "Should simply return the sensor value at position 0.");
	}
	
	@Test
	void isEncoderAtRetractionLimit_WhenPastRetractionLimit_ReturnsTrue() {
		Mockito.when(_elevatorMotor.getSelectedSensorPosition(0)).thenReturn(-2000);
		assertTrue(_subsystem.isEncoderAtRetractionLimit());
	}
	
	@Test
	void isEncoderAtRetractionLimit_WhenBeforeRetractionLimit_ReturnsFalse() {
		Mockito.when(_elevatorMotor.getSelectedSensorPosition(0)).thenReturn(2000);
		assertFalse(_subsystem.isEncoderAtRetractionLimit());
	}

	@Test
	void getBottomLimitSwitchValue_WhenValueKnown_ReturnsValue() {
		Mockito.when(_bottomLimitSwitch.get()).thenReturn(true);
		assertFalse(_subsystem.getBottomLimitSwitchValue(), "Value should be opposite of switch");
	}
}
