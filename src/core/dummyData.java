/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Appointment;
import Interfaces.ConsultationReport;
import Interfaces.Doctor;
import Interfaces.Nurse;
import Interfaces.Patient;
import static core.DoctorImpl.doctorsFile;
import static core.HospitalImpl.hospitalFile;
import java.io.File;
import java.io.IOException;
import refClasses.Date;
import refClasses.Temperature;
import refClasses.Time;



public class dummyData {
    public static void main(String[] args) throws IOException{
        HospitalImpl.hospitalFile.delete();
        DoctorImpl.doctorsFile.delete();
        NurseImpl.nursesFile.delete();
        PatientImpl.patientsFile.delete();
        ConsultationReportImpl.ConsultationReports.delete();
        AppointmentImpl.appointmentsFile.delete();
        //Hospital Declaration
        HospitalImpl hospital=HospitalImpl.getInstance("National Hospital","www.nh.moh.gov.sa",
                new AddressImpl("King Fahd",3,12546,"Riyadh","KSA"),new PhoneImpl(966,11,3933));
        //Patient Declaration
        Patient patient1=new PatientImpl("P001","Rick","Grimes",new Date(1,2,1970),"123765432",
                new PhoneImpl(966,11,9374),new  SSNImpl("534-13-999",'A'),new Date(1,2,2013),
                new AddressImpl("King Fahd",2,12588,"Riyadh","KSA"));
        Patient patient2=new PatientImpl("P002","Daryl","Dixon",new Date(4,6,1984),"12399943",
                new PhoneImpl(966,11,9532),new SSNImpl("987-52-596",'B'),new Date(3,3,2013),
                new AddressImpl("Olaya St",3,3155,"Riyadh","KSA"));
        //Doctor Declaration
        Doctor doctor1=new DoctorImpl("D001","Dexter","Morgan",new Date(5,4,1980),new Date(1,1,2009),"123765467",
                new PhoneImpl(966,55,5486),new PhoneImpl(966,11,5435),"Heart Surgery",new Time(8,0,0),
                new Time(17,0,0),"Internal Medicine");
        Doctor doctor2=new DoctorImpl("D002","Lucas","Lopez",new Date(1,8,1978),new Date(1,1,2010),"123765457",
                new PhoneImpl(966,55,6985),new PhoneImpl(966,11,369),"Pediatric Medicine",new Time(8,0,0),
                new Time(17,0,0),"Pediatrics");
               doctor1.writeToFile();
               doctor2.writeToFile();
               
        //Nurse Declaration
        Nurse nurse1=new NurseImpl("N0003234","Sarah","Clarkson",new Date(9,8,1990),new Date(1,1,2017),"123765223",
                new PhoneImpl(966,55,8877),new PhoneImpl(966,11,4821),"Emergency Medicine",new Time(6,0,0),
                new Time(18,0,0),doctor1);

                
        //Appointment Declaration with patient & doctor
        Appointment appointment1=new AppointmentImpl("A001",doctor1, patient1,new Date(5,4,2016),new Time(9,0,0),300);
        Appointment appointment2=new AppointmentImpl("A002",doctor2, patient1,new Date(5,6,2016),new Time(11,30,0),250);
        Appointment appointment3=new AppointmentImpl("A003",doctor2, patient2,new Date(3,3,2016),new Time(12,20,0),350);
        
       
        //Print the medical report of a patient
        doctor1.addConsultationReport(patient1, appointment1, "120/80", new Temperature(37,'C'), 180, 100, "coughing", "cough syrup");
       doctor2.addConsultationReport(patient1, appointment2, "118/87", new Temperature(37.5,'C'), 180, 100, "sneezing", "cold medicine");
        doctor1.addConsultationReport(patient2, appointment3, "115/81", new Temperature(37.7,'C'), 170, 95, "fever", "panadol");
        //System.out.println(patient1.getMedicalReport());
        patient1.writeToFile();
        patient2.writeToFile();
        
        
    }
}
