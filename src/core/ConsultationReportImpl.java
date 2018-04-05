/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Appointment;
import Interfaces.ConsultationReport;
import Interfaces.Patient;
import static core.NurseImpl.nursesFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import refClasses.Temperature;

/**
 *
 * @author 215210704
 */
public class ConsultationReportImpl implements ConsultationReport{
    
    private Patient patient;
    private Appointment appointment;
    private Temperature temperature; 
    private String bloodPressure;
    private double height;
    private double weight;
    private String symptoms;
    private String medicine;  
    public static File ConsultationReports=new File("Data Files\\ConsultationReports.txt");
    

    public ConsultationReportImpl(Patient patient, Appointment appointment, String bloodPressure, Temperature temperature,
            double height,double weight, String symptoms, String medicine){
       
        setPatient(patient);
        setAppointment(appointment);
        setBloodPressure(bloodPressure);
        setTemperature(temperature);
        setHeight(height);
        setWeight(weight);
        setSymptoms(symptoms);
        setMedicine(medicine);
        writeToFile();
    }
    public ConsultationReportImpl(){
        
    }
    //without symptoms and medicine
    public ConsultationReportImpl(PatientImpl patient, Appointment appointment, String bloodPressure, Temperature temperature,
            double height,double weight){
        
        setPatient(patient);
        setAppointment(appointment);
        setBloodPressure(bloodPressure);
        setTemperature(temperature);
        setHeight(height);
        setWeight(weight);
        writeToFile();
    }    
    
    @Override
    public Patient getPatient() {
        return patient;
    }

    
    public void setPatient(Patient patient) {
        this.patient=patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

  public void writeToFile(){
        
        try {
            
            FileWriter fw=new FileWriter(ConsultationReports,true);
            fw.write(toFile()+"\n");
            fw.close();
            
        } catch (IOException ex) {
            System.out.println("cannot write to ConsultationReports.txt");
        }
    }
    public void readFile(File file,String appointmentID){
        try {
            Scanner s=new Scanner(file);
            String nextLine;
            Boolean notFound=true; 
            while(s.hasNext()){
                nextLine=s.nextLine();
                if(nextLine.contains(appointmentID)){
                    
                    
                }
            }
            
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("cannot read consultation report");
        }
            





    }  
    public static void loadFile(){
        //TODO: load from file
    }
    @Override
    public String toString() {
        return String.format("Consultation Report:\n"
                + "%sBlood pressure: %s\nTemperature: %s\nHeight: %s \nWeight: %s\nSymptoms: %s\nMedicine: %s\n"
                ,getAppointment().toString(),getBloodPressure(),getTemperature(),
                getHeight(),getWeight(),getSymptoms(),getMedicine());
    }
    public String toFile(){
        return toString();
    }

}
