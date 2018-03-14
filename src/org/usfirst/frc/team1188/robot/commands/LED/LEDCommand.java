package org.usfirst.frc.team1188.robot.commands.LED;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LEDCommand extends Command{
	
	public LEDCommand() {
		requires(Robot.LED_SUBSYSTEM);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
 // Called repeatedly when this Command is scheduled to run
    public void execute() {     
        Robot.LED_SUBSYSTEM.run();
        // System.out.println("running command@@@@@@@@@@@@@@@");
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
