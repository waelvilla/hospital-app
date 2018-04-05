/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Appointment;
import Interfaces.Doctor;
import Interfaces.Hospital;
import Interfaces.Nurse;
import Interfaces.Patient;
import static core.HospitalImpl.DOCTORS;
import static core.HospitalImpl.NURSES;
import static core.HospitalImpl.PATIENTS;
import static core.HospitalImpl.nurseAssignments;
import static core.PatientImpl.patientsFile;
import static core.UserInterface.fromString;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import refClasses.Date;
import refClasses.Temperature;
import refClasses.Time;

/**
 *
 * @author Wael
 */
public class test {

    public static void main(String[] args) {
        Hospital h=HospitalImpl.getInstance("Name", "web", new AddressImpl(), new PhoneImpl());
        h.loadFiles();
            
        System.out.println(DOCTORS);
  
    
        
    
    }
}
