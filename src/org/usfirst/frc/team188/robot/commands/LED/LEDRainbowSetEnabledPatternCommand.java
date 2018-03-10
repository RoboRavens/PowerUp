package org.usfirst.frc.team188.robot.commands.LED;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LEDRainbowSetEnabledPatternCommand extends Command{
	
	public LEDRainbowSetEnabledPatternCommand() {
		requires(Robot.LED_SUBSYSTEM);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    }
    
 // Called repeatedly when this Command is scheduled to run
    protected void execute() {     
        Robot.LED_SUBSYSTEM.setEnabledPattern();
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
