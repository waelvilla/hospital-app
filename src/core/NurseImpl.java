/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Doctor;
import Interfaces.Nurse;
import Interfaces.Phone;
import static core.HospitalImpl.DOCTORS;
import static core.HospitalImpl.NURSES;
import static core.HospitalImpl.nurseAssignments;
import static core.UserInterface.fromString;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import refClasses.Date;
import refClasses.Time;

/**
 *
 * @author Wael
 */
public class NurseImpl extends StaffImpl implements Nurse {

    private Doctor doctor;
    private String nurseID;
    public static File nursesFile = new File("Data Files\\Nurses.txt");
    public static File nurseAssgnmentsFile = new File("Data Files\\nurseAssignments.txt");

    //no Doctor Constructor
    public NurseImpl(String nurseID, String firstName, String LastName, Date DOB, Date hireDate, String NationalID, Phone mobilePhone,
            Phone officePhone, String speciality, Time StartWorkingTime, Time EndWorkingTime) {
        super(firstName, LastName, DOB, hireDate, NationalID, mobilePhone, officePhone, speciality, StartWorkingTime, EndWorkingTime);
        setNurseID(nurseID);
        writeToFile();
        HospitalImpl.NURSES.put(nurseID, this);
    }

    public NurseImpl(String nurseID, String firstName, String LastName, Date DOB, Date hireDate, String NationalID, Phone mobilePhone,
            Phone officePhone, String speciality, Time StartWorkingTime, Time EndWorkingTime, Doctor doctor) {
        super(firstName, LastName, DOB, hireDate, NationalID, mobilePhone, officePhone, speciality, StartWorkingTime, EndWorkingTime);
        setDoctor(doctor);
        setNurseID(nurseID);
        writeToFile();
        HospitalImpl.NURSES.put(nurseID, this);
    }

    public NurseImpl() {

    }

    public String getNurseID() {
        return nurseID;
    }

    public void setNurseID(String nurseID) {
        this.nurseID = nurseID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (this.doctor != null) {
            throw new IllegalArgumentException("this nurse has already been assigned to doctor " + this.doctor);
        } else {
            this.doctor = doctor;

        }
    }

    public void writeToFile() {

        try {

            FileWriter fw = new FileWriter(nursesFile, true);
            fw.write(toFile() + "\n");
            fw.close();

        } catch (IOException ex) {
            System.out.println("cannot write to Nurses.txt");
        }
    }

    public static void loadFile() {
        try {
            Scanner s = new Scanner(nursesFile);
            String nextLine;
            Boolean notFound = true;
            while (s.hasNext()) {
                nextLine = s.nextLine();
                if (nextLine.contains("NurseID")) {
                    notFound = false;
                    Nurse nurse = new NurseImpl();
                    String nurseID = nextLine.split(" ")[1];
                    nurse.setNurseID(nurseID);
                    String[] nameSplit = s.nextLine().split(" ");
                    nurse.setFirstName(nameSplit[1]);
                    nurse.setLastName(nameSplit[2]);
                    String[] dobTemp = s.nextLine().split(" ");
                    String[] docDOB = dobTemp[3].split("/");
                    nurse.setDOB(new Date((Integer) (Integer.parseInt(docDOB[0])), (Integer) (Integer.parseInt(docDOB[1])), (Integer) (Integer.parseInt(docDOB[2]))));

                    nurse.setNationalID(s.nextLine().split(" ")[1]);
                    String[] mPhone = s.nextLine().split(" ");
                    nurse.setMobilePhone(fromString(mPhone[2]));

                    String[] hDateTemp = s.nextLine().split(" ");
                    String[] nurHireDate = hDateTemp[2].split("/");
                    nurse.setHireDate(new Date((Integer) (Integer.parseInt(nurHireDate[0])), (Integer) (Integer.parseInt(nurHireDate[1])), (Integer) (Integer.parseInt(nurHireDate[2]))));

                    String[] oPhone = s.nextLine().split(" ");
                    nurse.setOfficePhone(fromString(oPhone[2]));

                    nurse.setSpeciality(s.nextLine().split(":")[1]);

                    String[] SWTime = s.nextLine().split(":");
                    nurse.setStartWorkingTime(new Time((Integer) (Integer.parseInt(SWTime[1])), (Integer) (Integer.parseInt(SWTime[2])), (Integer) (Integer.parseInt(SWTime[3]))));

                    String[] EWTime = s.nextLine().split(":");
                    nurse.setEndWorkingTime(new Time((Integer) (Integer.parseInt(EWTime[1])), (Integer) (Integer.parseInt(EWTime[2])), (Integer) (Integer.parseInt(EWTime[3]))));

                    NURSES.put(nurseID, nurse);
                }
            }
            if (notFound) {
                throw new IllegalArgumentException("no nurse was found");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NurseImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void loadNurseAssignments() {
        try {
            Scanner input = new Scanner(nurseAssgnmentsFile);
            String nextLine;
            while (input.hasNext()) {
                nextLine = input.nextLine();
                if (nextLine.contains("D")) {
                    String[] line = nextLine.split(":");
                    String doctorID = line[0];
                    String[] nursesArray = line[1].split(" ");
                    ArrayList<Nurse> nurses = new ArrayList<Nurse>();
                    for (String nurseID : nursesArray) {
                        nurses.add(NURSES.get(nurseID));
                    }
                    nurseAssignments.put(doctorID, nurses);
                }
            }
            for (String doctorID : nurseAssignments.keySet()) {
                for (Nurse n : nurseAssignments.get(doctorID)) {
                    DOCTORS.get(doctorID).assignNurse(n);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("cant read nurse assignments");
        }
    }

    public static void saveNurseAssignments() {
        try {
            FileWriter fw = new FileWriter(nurseAssgnmentsFile, true);
            String s = "";
            for (String doctorID : nurseAssignments.keySet()) {
                s = s + (doctorID + ":");
                for (Nurse n : nurseAssignments.get(doctorID)) {
                    s = s + (n.getNurseID() + " ");
                }
                s = s + ("\n");
            }
            fw.write(s);
            fw.close();

        } catch (IOException ex) {
            System.out.println("cant write nurse assignmentss");
        }

    }

    @Override
    public String toString() {
        return String.format("Nurse %s %s", getFirstName(), getLastName());
    }

    public String toFile() {
        return String.format("NurseID: %s\n%s\n", getNurseID(), super.toFile());
    }

}
