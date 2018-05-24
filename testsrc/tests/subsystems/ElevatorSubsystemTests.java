package tests.subsystems;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

	@Test
	void test(@Mock IMotorController elevatorMotor,
			@Mock IEncoder encoder,
			@Mock IBufferedDigitalInput bottomLimitSwitch,
			@Mock IBufferedDigitalInput topLimitSwitch,
			@Mock ITimer safetyTimer) {
		
		ElevatorSubsystem2 sub = new ElevatorSubsystem2(elevatorMotor, encoder, bottomLimitSwitch, topLimitSwitch, safetyTimer);
	}

}
