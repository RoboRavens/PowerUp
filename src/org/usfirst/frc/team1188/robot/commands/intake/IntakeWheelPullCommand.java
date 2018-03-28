package org.usfirst.frc.team1188.robot.commands.intake;

import org.usfirst.frc.team1188.robot.Robot;
import org.usfirst.frc.team1188.robot.commands.commandgroups.LowerElevatorThenExtendArmCommandGroup;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWheelPullCommand extends Command {

    public IntakeWheelPullCommand () {
         requires(Robot.INTAKE_WHEEL_SUBSYSTEM); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.INTAKE_WHEEL_SUBSYSTEM.hasCube() == false) {
    		// new LowerElevatorThenExtendArmCommandGroup().start();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.INTAKE_WHEEL_SUBSYSTEM.pull();
    	System.out.println("IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();IntakeWheelSubsystem.pull();");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Robot.INTAKE_WHEEL_SUBSYSTEM.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.INTAKE_WHEEL_SUBSYSTEM.stop();
    }
}
