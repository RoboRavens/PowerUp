package org.usfirst.frc.team1188.robot.commands.arm;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmHoldPositionCommand extends Command {
  
    
    public ArmHoldPositionCommand() {
    	requires(Robot.ARM_SUBSYSTEM);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	// Set the target position to be whatever the elevator's position is when the command begins.
    	System.out.println("ArmHoldPositionCommand init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// System.out.println("Holding arm at " + Calibrations.armHoldPositionPowerMagnitude + " power.");
    	// The goal of this command is to send a very small amount of power to the arm motors
    	// to fight against gravity - NOT to move the arm, at all. 
    	Robot.ARM_SUBSYSTEM.holdPosition();
    }

	protected boolean isFinished() {
		// This command is never finished; once it is called, the only way it stops is
		// if another command overrides it.
		return false;
	}

}
