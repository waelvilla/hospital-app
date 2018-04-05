package Interfaces;

import core.DoctorImpl;
import refClasses.Temperature;

/*

    private Temperature temperature; 
    private String bloodPressure;
    private double height;
    private double weight;
    private String symptoms;
    private String medicine;  
 */
public interface ConsultationReport {
    
    public abstract Appointment getAppointment();
    public abstract void setAppointment(Appointment appointment);
    
    public abstract Patient getPatient();
    public abstract void setPatient(Patient patient);
    
    public abstract Temperature getTemperature();
    public abstract void setTemperature(Temperature temperature);
      
    public abstract String getBloodPressure();
    public abstract void setBloodPressure(String bloodPressure);
    
    public abstract double getHeight();
    public abstract void setHeight( double height);
    
    public abstract double getWeight();
    public abstract void setWeight(double weight);
    
    public abstract String getSymptoms();
    public abstract void setSymptoms(String symptoms);
    
    public abstract String getMedicine();
    public abstract void setMedicine(String medicine);
 
}
