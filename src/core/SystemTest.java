/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static core.HospitalImpl.nurseAssignments;
import static core.NurseImpl.nurseAssgnmentsFile;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Wael
 */
public class SystemTest {

    public static void main(String[] args) {
                System.out.println("\n---------------------------------------------------------------\nDo you want to Go Back? [Y/N]:");

    }

    public static boolean ask() {
        System.out.println("\n\nDo you want to Go Back? [Y/N]:");
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
