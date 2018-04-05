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
public interface Department {
    public abstract void setName(String name);
    public abstract String getName();
    
    public abstract Staff getStaff();
    public abstract void setStaff(Staff staff);
}
