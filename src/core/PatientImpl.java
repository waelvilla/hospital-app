package core;

import Interfaces.Address;
import Interfaces.Appointment;
import Interfaces.ConsultationReport;
import Interfaces.Doctor;
import Interfaces.Patient;
import Interfaces.Phone;
import Interfaces.SSN;
import static core.DoctorImpl.doctorsFile;
import static core.HospitalImpl.DOCTORS;
import static core.HospitalImpl.PATIENTS;
import static core.UserInterface.fromString;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import refClasses.Date;
import refClasses.Temperature;
import refClasses.Time;

public class PatientImpl extends PersonImpl implements Patient {

    private SSN SSN;
    private String hospitalID;
    private Date firstConsultDate;
    private Address address;
    private Map<String, Appointment> appointments = new TreeMap<String, Appointment>();
    private Appointment appointment;

    public static File patientsFile = new File("Data Files\\Patients.txt");
    private ArrayList<ConsultationReport> medicalReport = new ArrayList<ConsultationReport>();

    public PatientImpl(String hospitalID, String firstName, String LastName, Date DOB, String NationalID, PhoneImpl mobilePhone, SSNImpl SSN,
            Date firstConsultDate, AddressImpl address) {
        super(firstName, LastName, DOB, NationalID, mobilePhone);
        setSSN(SSN);
        setHospitalID(hospitalID);
        setFirstConsultationDate(firstConsultDate);
        setAddress(address);
        //writeToFile();

    }

    //no First Consultation
    public PatientImpl(String hospitalID, String firstName, String LastName, Date DOB, String NationalID, PhoneImpl mobilePhone, SSNImpl SSN,
            AddressImpl address) {
        super(firstName, LastName, DOB, NationalID, mobilePhone);
        setSSN(SSN);
        setHospitalID(hospitalID);
        setAddress(address);
        //writeToFile();

    }

    public PatientImpl() {

    }

    public PatientImpl(String hospitalID) {
        readFile(hospitalID);
    }

    @Override
    public Date getFirstConsultationDate() {
        return firstConsultDate;
    }

    @Override
    public void setFirstConsultationDate(Date firstConsultationDate) {
        if (this.getAppointment() != null) {
            if (firstConsultationDate.CompareTo(this.getAppointment().getAppointmentDate()) > 0) {
                this.firstConsultDate = firstConsultationDate;
            } else {
                this.firstConsultDate = this.getAppointment().getAppointmentDate();
            }
        } else {
            this.firstConsultDate = firstConsultationDate;
        }

    }

    @Override
    public SSN getSSN() {
        return SSN;

    }

    @Override
    public void setSSN(SSN SSN) {
        this.SSN = SSN;

    }

    @Override
    public String getHospitalID() {
        return hospitalID;
    }

    @Override
    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;

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
    public Appointment getAppointment() {
        return appointment;
    }

    @Override
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public ArrayList<ConsultationReport> getMedicalReport() {
        return medicalReport;
    }

    public void setMedicalReport(ArrayList<ConsultationReport> medicalReport) {
        this.medicalReport = medicalReport;
    }

    public void writeToFile() {
        try {

            FileWriter fw = new FileWriter(patientsFile,true);
            fw.write(toFile() + "\n");
            fw.close();

        } catch (IOException ex) {
            System.out.println("cannot write to Patients.txt");
        }
    }


