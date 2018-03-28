package org.usfirst.frc.team1188.robot.commands.commandgroups;

import org.usfirst.frc.team1188.robot.AutonomousCalibrations;
import org.usfirst.frc.team1188.robot.Calibrations;
import org.usfirst.frc.team1188.robot.commands.arm.ArmMoveToMidwayCommand;
import org.usfirst.frc.team1188.robot.commands.arm.ArmRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorMoveToHeightCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractFullyCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelStopCommand;
import org.usfirst.frc.team1188.robot.commands.intake.IntakeWheelsSpitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScoreOnScaleCommandGroup extends CommandGroup {

    public ScoreOnScaleCommandGroup() {
    	addSequential(new ElevatorMoveToHeightCommand(Calibrations.elevatorMidwayEncoderValue));
    	addSequential(new ArmMoveToMidwayCommand());
    	// addSequential(new ElevatorExtendFullyCommand());
    	
    	addSequential(new IntakeWheelsSpitCommand(AutonomousCalibrations.AutonomousScaleIntakeSpitPowerMagnitude));
    	addSequential(new IntakeWheelStopCommand());
    	
    	addSequential(new ArmRetractFullyCommand());
    	addSequential(new ElevatorRetractFullyCommand());
    	
    }
}
