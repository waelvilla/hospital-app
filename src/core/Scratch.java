/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Doctor;
import Interfaces.Hospital;
import Interfaces.Nurse;
import static core.HospitalImpl.DOCTORS;
import static core.HospitalImpl.NURSES;
import static core.HospitalImpl.nurseAssignments;
import java.util.ArrayList;

/**
 *
 * @author Wael
 */
public class Scratch {

    public static void main(String[] args) {
        Hospital h=HospitalImpl.getInstance("hospital", "link", new AddressImpl(), new PhoneImpl());
        h.loadFiles();
        Doctor d=DOCTORS.get("D001");
        Nurse n1=NURSES.get("N001");
        ArrayList<Nurse> nurses=new ArrayList<Nurse>();
        nurses.add(n1);
        d.setAssignedNurses(nurses);
        System.out.println(n1.getDoctor());
        
        
        
        
    }
}
