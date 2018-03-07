package org.usfirst.frc.team1188.util;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class PCDashboardDiagnostics {
	private static final NetworkTable _table =
		      NetworkTableInstance.getDefault().getTable("Diagnostics");
	
	/**
	 * This function is used by subsystems to add data to the Variables tab of the PC Dashboard. It will appear under the name "Diagnostics".
	 * @param subsystemName The name of the subsystem excluding the word "Subsystem"
	 * @param name The name of the data to be included in diagnostics
	 * @param data The data to be included in diagnostics
	 */
	public static void SubsystemData(String subsystemName, String name, String data) {
		_table.getEntry("Subsystem/" + subsystemName + "/" + name).setString(data);
	}
	
	public static void AdHocData(String name, String data) {
		_table.getEntry("AdHoc/" + name).setString(data);
	}
}
