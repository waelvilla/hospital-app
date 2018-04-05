/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Appointment;
import Interfaces.ConsultationReport;
import Interfaces.Doctor;
import Interfaces.Hospital;
import Interfaces.Nurse;
import Interfaces.Patient;
import static core.AppointmentImpl.appointmentsFile;
import static core.DoctorImpl.doctorsFile;
import static core.HospitalImpl.APPOINTMENTS;
import static core.HospitalImpl.DOCTORS;
import static core.HospitalImpl.NURSES;
import static core.HospitalImpl.PATIENTS;
import static core.HospitalImpl.nurseAssignments;
import java.util.ArrayList;
import java.util.Scanner;
import refClasses.Date;
import refClasses.Temperature;
import refClasses.Time;

public class UserInterface {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Scanner inputLine = new Scanner(System.in);
        // MENU BOOLEANS 
        boolean programIsRunning = true;
        boolean doctorIsRunning=true;
        boolean nurseIsRunning=true;
        boolean patientIsRunning=true;
 
        Hospital hospital = HospitalImpl.getInstance("California General Hospital", "www.cgh.us", new AddressImpl("Parnassus Ave", 505, 94143, "San Francisco", "USA"), new PhoneImpl(01, 22, 9988));
        hospital.loadFiles();
        //WHILE PROGRAM IS RUNNING
        do {
            System.out.println("Welcome to " + hospital.getName() + "\n\nWould you like to: \n1.Access the system \n2.View hospital information");
            int loadSystem = input.nextInt();
            //View Hospital Info
            if (loadSystem == 2) {
                System.out.println(hospital);
                System.out.println("\nEnter a number to view: \n1.Doctors \n2.Nurses \n3.Patients");
                int role = input.nextInt();
                switch (role) {
                    //Doctor
                    case 1:
                        System.out.println(DOCTORS.values());
                        programIsRunning = askToGoBack("main");
                        break;
                    //Nurses
                    case 2:
                        System.out.println(NURSES.values());
                        programIsRunning = askToGoBack("main");
                        break;
                    //Patients
                    case 3:
                        System.out.println(PATIENTS.values());
                        programIsRunning = askToGoBack("main");
                        break;

                    default:
                        System.out.println("Wrong Entry");
                        programIsRunning = askToGoBack("main");
                        break;

                }

            } //Load System
            else if (loadSystem == 1) {
                System.out.println("Choose the number of your Role:\n1.Doctor\n2.Nurse\n3.Patient");
                int role = input.nextInt();
                //Doctor
                if (role == 1) {
                    Doctor doctor;
                    System.out.println("Choose the number of your Role:\n1.new Doctor\n2.Existing Doctor");
                    int doctorRole = input.nextInt();
                /////////////            DOCTOR ROLE          ////////////////////////////////////////////////
                    switch (doctorRole) {

                        case 1://New Doctor
                        {
                            doctor = new DoctorImpl();
                            System.out.println("Enter the following info:");
                            System.out.print("DoctorID: ");
                            String doctorID = input.next();
                            doctor.setDoctorID(doctorID);
                            System.out.print("First Name: ");
                            String firstName = input.next();
                            doctor.setFirstName(firstName);
                            System.out.print("Last Name: ");
                            String lastName = input.next();
                            doctor.setLastName(lastName);
                            System.out.print("Date of Birth (DD/MM/YYYY) : ");
                            Date docDOB = fromString(input.next());
                            doctor.setDOB(docDOB);
                            System.out.print("Hire Date (DD/MM/YYYY) : ");
                            Date DocHireDate = fromString(input.next());
                            doctor.setHireDate(DocHireDate);
                            System.out.print("National ID: ");
                            String NationalID = input.next();
                            doctor.setNationalID(NationalID);
                            System.out.print("Mobile Phone (999-99-999) : ");
                            PhoneImpl DocMobilePhone = fromString(input.next());
                            doctor.setMobilePhone(DocMobilePhone);
                            System.out.print("Office Phone (999-99-999) : ");
                            PhoneImpl DocOfficePhone = fromString(input.next());
                            doctor.setOfficePhone(DocOfficePhone);
                            System.out.print("Speciality: ");
                            String speciality = inputLine.nextLine();
                            doctor.setSpeciality(speciality);
                            System.out.print("Start Working Time (HH:MM:SS) : ");
                            Time docStartWorkTime = fromString(input.next());
                            doctor.setStartWorkingTime(docStartWorkTime);
                            System.out.print("End Working Time(HH:MM:SS) : ");
                            Time docEndWorkTime = fromString(input.next());
                            doctor.setEndWorkingTime(docEndWorkTime);
                            System.out.print("Department: ");
                            String docDepartment = inputLine.nextLine();
                            doctor.setDepartment(docDepartment);
                            System.out.println("Do you have nurses assigned? \n1.Yes \n2.No");

                            int haveNurses = input.nextInt();
                            if (haveNurses == 1) {
                                System.out.println("How many nurses do you have?");
                                int numNurses = input.nextInt();
                                int j = 1;
                                for (int i = 0; i < numNurses; i++) {
                                    System.out.print("Enter Nurse " + j + "'s ID : ");
                                    String nurseID = input.next();
                                    Nurse nurse = NURSES.get(nurseID);
                                    doctor.assignNurse(nurse);
                                    j++;
                                }
                            }
                            DOCTORS.put(doctorID, doctor);
                            break;
                        }

                        case 2: //Existing Doctor
                        {
                            System.out.print("Enter your DoctorID: ");
                            String doctorID = input.next();
                            doctor = DOCTORS.get(doctorID);
                            break;
                        }
                        default:
                            throw new IllegalArgumentException("Invalid Entry: Please enter either 1 or 2");
                    }
                    do{
                    System.out.println("\nSelected Doctor:  " + doctor + "\n\nWould you like to:\n1.Add new patient consultation\n2.View previous consultation reports\n3.change your information \n4.Assign new Nurses\n\nor press 0 to terminate program");
                    int docFunction = input.nextInt();
                    
                    switch (docFunction) {
                        case 0: //terminate program
                            programIsRunning=false;
                            break;
                        case 1://add new patient consultation
                            System.out.print("Patient HospitalID: ");
                            Patient patientTemp = PATIENTS.get(input.next());
                            String appointID = AppointmentImpl.getAppID(patientTemp.getHospitalID(), doctor.getDoctorID());
                            Appointment appTemp;
                            if (appointID.compareTo(" ") > 0) {
                                appTemp = APPOINTMENTS.get(appointID);
                                patientTemp.setAppointment(appTemp);
                                System.out.println("Selected Patient: " + patientTemp.toString());
                            } else {
                                throw new IllegalArgumentException("Patient " + patientTemp + " has not made an appointment");
                            }
                            System.out.print("Patient Blood Pressure: ");
                            String patientBloodPressure = input.next();
                            System.out.print("Patient Temperature: ");
                            String[] arrTemperature = inputLine.nextLine().split(" ");
                            Temperature patientTemperature = new Temperature((Double) (Double.parseDouble(arrTemperature[0])), arrTemperature[1].charAt(0));
                            System.out.print("Patient Height: ");
                            double patientHeight = input.nextDouble();
                            System.out.print("Patient Weight: ");
                            double patientWeight = input.nextDouble();
                            System.out.print("Symptoms: ");
                            String Symptoms = inputLine.nextLine();
                            System.out.print("Medicine: ");
                            String Medicine = inputLine.nextLine();
                            doctor.addConsultationReport(patientTemp, patientTemp.getAppointment(), patientBloodPressure, patientTemperature, patientHeight, patientWeight, Symptoms, Medicine);
                            doctorIsRunning=askToGoBack("doctor");
                            
                            break;
                        case 2://View prev consult reports

                            System.out.println(doctor.getConsultations());
                            doctorIsRunning=askToGoBack("doctor");
                            break;
                        case 3: //TODO: Change doc info
                            System.out.println("What would you like to change? \n 1.Name \n2.Date of Birth \n3.Department \n4.Speciality \n5.Work hours \n6.Mobile Phone \n7.Office Phone\n\nor press 0 to terminate program");
                            int infoChange = input.nextInt();
                            switch (infoChange) {
                                case 0: //terminate program
                                    programIsRunning=false;
                                    break;
                                    
                                case 1:// Name Change
                                    System.out.print("First Name: ");
                                    String firstName = input.next();
                                    System.out.print("Last Name: ");
                                    String lastName = input.next();
                                    doctor.setFirstName(firstName);
                                    doctor.setLastName(lastName);
                                    doctorIsRunning=askToGoBack("doctors");
                                    break;
                                case 2://Date of Birth
                                    System.out.print("Date of Birth (DD/MM/YYYY) : ");
                                    Date docDOB = fromString(input.next());
                                    doctor.setDOB(docDOB);
                                    doctorIsRunning=askToGoBack("doctors");
                                    break;
                                case 3://Department
                                    System.out.print("Department: ");
                                    String docDepartment = inputLine.nextLine();
                                    doctor.setDepartment(docDepartment);
                                    doctorIsRunning=askToGoBack("doctors");
                                    break;
                                case 4://Speciality
                                    System.out.print("Speciality: ");
                                    String speciality = inputLine.nextLine();
                                    doctor.setSpeciality(speciality);
                                    doctorIsRunning=askToGoBack("doctors");
                                    break;
                                case 5://Work hours
                                    System.out.print("Start Working Time (HH:MM:SS) : ");
                                    Time docStartWorkTime = fromString(input.next());
                                    System.out.print("End Working Time(HH:MM:SS) : ");
                                    Time docEndWorkTime = fromString(input.next());
                                    doctor.setStartWorkingTime(docStartWorkTime);
                                    doctor.setEndWorkingTime(docEndWorkTime);
                                    doctorIsRunning=askToGoBack("doctors");
                                    break;
                                case 6://Mobile Phone
                                    System.out.print("Mobile Phone (999-99-999) : ");
                                    PhoneImpl DocMobilePhone = fromString(input.next());
                                    doctor.setMobilePhone(DocMobilePhone);
                                    doctorIsRunning=askToGoBack("doctors");

                                    break;
                                case 7://Office Phone
                                    System.out.print("Office Phone (999-99-999) : ");
                                    PhoneImpl DocOfficePhone = fromString(input.next());
                                    doctor.setOfficePhone(DocOfficePhone);
                                    doctorIsRunning=askToGoBack("doctors");
                                    break;
                                default:
                                    System.out.println("invalid entry");
                                    doctorIsRunning=askToGoBack("doctors");
                                    break;
                            }
                            
                            break;
                        case 4: //get new nurses
                            ArrayList<Nurse> avNurses = new ArrayList<Nurse>();
                            for (Nurse n : NURSES.values()) {
                                if (n.getDoctor() == null) {
                                    avNurses.add(n);
                                }
                            }
                            System.out.println("Pick from these available Nurses: \n");
                            for (int i = 0; i < avNurses.size(); i++) {
                                System.out.println(i + ". " + avNurses.get(i).toString());
                            }
                            int pickedNurse = input.nextInt();
                            doctor.getAssignedNurses().add(avNurses.get(pickedNurse));
                            doctorIsRunning=askToGoBack("doctors");
                            break;
                        case 5: //remove nurse
                            System.out.println("Enter the ID of the nurse you would like to move");
                            String nurseID = input.next();
                            doctorIsRunning=askToGoBack("doctors");
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid enter from 1-3");
                    }
                    }while(doctorIsRunning&&programIsRunning);

                }
                
                    /////////////            NURSE ROLE          ////////////////////////////////////////////////
                else if (role == 2) {
                    Nurse nurse;
                    System.out.println("Choose the number of your role: \n1.New Nurse \n2.Existing Nurse");
                    int nurseRole = input.nextInt();

                    //TODO: new nurse
                    if (nurseRole == 1) {
                        System.out.println("Enter the following info:");
                        System.out.print("NurseID: ");
                        String nurseID = input.next();
                        System.out.print("First Name: ");
                        String firstName = input.next();
                        System.out.print("Last Name: ");
                        String lastName = input.next();
                        System.out.print("Date of Birth (DD/MM/YYYY) : ");
                        Date nurseDOB = fromString(input.next());
                        System.out.print("Hire Date (DD/MM/YYYY) : ");
                        Date nurseHireDate = fromString(input.next());
                        System.out.print("National ID: ");
                        String NationalID = input.next();
                        System.out.print("Mobile Phone (999-99-999) : ");
                        PhoneImpl nurseMobilePhone = fromString(input.next());
                        System.out.print("Office Phone (999-99-999) : ");
                        PhoneImpl nurseOfficePhone = fromString(input.next());
                        System.out.print("Speciality: ");
                        String speciality = inputLine.nextLine();
                        System.out.print("Start Working Time (HH:MM:SS) : ");
                        Time nurseStartWorkTime = fromString(input.next());
                        System.out.print("End Working Time(HH:MM:SS) : ");
                        Time nurseEndWorkTime = fromString(input.next());
                        System.out.println("Have you been assigned to a doctor? \n1.Yes \n2.No");
                        int haveDoc = input.nextInt();
                        if (haveDoc == 1) {
                            System.out.print("Enter DoctorID: ");
                            String doctorID = input.next();
                            Doctor tempDoctor = DOCTORS.get(doctorID);
                            nurse = new NurseImpl(nurseID, firstName, lastName, nurseDOB, nurseHireDate, NationalID, nurseMobilePhone, nurseOfficePhone, speciality, nurseStartWorkTime, nurseEndWorkTime, tempDoctor);
                        } else {
                            nurse = new NurseImpl(nurseID, firstName, lastName, nurseDOB, nurseHireDate, NationalID, nurseMobilePhone, nurseOfficePhone, speciality, nurseStartWorkTime, nurseEndWorkTime);
                        }
                        NURSES.put(nurseID, nurse);
                    } //TODO: existing nurse
                    else if (nurseRole == 2) {
                        System.out.print("Enter NurseID: ");
                        String nurseID = input.next();
                        nurse = NURSES.get(nurseID);
                    } else {
                        throw new IllegalArgumentException("Invalid Entry: Please enter either 1 or 2");
                    }
                    do{
                    System.out.println("What would you like to do?  \n1. View Information \n2.Change Information\n\nor press 0 to terminate program");
                    int decision = input.nextInt();
                        switch (decision) {
                            case 0: //terminate program
                                programIsRunning=false;
                                break;
                            case 1: //view information
                                System.out.println(nurse.toFile());
                                nurseIsRunning=askToGoBack("nurses");
                                break;
                            case 2: //change information
                                System.out.println("What would you like to change?\n 1.Name \n2.Date of Birth \n3.Assigned Doctor \n4.Speciality \n5.Work hours \n6.Mobile Phone \n7.Office Phone");
                                int infoChange = input.nextInt();
                                switch (infoChange) {
                                    case 1:// Name Change
                                        System.out.print("First Name: ");
                                        String firstName = input.next();
                                        System.out.print("Last Name: ");
                                        String lastName = input.next();
                                        nurse.setFirstName(firstName);
                                        nurse.setLastName(lastName);
                                        nurseIsRunning=askToGoBack("nurses");
                                        break;
                                    case 2://Date of Birth
                                        System.out.print("Date of Birth (DD/MM/YYYY) : ");
                                        Date docDOB = fromString(input.next());
                                        nurse.setDOB(docDOB);
                                        nurseIsRunning=askToGoBack("nurses");
                                        break;
                                    case 3://Assigned Doctor
                                        System.out.print("DoctorID: ");
                                        String doctorID = inputLine.next();
                                        nurse.setDoctor(DOCTORS.get(doctorID));
                                        nurseIsRunning=askToGoBack("nurses");
                                        break;
                                    case 4://Speciality
                                        System.out.print("Specialty: ");
                                        String speciality = inputLine.nextLine();
                                        nurse.setSpeciality(speciality);
                                        nurseIsRunning=askToGoBack("nurses");
                                        break;
                                    case 5://Work hours
                                        System.out.print("Start Working Time (HH:MM:SS) : ");
                                        Time docStartWorkTime = fromString(input.next());
                                        System.out.print("End Working Time(HH:MM:SS) : ");
                                        Time docEndWorkTime = fromString(input.next());
                                        nurse.setStartWorkingTime(docStartWorkTime);
                                        nurse.setEndWorkingTime(docEndWorkTime);
                                        nurseIsRunning=askToGoBack("nurses");
                                        break;
                                    case 6://Mobile Phone
                                        System.out.print("Mobile Phone (999-99-999) : ");
                                        PhoneImpl DocMobilePhone = fromString(input.next());
                                        nurse.setMobilePhone(DocMobilePhone);
                                        nurseIsRunning=askToGoBack("nurses");
                                        break;
                                    case 7://Office Phone
                                        System.out.print("Office Phone (999-99-999) : ");
                                        PhoneImpl DocOfficePhone = fromString(input.next());
                                        nurse.setOfficePhone(DocOfficePhone);
                                        nurseIsRunning=askToGoBack("nurses");
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Invalid enter from 1-7");
                                        
                                }       break;
                            
                            default:
                                break;
                        }
                    }while(nurseIsRunning&&programIsRunning);

                }
                /////////////            PATIENT ROLE          ////////////////////////////////////////////////
                else if (role == 3) {
                    Patient patient = null;
                    System.out.println("Choose the number of your role: \n1.New Patient \n2.Existing Patient");
                    int patientRole = input.nextInt();

                    switch (patientRole) {
                        //new patient
                        case 1:
                            System.out.println("Enter the following info: ");
                            System.out.print("First Name: ");
                            String firstName = input.next();
                            System.out.print("Last Name: ");
                            String lastName = input.next();
                            System.out.print("Date of Birth (DD/MM/YYYY) : ");
                            Date DOB = fromString(input.next());
                            System.out.print("National ID: ");
                            String NationalID = input.next();
                            System.out.print("Mobile Phone (999-99-999) : ");
                            PhoneImpl MobilePhone = fromString(input.next());
                            System.out.print("Social Security Number and class: (999-99-9999 A): ");
                            SSNImpl SSN = fromString(inputLine.nextLine());
                            System.out.print("HospitalID: ");
                            String HospitalID = input.next();
                            System.out.println("is this your first consultation? \n1.Yes \n2.No");
                            Date FirstConsultationDate;
                            boolean FCexist;
                            if (input.nextInt() == 2) {
                                FCexist = true;
                                System.out.print("Date of First Consultation: ");
                                FirstConsultationDate = fromString(input.next());
                            } else {
                                FCexist = false;
                                FirstConsultationDate = null;
                            }
                            System.out.print("Adress (Street Name, Street Number, Zip Code, City, Country): ");
                            AddressImpl address = fromString(inputLine.nextLine());
                            if (FCexist) {
                                patient = new PatientImpl(HospitalID, firstName, lastName, DOB, NationalID, MobilePhone, SSN, FirstConsultationDate, address);
                            } else {
                                patient = new PatientImpl(HospitalID, firstName, lastName, DOB, NationalID, MobilePhone, SSN, address);
                            }
                            //patient.writeToFile();
                            PATIENTS.put(HospitalID, patient);

                            break;
                        //Existing Patient
                        case 2:
                            System.out.print("Enter HospitalID OR enter S to search by keyword: ");
                            String patientEntry = input.next();
                            if (patientEntry.equalsIgnoreCase("S")) {
                                System.out.println("Enter First Name and/or Last Name");
                                String patientName = inputLine.nextLine();
                                boolean isFound = false;
                                for (Patient p : PATIENTS.values()) {
                                    if (p.getFirstName().contains(patientName) || p.getLastName().contains(patientName)) {
                                        System.out.println("Did you mean: \n" + p.toFile() + "Enter 1 if it matches, and 0 if it does not");
                                        if (input.nextInt() == 1) {
                                            isFound = true;
                                            patient = p;
                                            break;
                                        }
                                    }
                                }
                                if (!isFound) {
                                    throw new IllegalArgumentException("No patient with the name " + patientName + " was found.");
                                }

                            } else {
                                patient = PATIENTS.get(patientEntry);
                                if (patient == null) {
                                    throw new IllegalArgumentException("No patient found with id " + patientEntry);
                                }
                            }

                            break;
                        default:

                            throw new IllegalArgumentException("Invalid Entry: Please enter either 1 or 2");
                    }
                    do{
                    System.out.println("\nSelected Patient: " + patient + "\nWould you like to: \n1.Make a new Appointment \n2.View your medical report \n3.View your information \n4.View past measurements of temperature and blood pressure \n5.Sign out of Hospital and Delete Record\n \nor press 0 to terminate program");

                    //new appointment
                    switch (input.nextInt()) {
                        case 0://terminate program
                            programIsRunning=false;
                            break;
                        case 1:
                            System.out.print("AppointmentID: ");
                            String appointmentID = input.next();
                            System.out.print("DoctorID: ");
                            Doctor doctorTemp = DOCTORS.get(input.next());
                            System.out.print("Appointment Date (DD/MM/YYYY) : ");
                            Date AppointmentDate = fromString(input.next());
                            System.out.print("Appointment Time (HH:MM:SS) : ");
                            Time AppointmentTime = fromString(input.next());
                            System.out.print("Price: $");
                            Double price = Double.parseDouble(input.next());
                            Appointment appointment = new AppointmentImpl(appointmentID, doctorTemp, patient, AppointmentDate, AppointmentTime, price);
                            patient.setAppointment(appointment);
                            APPOINTMENTS.put(appointmentID, appointment);
                            System.out.println("\n Appointment Information: " + appointment);
                            patientIsRunning=askToGoBack("patients");
                            break;
                        case 2:// view medical report
                            System.out.println(patient.getMedicalReport());
                            patientIsRunning=askToGoBack("patients");
                            break;
                        case 3: //view information
                            System.out.println(patient.toFile());
                            patientIsRunning=askToGoBack("patients");
                            break;

                        case 4://view temperature and bloodpressure
                            for (ConsultationReport c : patient.getMedicalReport()) {
                                System.out.printf("On %s, %s: \nTemperature: %s \nBloodPressure: %s\n\n",
                                        c.getAppointment().getAppointmentDate(), c.getAppointment().getAppointmentTime(), c.getTemperature(), c.getBloodPressure());
                            }
                            patientIsRunning=askToGoBack("patients");
                            break;
                        case 5:// sign out of hospital and delete record
                            PATIENTS.remove(patient.getHospitalID());
                            for (Appointment a : APPOINTMENTS.values()) {
                                if (a.getPatient().getHospitalID() == patient.getHospitalID()) {
                                    APPOINTMENTS.remove(a.getAppointmentID());
                                }
                            }
                            System.out.println(patient.getFirstName() + " " + patient.getLastName() + " has been removed from hospital");
                            patientIsRunning=askToGoBack("patients");
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid Entry: Please enter either 1, 2, 3, 4 or 5");
                            
                    }
                    
                }while(patientIsRunning&&programIsRunning);
            }
                
                
            }
            hospital.saveRecords();
        } while (programIsRunning);
    }

    public static <T> T fromString(String str) {
        //Date
        if (str.contains("/")) {
            String[] arrDate = str.split("/");
            return (T) new Date(Integer.parseInt(arrDate[0]), Integer.parseInt(arrDate[1]), Integer.parseInt(arrDate[2]));
        } //Time
        else if (str.contains(":")) {
            String[] arrTime = str.split(":");
            return (T) new Time((Integer) (Integer.parseInt(arrTime[0])), (Integer) (Integer.parseInt(arrTime[1])), (Integer) (Integer.parseInt(arrTime[2])));
        } //SSN
        else if (str.contains("-") && str.contains(" ")) {
            String[] arrSSN = str.split(" ");
            return (T) new SSNImpl(arrSSN[0], arrSSN[1].charAt(0));
        } //Phone
        else if (str.contains("-")) {
            String[] arrPhone = str.split("-");
            return (T) new PhoneImpl((Integer) (Integer.parseInt(arrPhone[0])), (Integer) (Integer.parseInt(arrPhone[1])), (Integer) (Integer.parseInt(arrPhone[2])));
        } //Address
        else if (str.contains(", ")) {
            String[] arrAddress = str.split(", ");
            return (T) new AddressImpl(arrAddress[0], (Integer) (Integer.parseInt(arrAddress[1])), (Integer) (Integer.parseInt(arrAddress[2])), arrAddress[3], arrAddress[4]);
        } else {
            throw new IllegalArgumentException("Incoorect format");
        }

    }

    public static boolean askToGoBack(String role) {
        System.out.println("\n---------------------------------------------------------------\n"
                 +"Do you want to Go Back to "+role+" menu? [Y/N]:");
        Scanner s = new Scanner(System.in);
        String c = s.next();
        if (c.equalsIgnoreCase("Y")) {
            return true;
        } else if (c.equalsIgnoreCase("N")) {
            return false;
        } else {
            throw new IllegalArgumentException("Please enter Y or N");
        }
    }

    

}
