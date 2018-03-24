package org.usfirst.frc.team1188.ravenhardware;

import edu.wpi.first.wpilibj.Encoder;

public class RavenEncoder {
    Encoder encoder;
    
    int cyclesPerRevolution;
    double driveWheelDiameterInches;
    double driveWheelCircumferenceInches;
    
    boolean inverted = false;
    
    public RavenEncoder(Encoder encoder, int cyclesPerRevolution, double driveWheelDiameterInches, boolean inverted) {
        this.encoder = encoder;
        this.cyclesPerRevolution = cyclesPerRevolution;
        this.driveWheelDiameterInches = driveWheelDiameterInches;
        this.inverted = inverted;
        
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
        return netInchesTraveled;
    }
    
    public int getCycles() {
    	int cycles = this.encoder.get();
    	
    	if (inverted) {
    		cycles *= -1;
    	}
    	
    	
    	return cycles;
    }
    
    public void resetEncoder() {
    	this.encoder.reset();
    }
}