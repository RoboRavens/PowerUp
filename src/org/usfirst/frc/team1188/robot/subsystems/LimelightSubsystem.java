package org.usfirst.frc.team1188.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LimelightSubsystem extends Subsystem {
	static double Limit = 0.00;
	static double A = 0;
	static double B = 0;
	static double C = 0;
	static double D = 0;
	edu.wpi.first.networktables.NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");
	NetworkTableEntry tv = table.getEntry("tv");
	NetworkTableEntry ledMode = table.getEntry("ledMode");

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void periodic() {
    	//PCDashboardDiagnostics.SubsystemNumber("Limelight", "TargetArea", this.targetArea());
    //	PCDashboardDiagnostics.SubsystemNumber("Limelight", "angleOffHorizontal", this.angleOffHorizontal());
    //	PCDashboardDiagnostics.SubsystemNumber("Limelight", "angleOffVertical", this.angleOffVertical());
    //	PCDashboardDiagnostics.SubsystemNumber("Limelight", "hasTarget", this.hasTarget());
    	table.getEntry("ledMode").setNumber(1);
   	
    }
	
    	
	public double targetArea() {
		return ta.getDouble(0);
	}
	public double hasTarget() {
		return tv.getDouble(0);
	}
	public double angleOffHorizontal() {
		
		return tx.getDouble(0);
	}
	public double angleOffVertical() {
		return ty.getDouble(0);
	}

	
	public static void limeLightDiagnostics() {
		
	}
	public static void limeLightDetect() {


		//if( A*B^(targetArea+C) +D < Limit) {
			
	//}
	}
}

