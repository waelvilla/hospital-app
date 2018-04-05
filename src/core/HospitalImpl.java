package core;

import Interfaces.Address;
import Interfaces.Appointment;
import Interfaces.ConsultationReport;
import Interfaces.Doctor;
import Interfaces.Hospital;
import Interfaces.Nurse;
import Interfaces.Patient;
import Interfaces.Phone;
import static core.AppointmentImpl.appointmentsFile;
import static core.ConsultationReportImpl.ConsultationReports;
import static core.DoctorImpl.doctors;
import static core.DoctorImpl.doctorsFile;
import static core.NurseImpl.nurseAssgnmentsFile;
import static core.NurseImpl.nursesFile;
import static core.PatientImpl.patientsFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HospitalImpl implements Hospital {

    private String name;
    private String webLink;
    private Address address;
    private Phone phone;
    public static final String[] DEPARTMENTS = {"Internal Medicine", "Pediatrics", "Dentistry", "Emergency", "Laboratory", "Pharmacy"};
    //Creating a Singleton Instance of Hospital
    private static HospitalImpl instance = null;
    public static File hospitalFile = new File("Data Files\\hospital.txt");
    //MAPS OF PERSONS IN THE HOSPITAL  WITH KEY: ID AND VALUE: OBJECT 
    public static Map<String, Doctor> DOCTORS = new TreeMap<String, Doctor>();      // <DoctorID , Doctor Object>
    public static Map<String, Patient> PATIENTS = new TreeMap<String, Patient>();   // <PatientID , Patient Object>
    public static Map<String, Nurse> NURSES = new TreeMap<String, Nurse>();         // <NurseID , Nurse Object>
    public static Map<String, Appointment> APPOINTMENTS=new TreeMap<String,Appointment>(); //<Appointment ID, Appointment Object>
    public static Map<String, ConsultationReport> CONSULTATIONS=new TreeMap<String,ConsultationReport>();
    public static Map<String,ArrayList<Nurse>> nurseAssignments=new TreeMap<String,ArrayList<Nurse>>();    //<DoctorId, Nurse ID's> 
    
    
    private HospitalImpl(String name, String webLink, Address address, Phone phone) {
        setName(name);
        setWebLink(webLink);
        setAddress(address);
        setPhone(phone);
        writeToFile();

    }

    public static HospitalImpl getInstance(String name, String webLink, Address address, Phone phone) {
        if (instance == null) {
            instance = new HospitalImpl(name, webLink, address, phone);
        }
        return instance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getWebLink() {
        return webLink;
    }

    @Override
    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Phone getPhone() {
        return phone;
    }

    @Override
    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String[] getDepartments() {
        return DEPARTMENTS;
    }

    public void writeToFile() {

        try {

            FileWriter fw = new FileWriter(hospitalFile);
            fw.write(toFile() + "\n");
            fw.close();

        } catch (IOException ex) {
            System.out.println("cannot write to hospitalFile.txt");
        }
    }

    

    //TODO READ FILES
    public void loadFiles() {
        DoctorImpl.loadFile();
        NurseImpl.loadFile();
        PatientImpl.loadFile();
        AppointmentImpl.loadFile();
        NurseImpl.loadNurseAssignments();
        
    }

    public void saveRecords() {
        deleteFile(doctorsFile);
        deleteFile(patientsFile);
        deleteFile(nursesFile);
        deleteFile(hospitalFile);     
        deleteFile(ConsultationReports);
        deleteFile(appointmentsFile);
        deleteFile(nurseAssgnmentsFile);
        writeToFile();
        for (Patient p : PATIENTS.values()) {
            p.writeToFile();
        }
        for (Nurse n : NURSES.values()) {
            n.writeToFile();
        }
        for (Doctor d : DOCTORS.values()) {
            d.writeToFile();
        }
        for(Appointment a:APPOINTMENTS.values()){
            a.writeToFile();
        }
        NurseImpl.saveNurseAssignments();
        
        System.out.println("records saved");

    }

    public static void deleteFile(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();
        } catch (IOException ex) {
            throw new IllegalArgumentException("cannot write to"+file.getName()+" file");
        }
    }

    
    @Override
    public String toString() {
        //String name, String webLink, Address address, Phone phone
        return String.format("Hospital Name: %s \nWeblink: %s \n%s \nPhone: %s", getName(), getWebLink(), getAddress(), getPhone());
    }

    public String toFile() {
        return toString();
    }

}
