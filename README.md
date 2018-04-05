# hospital-app
Hospital Management System written in Java SE.

Part 1: Accessing the system

When the program first starts, the user can either access the hospital system or view information about the hospital. 
If the user decides to view the hospital information, they will be displayed with the hospital’s name, web link, address, and phone number. 
If the user decides to access the system they will be displayed with a menu of possible roles

Part 2: Picking Role


The user gets to pick a role: doctor, nurse, or patient.
If the user picks “Doctor”, he will be displayed with 2 possibilities: Registering as a new doctor, or accessing an existing doctor profile. 
To add a new doctor, the user will have to input the Doctor’s ID, first name, last name, date of birth, hire date, national ID, mobile phone, office phone, specialty, start working time, end working time, and department. The user will be asked if the doctor has any nurses assigned. If he does, the user will input the nurse ID’s and the system will assign these nurses to the doctor. 
To access an existing doctor profile, the user will have to input the doctor’s ID, and the system will load and display a doctor with the matching ID.
To add a new nurse, the user will have to input the nurse’s ID, first name, last name, date of birth, hire date, national ID, mobile phone, office phone, specialty, start working time, end working time, department, and assigned doctor (if any).
To access an existing nurse profile, the user will have to input the nurse’s ID, and the system will load and display a nurse with the matching ID.
To add a new patient, the user will have to input the patient’s hospital ID, first name, last name, date of birth, mobile phone, Social Security Number, date of first consultation (if any), and address.
To access an existing patient profile, the user will have to input the patient’s hospital ID, and the system will load and display a patient with the matching ID.

Part 2.1: Doctor functions

After a doctor has been selected (by either creating a new one or accessing an existing one), the user will be displayed with a list of the possible functions: add new consultation report to a patient, view previous consultation reports the doctor made, change the doctor’s information, or get new nurses assigned. 
If the user decides to add a new consultation report, the patient must have made an appointment with this doctor. The user will have to enter the patient’s ID, which the system uses to check if the patient has made an appointment. If an appointment is found, the user will have to input the patient’s temperature, blood pressure, height, weight, symptoms, and prescribed medicine. The consultation report will be written in the patient’s medical report. 
