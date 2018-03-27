package org.usfirst.frc.team1188.robot.commands.commandgroups;

import org.usfirst.frc.team1188.robot.commands.arm.ArmExtendFullyCommand;
import org.usfirst.frc.team1188.robot.commands.elevator.ElevatorRetractFullyCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowerElevatorThenExtendArmCommandGroup extends CommandGroup {

    public LowerElevatorThenExtendArmCommandGroup() {
       addSequential(new ElevatorRetractFullyCommand());
       addSequential(new ArmExtendFullyCommand());
    }
}
