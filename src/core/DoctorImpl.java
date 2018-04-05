package core;

import Interfaces.Appointment;
import Interfaces.ConsultationReport;
import Interfaces.Doctor;
import Interfaces.Nurse;
import Interfaces.Patient;
import static core.HospitalImpl.CONSULTATIONS;
import static core.HospitalImpl.DOCTORS;
import static core.HospitalImpl.nurseAssignments;
import static core.UserInterface.fromString;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import refClasses.Date;
import refClasses.Temperature;
import refClasses.Time;

/**
 *
 * @author Wael
 */
public class DoctorImpl extends StaffImpl implements Doctor {

    private String DoctorID;
    private  String department;
    private ArrayList<ConsultationReport> consultations = new ArrayList<ConsultationReport>();
    private  ArrayList<Nurse> assignedNurses=new ArrayList<Nurse>();
     static File doctorsFile = new File("Data Files\\Doctors.txt");
    static File doctors=new File("docs.txt");

    //no Nurses Constructor
    public DoctorImpl(String DoctorID, String firstName, String LastName, Date DOB, Date hireDate, String NationalID, PhoneImpl mobilePhone, PhoneImpl officePhone,
            String speciality, Time StartWorkingTime, Time EndWorkingTime, String department) {
        super(firstName, LastName, DOB, hireDate, NationalID, mobilePhone, officePhone, speciality, StartWorkingTime, EndWorkingTime);
        setDoctorID(DoctorID);
        setDepartment(department);
        HospitalImpl.DOCTORS.put(DoctorID, this);
        //writeToFile();
    }

    //with nurses
    public DoctorImpl(String DoctorID, String firstName, String LastName, Date DOB, Date hireDate, String NationalID, PhoneImpl mobilePhone, PhoneImpl officePhone,
            String speciality, Time StartWorkingTime, Time EndWorkingTime, String department, ArrayList<Nurse> nurses) {
        super(firstName, LastName, DOB, hireDate, NationalID, mobilePhone, officePhone, speciality, StartWorkingTime, EndWorkingTime);
        setDoctorID(DoctorID);
        setDepartment(department);
        setAssignedNurses(nurses);
        HospitalImpl.DOCTORS.put(DoctorID, this);
        // writeToFile();
    }

    //Read from File
    public DoctorImpl(String DoctorID) {
        readFile(DoctorID);
        HospitalImpl.DOCTORS.put(DoctorID, this);
    }

    public DoctorImpl() {
    }

    @Override
    public ArrayList<Nurse> getAssignedNurses() {
        return assignedNurses;
    }

    @Override
    public void setAssignedNurses(ArrayList<Nurse> assignedNurses) {
         this.assignedNurses=assignedNurses;
        for(Nurse n: assignedNurses){
            n.setDoctor(this);
        }
        nurseAssignments.put(DoctorID, assignedNurses);
        this.assignedNurses = assignedNurses;
    }
    @Override
    public void assignNurse(Nurse n){
        n.setDoctor(this);
        this.getAssignedNurses().add(n);
        nurseAssignments.put(DoctorID, assignedNurses);
        
    }
    public void removeNurse(Nurse n){
        n.setDoctor(null);
        this.getAssignedNurses().remove(n);
        nurseAssignments.put(DoctorID, assignedNurses);
    }
    @Override
    public String getDoctorID() {
        return DoctorID;
    }

