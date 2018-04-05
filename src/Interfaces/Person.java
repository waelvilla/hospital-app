/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import refClasses.Date;
/*
Each medical staff has a first name, last name,
date of birth, hire date, national ID, mobile phone and office phone,
a specialty, start working time and end working time. 
*/


public interface Person {
    public abstract String getFirstName();
    public abstract void setFirstName(String firstName);
    
    public abstract String getLastName();
    public abstract void setLastName(String lastName);
    
    public abstract Date getDOB();
    public abstract void setDOB(Date DOB);
    
    public abstract String getNationalID();
    public abstract void setNationalID(String nationalID);
    
    public abstract Phone getMobilePhone();
    public abstract void setMobilePhone(Phone mobilePhone);
    
    
}
