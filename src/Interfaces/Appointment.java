package Interfaces;

import refClasses.Date;
import refClasses.Time;

/*
A patient takes an appointment for a consultation with a doctor.
An appointment has a doctor, patient, a appointment date and an 
appointment time and a price of consultation. The appointment time 
must be valid to be inside the working hours of the doctor. 
If the patient has a social security number with class A the cost of 
consultation will be only 20% of the real cost. If he has a social
security number with a class B, the cost is just set to zero. 
 */
public interface Appointment {
    public abstract String getAppointmentID();
    public abstract void setAppointmentID(String appointmentID);
    public abstract Doctor getDoctor();
    public abstract void setDoctor(Doctor doctor);
    
    public abstract Patient getPatient();
    public abstract void setPatient(Patient patient);
    
    public abstract Date getAppointmentDate();
    public abstract void setAppointmentDate(Date AppointmentDate);
    
    public abstract Time getAppointmentTime();
    public abstract void setAppointmentTime(Time AppointmentTime);
    
    public abstract double getAppointmentPrice();
    public abstract void setAppointmentPrice(double price);
    public abstract void writeToFile();
    public static  boolean exists(String patientID, String doctorID){
        return false;
    }
}
