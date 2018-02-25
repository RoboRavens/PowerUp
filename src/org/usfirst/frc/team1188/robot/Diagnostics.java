package org.usfirst.frc.team1188.robot;
public class Diagnostics {
	
	public void outputAutonomousDiagnostics() {
		//System.out.print("LEncoder: " + round(robot.driveTrain.ravenTank.leftEncoder.getNetInchesTraveled(), 2) + " REncoder: " + round(robot.driveTrain.ravenTank.rightEncoder.getNetInchesTraveled(), 2));
		System.out.println("DT total IT: " + Math.round(Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled()));
		//System.out.println(" Current heading: " + robot.driveTrain.ravenTank.getCurrentHeading());
		// System.out.println("Shooter RPM: " + Math.round(robot.fuelShooter.shooterMotorLead.getSpeed() / 2));
		
	}
	
	public void outputTeleopDiagnostics() {
		// System.out.print("DT total IT: " + Math.round(Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled()));
		// System.out.println("Navx.getAngle: " + navX.getAngle());
		// System.out.println("REncoder: " + round(robot.driveTrain.ravenTank.rightEncoder.getNetInchesTraveled(), 2) + " LEncoder: " + round(robot.driveTrain.ravenTank.leftEncoder.getNetInchesTraveled(), 2));
		
		// System.out.println("Slew rate: " + robot.driveTrain.ravenTank.getSlewRate());

		// robot.driveTrain.ravenTank.outputEncoderTravels();
		
		
		// System.out.println("DT total IT: " + Math.round(Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled()));
		 // System.out.println(" Current heading: " + robot.driveTrain.ravenTank.getCurrentHeading());
		
		// System.out.println("Navx get angle: " + driveTrain.ravenTank.getGyroAngle());
		
		//System.out.println("Extension limit: " + gearCarriage.getIsAtExensionLimit() + " retraction limit: " + gearCarriage.getIsAtRetractionLimit());
		
		// System.out.println("Total revolutions: " + robot.fuelShooter.shooterMotorLead.getPosition());
		
		// System.out.print("EncVel: " + robot.fuelShooter.shooterMotorLead.getEncVelocity());
		
		// System.out.print("Encoder RPM: " + robot.fuelShooter.shooterMotorLead.getSpeed());
		
		// System.out.println("Shooter RPM: " + Math.round(robot.fuelShooter.shooterMotorLead.getSpeed() / 2));
		
		// System.out.println("Talon 6 output: " + robot.lightInput1.get());
		
		
		// System.out.println(" RPM: " + robot.fuelShooter.shooterMotorLead.getSpeed() / 2);

		// fuelPump.getRpm();
		// System.out.println(Calibrations.DriveTrainCollisionJerkThreshold);
		
		// robot.driveTrain.ravenTank.outputJerk();
		// robot.driveTrain.ravenTank.outputHighestJerk();
		
		// System.out.println();
	}
	
	public double round(double value, int digits) {
		double tempValue = value;
		
		for (int i = 0; i < digits; i++) {
			tempValue *= 10;
		}
		
		tempValue = Math.round(tempValue);
		
		for (int i = 0; i < digits; i++) {
			tempValue /= 10;
		}
		
		return tempValue;
	}

	public void outputDisabledDiagnostics() {
		// System.out.println("-410 mod 360: " + -410 % 360);
		// robot.driveTrain.ravenTank.getStaticGyroAdjustment();
		//System.out.println("REncoder: " + round(Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.rightEncoder.getNetInchesTraveled(), 2) + " LEncoder: " + round(robot.driveTrain.ravenTank.leftEncoder.getNetInchesTraveled(), 2));
		// System.out.println("RPM: " + robot.fuelShooter.shooterMotorLead.getSpeed() * 2);
		// System.out.println("Shooter RPM: " + Math.round(robot.fuelShooter.shooterMotorLead.getSpeed() / 2));
		//System.out.println("DT total IT: " + Math.round(Robot.DRIVE_TRAIN_SUBSYSTEM.ravenTank.getNetInchesTraveled()));
		
	}
	
	
}