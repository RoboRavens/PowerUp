package org.usfirst.frc.team188.robot.commands.LED;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LEDRainbowCommand extends Command{
	
	public LEDRainbowCommand() {
		requires(Robot.LED_RAINBOW_SUBSYSTEM);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
 // Called repeatedly when this Command is scheduled to run
    protected void execute() {     
        Robot.LED_RAINBOW_SUBSYSTEM.run();
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