    @Override
    public void setDoctorID(String doctorID) {
        this.DoctorID = doctorID;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(String department) {
        boolean depNotExist = true;
        if(this.department!=null)
            throw new IllegalArgumentException("Department cannot be changed");
        for (String dep : HospitalImpl.DEPARTMENTS) {
            if (dep.equals(department)) {
                this.department = department;
                depNotExist = false;
                break;
            }
        }
        if (depNotExist == true) {
            System.out.println("Enter dep again");
            
        }
    }

    public void addConsultationReport(Patient patient, Appointment appointment, String bloodPressure,
            Temperature temperature, double height, double weight, String symptoms, String medicine) {
        ConsultationReport consultReport = new ConsultationReportImpl(patient, appointment, bloodPressure, temperature, height, weight,
                symptoms, medicine);
        patient.getMedicalReport().add(consultReport);
        consultations.add(consultReport);
        if (appointment == null) {
            throw new IllegalArgumentException("This patient has not made an appointment");
        }
    }

    public void writeToFile() {

        try {

            FileWriter fw = new FileWriter(doctorsFile,true);
            fw.write(toFile() + "\n");
            fw.close();

        } catch (IOException ex) {
            System.out.println("cannot write to Doctors.txt");
        }
    }

    public ArrayList<ConsultationReport> getConsultations() {
        return consultations;
    }

    public void setConsultations(ArrayList<ConsultationReport> consultations) {
        this.consultations = consultations;
    }

    public void readFile(String docID) {
        try {
            Scanner doctorFileInput = new Scanner(doctorsFile);
            String nextLine;
            Boolean notFound = true;
            while (doctorFileInput.hasNext()) {
                nextLine = doctorFileInput.nextLine();
                if (nextLine.contains(docID)) {
                    notFound = false;
                    setDoctorID(nextLine.split(" ")[1]);
                    String[] dnameSplit = doctorFileInput.nextLine().split(" ");
                    setFirstName(dnameSplit[1]);

                    setLastName(dnameSplit[2]);

                    String[] dobTemp = doctorFileInput.nextLine().split(" ");
                    String[] docDOB = dobTemp[3].split("/");
                    setDOB(new Date((Integer) (Integer.parseInt(docDOB[0])), (Integer) (Integer.parseInt(docDOB[1])), (Integer) (Integer.parseInt(docDOB[2]))));

                    setNationalID(doctorFileInput.nextLine().split(" ")[1]);
                    String[] mPhone = doctorFileInput.nextLine().split(" ");
                    setMobilePhone(fromString(mPhone[2]));
                            
                    String[] hDateTemp = doctorFileInput.nextLine().split(" ");
                    String[] docHireDate = hDateTemp[2].split("/");
                    setHireDate(new Date((Integer) (Integer.parseInt(docHireDate[0])), (Integer) (Integer.parseInt(docHireDate[1])), (Integer) (Integer.parseInt(docHireDate[2]))));

                    String[] oPhone = doctorFileInput.nextLine().split(" ");
                    setOfficePhone(fromString(oPhone[2]));
                            
                    setSpeciality(doctorFileInput.nextLine().split(":")[1]);

                    String[] SWTime = doctorFileInput.nextLine().split(":");
                    setStartWorkingTime(new Time((Integer) (Integer.parseInt(SWTime[1])), (Integer) (Integer.parseInt(SWTime[2])), (Integer) (Integer.parseInt(SWTime[3]))));

                    String[] EWTime = doctorFileInput.nextLine().split(":");
                    setEndWorkingTime(new Time((Integer) (Integer.parseInt(EWTime[1])), (Integer) (Integer.parseInt(EWTime[2])), (Integer) (Integer.parseInt(EWTime[3]))));
                     setDepartment(doctorFileInput.nextLine().split(":")[1]);
                    String nursesLine=doctorFileInput.nextLine();
                    if(nursesLine.contains("N0")){
                        String[] nurses=nursesLine.split(" ");
                        
                    }
                    

                    break;
                }

            }
            if (notFound) {
                throw new IllegalArgumentException("no doctor with id " + docID + " was found");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }

    }

    public static void loadFile() {
        //TODO: load from file
        try {
            Scanner doctorFileInput = new Scanner(doctorsFile);
            String nextLine;
            Boolean notFound = true;
            while (doctorFileInput.hasNext()) {
                nextLine = doctorFileInput.nextLine();
                if (nextLine.contains("DoctorID")) {
                    notFound = false;
                    Doctor doctor=new DoctorImpl();
                    String doctorID=nextLine.split(" ")[1];
                    doctor.setDoctorID(doctorID);
                    String[] dnameSplit = doctorFileInput.nextLine().split(" ");
                    doctor.setFirstName(dnameSplit[1]);

                    doctor.setLastName(dnameSplit[2]);

                    String[] dobTemp = doctorFileInput.nextLine().split(" ");
                    String[] docDOB = dobTemp[3].split("/");
                    doctor.setDOB(new Date((Integer) (Integer.parseInt(docDOB[0])),
                            (Integer) (Integer.parseInt(docDOB[1])), (Integer) (Integer.parseInt(docDOB[2]))));

                    doctor.setNationalID(doctorFileInput.nextLine().split(" ")[1]);
                    String[] mPhone = doctorFileInput.nextLine().split(" ");
                    doctor.setMobilePhone(fromString(mPhone[2]));
                            
                    String[] hDateTemp = doctorFileInput.nextLine().split(" ");
                    String[] docHireDate = hDateTemp[2].split("/");
                    doctor.setHireDate(new Date((Integer) (Integer.parseInt(docHireDate[0])),
                            (Integer) (Integer.parseInt(docHireDate[1])), (Integer) (Integer.parseInt(docHireDate[2]))));

                    String[] oPhone = doctorFileInput.nextLine().split(" ");
                    doctor.setOfficePhone(fromString(oPhone[2]));
                            
                    doctor.setSpeciality(doctorFileInput.nextLine().split(":")[1]);

                    String[] SWTime = doctorFileInput.nextLine().split(":");
                    doctor.setStartWorkingTime(new Time((Integer) (Integer.parseInt(SWTime[1])),
                            (Integer) (Integer.parseInt(SWTime[2])), (Integer) (Integer.parseInt(SWTime[3]))));

                    String[] EWTime = doctorFileInput.nextLine().split(":");
                    doctor.setEndWorkingTime(new Time((Integer) (Integer.parseInt(EWTime[1])),
                            (Integer) (Integer.parseInt(EWTime[2])), (Integer) (Integer.parseInt(EWTime[3]))));
                    
                    doctor.setDepartment(doctorFileInput.nextLine().split(":")[1]);
                    String nursesLine=doctorFileInput.nextLine();
                    if(nursesLine.contains("N0")){
                        String[] nurses=nursesLine.split(" ");
                        
                    }
                    
                    DOCTORS.put(doctorID, doctor);
                }

            }
            if (notFound) {
                throw new IllegalArgumentException("no doctor was found");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }

    }

    @Override
    public String toString() {
        return String.format("Dr. %s %s", super.getFirstName(), super.getLastName());
    }

    @Override
    public String toFile() {
        return String.format("DoctorID: %s \n%s\nDepartment:%s\n", getDoctorID(), super.toFile(), getDepartment());
    }

}
