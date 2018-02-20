package org.usfirst.frc.team188.robot.commands.LED;

import org.usfirst.frc.team1188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LEDRainbowSetDisabledPatternCommand extends Command{
	
	public LEDRainbowSetDisabledPatternCommand() {
		requires(Robot.LED_RAINBOW_SUBSYSTEM);
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Starting disabled pattern");
    }
    
 // Called repeatedly when this Command is scheduled to run
    protected void execute() {     

    	System.out.println("Executing disabled pattern");
        Robot.LED_RAINBOW_SUBSYSTEM.setDisabledPattern();
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
