/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Appointment;
import Interfaces.Doctor;
import Interfaces.Patient;
import static core.HospitalImpl.APPOINTMENTS;
import static core.HospitalImpl.DOCTORS;
import static core.HospitalImpl.PATIENTS;
import static core.UserInterface.fromString;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import refClasses.Date;
import refClasses.Time;

/*
A patient takes an appointment for a consultation with a doctor. An appointment has a doctor,
patient, a appointment date and an appointment time and a price of consultation. The appointment time must
be valid to be inside the working hours of the doctor. If the patient has a social security number with 
class A the cost of consultation will be only 20% of the real cost. If he has a social security number with a class B,
the cost is just set to zero. 
 */
public class AppointmentImpl implements Appointment {

    private String appointmentID;
    private Doctor doctor;
    private Patient patient;
    private Date appointmentDate;
    private Time appointmentTime;
    private double price;

    public static File appointmentsFile = new File("Data Files\\Appointments.txt");

    public AppointmentImpl(String appointmentID, Doctor doctor, Patient patient, Date AppointmentDate, Time AppointmentTime, double price) {
        setAppointmentID(appointmentID);
        setDoctor(doctor);
        setPatient(patient);
        setAppointmentDate(AppointmentDate);
        setAppointmentTime(AppointmentTime);
        setAppointmentPrice(price);
        writeToFile();
    }

    public AppointmentImpl(File file, String appointmentID) {
        readFile(file, appointmentID);
    }

    public AppointmentImpl() {

    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    @Override
    public Doctor getDoctor() {
        return doctor;
    }

    // @Override
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Patient getPatient() {
        return patient;
    }

    @Override
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    @Override
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public Time getAppointmentTime() {
        return appointmentTime;
    }

    @Override
    public void setAppointmentTime(Time appointmentTime) {
        if ((appointmentTime.compareTo(getDoctor().getStartWorkingTime()) >= 0)
                && (appointmentTime.compareTo(getDoctor().getEndWorkingTime()) < 0)) {
            this.appointmentTime = appointmentTime;
        } else {
            throw new IllegalArgumentException("Appointment has to be within Doctor's working hours");
        }

    }

    @Override
    public double getAppointmentPrice() {
        return price;
    }

    @Override
    public void setAppointmentPrice(double price) {
        if (getPatient().getSSN().getSSNClass() == 'A') {
            this.price = price * 0.2;
        }
        if (getPatient().getSSN().getSSNClass() == 'B') {
            this.price = 0.0;
        } else {
            this.price = price;
        }
    }

    @Override
    public void writeToFile() {

        try {

            FileWriter fw = new FileWriter(appointmentsFile, true);
            fw.write(toFile() + "\n");
            fw.close();

        } catch (IOException ex) {
            System.out.println("cannot write to appointmentsFile.txt");
        }
    }

    public void readFile(File file, String appointmentID) {
        try {
            Scanner appointmentInput = new Scanner(file);
            String nextLine;
            Boolean notFound = true;
            while (appointmentInput.hasNext()) {
                nextLine = appointmentInput.nextLine();
                if (nextLine.contains(appointmentID)) {
                    notFound = false;
                    setAppointmentID(nextLine.split(" ")[1]);
                    Patient tempPatient = new PatientImpl(appointmentInput.nextLine().split(" ")[1]);
                    setPatient(tempPatient);
                    Doctor tempDoctor = new DoctorImpl(appointmentInput.nextLine().split(" ")[1]);
                    setDoctor(tempDoctor);
                    String docname = appointmentInput.nextLine();
                    setAppointmentDate(fromString(appointmentInput.nextLine().split(" ")[2]));
                    setAppointmentTime(fromString(appointmentInput.nextLine().split(" ")[2]));
                    setAppointmentPrice((Double) (Double.parseDouble(appointmentInput.nextLine().split(" ")[2])));
                    break;
                }

            }
            if (notFound) {
                throw new IllegalArgumentException("no appointment with id " + appointmentID + " was found");
            }

        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Cannot read appointment file");
        }

    }
    public static void loadFile(){
                try {
            Scanner appointmentInput = new Scanner(appointmentsFile);
            String nextLine;
            Boolean notFound = true;
            while (appointmentInput.hasNext()) {
                nextLine = appointmentInput.nextLine();
                if (nextLine.contains("AppointmentID")) {
                    notFound = false;
                    Appointment appointment=new AppointmentImpl();
                    String appointmentID=nextLine.split(" ")[1];
                    appointment.setAppointmentID(appointmentID);
                    Patient tempPatient =PATIENTS.get(appointmentInput.nextLine().split(" ")[1]);
                    appointment.setPatient(tempPatient);
                    Doctor tempDoctor = DOCTORS.get(appointmentInput.nextLine().split(" ")[1]);
                    appointment.setDoctor(tempDoctor);
                    String docname = appointmentInput.nextLine();
                    appointment.setAppointmentDate(fromString(appointmentInput.nextLine().split(" ")[2]));
                    appointment.setAppointmentTime(fromString(appointmentInput.nextLine().split(" ")[2]));
                    appointment.setAppointmentPrice((Double) (Double.parseDouble(appointmentInput.nextLine().split(" ")[2])));
                    APPOINTMENTS.put(appointmentID, appointment);
                }
            }
            if (notFound) {
                throw new IllegalArgumentException("no appointments found");
            }

        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Cannot read appointment file");
        }
    }
    

    public static String getAppID(String patientID, String doctorID) {
        String appID = "";

        try {
            Scanner s = new Scanner(appointmentsFile);
            String appLine;
            String patIDLine;
            String docIDLine;
            boolean isFound = false;
            while (s.hasNext()) {
                appLine = s.nextLine();
                patIDLine = s.nextLine();
                docIDLine = s.nextLine();
                if (patIDLine.contains(patientID) && docIDLine.contains(doctorID)) {
                    appID=appLine.split(" ")[1];
                    isFound = true;
                    break;

                }

            }
            if(!isFound){
                throw new IllegalArgumentException("No appointment found with PatientID "+patientID+" ,DoctorID "+doctorID);
            }
            return appID;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppointmentImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appID;
    }
    //JSON Attempt
   /* public void writeJSON(String filename) {
        File file = new File(filename);
        FileWriter fw;
        try {
            fw = new FileWriter(file, true);
            Gson gson = new Gson();
            fw.write(gson.toJson(this));
            fw.close();

        } catch (IOException ex) {
            throw new IllegalArgumentException("cannot write to " + filename);
        }

    }

    public void readJSON(String filename) {

    }*/
  
    @Override
    public String toString() {
        return "AppointmentID: " + appointmentID + "\nPatientID: " + patient.getHospitalID() + "\nDoctorID: " + doctor.getDoctorID() + "\nDoctor: " + doctor.toString() + "\nAppointment Date: " + appointmentDate
                + "\nAppointment Time: " + appointmentTime + "\nprice ($): " + price + "\n";
    }

    public String toFile() {
        return toString();
    }

}
