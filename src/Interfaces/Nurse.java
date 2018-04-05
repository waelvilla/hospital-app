/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Wael
 */
public interface Nurse extends Staff{
    public abstract String getNurseID();
    public abstract void setNurseID(String nurseID);    
    
    public abstract Doctor getDoctor();
    public abstract void setDoctor(Doctor doctor);
    
    public abstract void writeToFile();
    public abstract String toFile();
    
    
}
