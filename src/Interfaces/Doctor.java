/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.ArrayList;
import refClasses.Temperature;

/**
 *
 * @author Wael
 */
public interface Doctor extends Staff{
    public abstract String getDoctorID();
    public abstract void setDoctorID(String doctorID);
    
    public abstract String getDepartment();
    public abstract void setDepartment(String department);
    
    public abstract ArrayList<Nurse> getAssignedNurses();
    public abstract void setAssignedNurses(ArrayList<Nurse> nurses);
    public abstract void assignNurse(Nurse n);
    public void addConsultationReport(Patient patient,Appointment appointment, String bloodPressure, Temperature temperature,double height,
            double weight, String symptoms, String medicine);
    
    public abstract ArrayList<ConsultationReport> getConsultations();
    public abstract String toFile();
    public abstract void writeToFile();
    
}