    public void readFile(String HospitalID) {
        try {
            Scanner patientFileInput = new Scanner(patientsFile);
            String nextLine;
            Boolean notFound = true;
            while (patientFileInput.hasNext()) {
                nextLine = patientFileInput.nextLine();
                if (nextLine.contains(HospitalID)) {
                    notFound = false;
                    //TODO: use fromString , substring()                 
                    setHospitalID(nextLine.split(" ")[1]);
                    String[] pnameSplit = patientFileInput.nextLine().split(" ");
                    setFirstName(pnameSplit[2]);
                    setLastName(pnameSplit[3]);
                    String[] dobTemp = patientFileInput.nextLine().split(" ");
                    String[] docDOB = dobTemp[3].split("/");
                    setDOB(new Date((Integer) (Integer.parseInt(docDOB[0])), (Integer) (Integer.parseInt(docDOB[1])), (Integer) (Integer.parseInt(docDOB[2]))));

                    setNationalID(patientFileInput.nextLine().split(" ")[1]);
                    String[] mPhone = patientFileInput.nextLine().split(" ");
                    setMobilePhone(new PhoneImpl((Integer) (Integer.parseInt(mPhone[2])), (Integer) (Integer.parseInt(mPhone[3])), (Integer) (Integer.parseInt(mPhone[4]))));

                    String[] arrSSN = patientFileInput.nextLine().split(" ");
                    setSSN(new SSNImpl(arrSSN[1], arrSSN[2].charAt(0)));

                    String consultLine = patientFileInput.nextLine();
                    if (!consultLine.contains("null")) {
                        setFirstConsultationDate(fromString(consultLine.substring(25)));
                    }
                    setAddress(fromString(patientFileInput.nextLine().substring(9)));

                    String consultRepLine = patientFileInput.nextLine();
                    String AppointmentLine="";
                    if(patientFileInput.hasNext())
                     AppointmentLine = patientFileInput.nextLine();
                    if ((consultRepLine.contains("Consultation Report")) && AppointmentLine.contains("AppointmentID")) {
                        String appointmentID = AppointmentLine.split(" ")[1];
                        String tempPatient = patientFileInput.nextLine();
                        Doctor tempDoctor = new DoctorImpl(patientFileInput.nextLine().split(" ")[1]);
                        String docname = patientFileInput.nextLine();
                        Date appointmentDate = fromString(patientFileInput.nextLine().split(" ")[2]);
                        Time appointmentTime = fromString(patientFileInput.nextLine().split(" ")[2]);
                        double price = (Double) (Double.parseDouble(patientFileInput.nextLine().split(" ")[2]));
                        setAppointment(new AppointmentImpl(appointmentID, tempDoctor, this, appointmentDate, appointmentTime, price));
                        HospitalImpl.PATIENTS.put(hospitalID, this);
                        break;
                    }
                   
                    break;

                }

            }
            if (notFound) {
                throw new IllegalArgumentException("no patient with id " + HospitalID + " was found");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }
    }

    public static void loadFile() {
        //TODO: load from file
        try {
            Scanner patientFileInput = new Scanner(patientsFile);
            
            String nextLine;
            Boolean notFound = true;
            Patient patient;
            while (patientFileInput.hasNext()) {
                nextLine = patientFileInput.nextLine();
                if (nextLine.contains("HospitalID")) {
                     patient=new PatientImpl();
                    notFound = false;
                    //TODO: use fromString , substring()     
                    String hospitalID=nextLine.split(" ")[1];
                    patient.setHospitalID(hospitalID);
                    String[] pnameSplit = patientFileInput.nextLine().split(" ");
                    patient.setFirstName(pnameSplit[2]);
                    patient.setLastName(pnameSplit[3]);
                    String[] dobTemp = patientFileInput.nextLine().split(" ");
                    String[] docDOB = dobTemp[3].split("/");
                    patient.setDOB(new Date((Integer) (Integer.parseInt(docDOB[0])), (Integer) (Integer.parseInt(docDOB[1])), (Integer) (Integer.parseInt(docDOB[2]))));

                    patient.setNationalID(patientFileInput.nextLine().split(" ")[1]);
                    String[] mPhone = patientFileInput.nextLine().split(" ");;
                    patient.setMobilePhone(fromString(mPhone[2]));
                    String[] arrSSN = patientFileInput.nextLine().split(": ");
                    patient.setSSN(fromString(arrSSN[1]));

                    String consultLine = patientFileInput.nextLine();
                    if (!consultLine.contains("null")) {
                        patient.setFirstConsultationDate(fromString(consultLine.substring(25)));
                    }
                    patient.setAddress(fromString(patientFileInput.nextLine().substring(9)));
                    
                    // String MedRepLine=patientFileInput.nextLine();
                    String reportLine = patientFileInput.nextLine();
                    String AppointmentLine="";
                    while(patientFileInput.hasNext()&&!reportLine.contains("]")){
                        AppointmentLine = patientFileInput.nextLine();
                       if ((reportLine.contains("Consultation Report")) && AppointmentLine.contains("AppointmentID")) {
                           String appointmentID = AppointmentLine.split(" ")[1];
                           String tempPatient = patientFileInput.nextLine();
                           Doctor tempDoctor = new DoctorImpl(patientFileInput.nextLine().split(" ")[1]);
                           String docname = patientFileInput.nextLine();
                           Date appointmentDate = fromString(patientFileInput.nextLine().split(" ")[2]);
                           Time appointmentTime = fromString(patientFileInput.nextLine().split(" ")[2]);
                           double price = (Double) (Double.parseDouble(patientFileInput.nextLine().split(" ")[2]));
                           Appointment tempAppointment=new AppointmentImpl(appointmentID, tempDoctor, patient, appointmentDate, appointmentTime, price);
                           patient.setAppointment(tempAppointment);
                           String bloodPressure=patientFileInput.nextLine().split(" ")[2];
                           String[] temperatureLine=patientFileInput.nextLine().split(" ");
                           Double temperatureValue=(Double)(Double.parseDouble(temperatureLine[1]));        
                           char temperatureUnit=temperatureLine[2].charAt(0);
                           Temperature temperature=new Temperature(temperatureValue,temperatureUnit);
                           Double height=(Double)(Double.parseDouble(patientFileInput.nextLine().split(" ")[1]));   
                           Double weight=(Double)(Double.parseDouble(patientFileInput.nextLine().split(" ")[1]));   
                           String symptoms=patientFileInput.nextLine().split(": ")[1];
                           String medicine=patientFileInput.nextLine().split(": ")[1];
                           tempDoctor.addConsultationReport(patient, tempAppointment, bloodPressure, temperature, height, weight, symptoms, medicine);
                       }
                       reportLine = patientFileInput.nextLine();
                    }
                    HospitalImpl.PATIENTS.put(hospitalID, patient);
                    
                    
                }

            }
            
            if (notFound) {
                throw new IllegalArgumentException("no patient was found");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
        }

    }
    
    @Override
    public String toString() {
        return String.format("%s %s", super.getFirstName(), super.getLastName());
    }

    public String toFile() {
        return String.format("HospitalID: %s\nPatient Name: %s \n%s \nFirst Consultation Date: %s\n%s\n%s\n\n",
                getHospitalID(), super.toFile(), getSSN(), getFirstConsultationDate(), getAddress(), getMedicalReport());
    }

}
