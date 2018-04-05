package Interfaces;

import core.ConsultationReportImpl;
import java.util.ArrayList;
import refClasses.Date;
import refClasses.Temperature;


public interface Patient extends Person{
    public abstract SSN getSSN();
    public abstract void setSSN(SSN SSN);
    
    public abstract String getHospitalID();
    public abstract void setHospitalID(String hospitalID);
    
    public abstract Date getFirstConsultationDate();
    public abstract void setFirstConsultationDate(Date firstConsult);
    
    public abstract Address getAddress();
    public abstract void setAddress(Address address);
    
  /* public abstract String getSymptoms();
   public abstract void setSymptoms(String symptoms);
   
   public abstract String getMedicine();
   public abstract void setMedicine(String medicine);
    
    public abstract String getBloodPressure();
    public abstract void setBloodPressure(String bloodPressure);
    
    public abstract Temperature getTemperature();
    public abstract void setTemperature(Temperature temperature);
    
    public abstract double getHeight();
    public abstract void setHeight(double height);
    
    public abstract double getWeight();
    public abstract void setWeight(double weight);
    */
    public abstract Appointment getAppointment() ;
    public abstract void setAppointment(Appointment appointment);
    
    public ArrayList<ConsultationReport> getMedicalReport();
    public abstract void setMedicalReport(ArrayList<ConsultationReport> medicalReport);
    
    public abstract String toFile();
    public abstract void writeToFile();
}
