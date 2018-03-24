package org.usfirst.frc.team1188.ravenhardware;

import edu.wpi.first.wpilibj.Encoder;

public class RavenEncoder {
    Encoder encoder;
    
    int cyclesPerRevolution;
    double driveWheelDiameterInches;
    double driveWheelCircumferenceInches;
    
    boolean inverted = false;
    
    public RavenEncoder(Encoder encoder, int cyclesPerRevolution, double driveWheelDiameterInches) {
        this.encoder = encoder;
        this.cyclesPerRevolution = cyclesPerRevolution;
        this.driveWheelDiameterInches = driveWheelDiameterInches;
        
        this.driveWheelCircumferenceInches = Math.PI * driveWheelDiameterInches;
    }
    
    public double getNetRevolutions() {
    	double netRevolutions = (double) encoder.get() / cyclesPerRevolution;
    	
    	if (inverted) {
    		netRevolutions *= -1;
    	}
    	
        return netRevolutions;
    }
    
    public double getNetInchesTraveled() {
        double netRevolutions = getNetRevolutions();
        double netInchesTraveled = netRevolutions * driveWheelCircumferenceInches;
       
        // I added the *2 here 
        return netInchesTraveled * 2;
    }
    
    public int getCycles() {
    	int cycles = this.encoder.get();
    	
    	if (inverted) {
    		cycles *= -1;
    	}
    	
    	
    	return cycles;
    }
    
    public void setInverted(boolean inverted) {
    	this.inverted = inverted;
    }
    
    public void resetEncoder() {
    	this.encoder.reset();
    }
}