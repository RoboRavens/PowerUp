package org.usfirst.frc.team1188.robot.commands.elevator;

import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorExtendQuarterWithPIDCommand extends Command {
	ElevatorSubsystem elevator;

    public ElevatorExtendQuarterWithPIDCommand(ElevatorSubsystem elevator) {
    	requires(Robot.ELEVATOR_SUBSYSTEM);
    	this.elevator = elevator;

        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevator.setMotorsPID(13000);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ELEVATOR_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
