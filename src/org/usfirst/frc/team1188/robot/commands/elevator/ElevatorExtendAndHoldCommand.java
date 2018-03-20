package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.gamepad.Gamepad;
import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorExtendAndHoldCommand extends CommandGroup {
	ElevatorSubsystem elevator;
	Robot robot;
    Gamepad operationController;
    Encoder encoder;
    int targetPosition;
	
    public ElevatorExtendAndHoldCommand(ElevatorSubsystem elevator, Gamepad driveController, Encoder encoder) {
        	requires(elevator);
        	this.elevator = elevator;
        	this.operationController = driveController;
        	this.encoder = encoder;
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new ElevatorExtendWhileHeldCommand());
    	addSequential(new ElevatorHoldPositionCommand());
    	
    }
}
