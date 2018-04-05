/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Person;
import Interfaces.Phone;
import refClasses.Date;

/**
 *
 * @author Wael
 */
public class PersonImpl implements Person{
    private String firstName;
    private String LastName;
    private Date DOB;
    private String NationalID;
    private Phone mobilePhone;

    public PersonImpl(String firstName, String LastName, Date DOB, String NationalID, Phone mobilePhone) {
        setFirstName(firstName);
        setLastName(LastName);
        setDOB(DOB);
        setNationalID(NationalID);
        setMobilePhone(mobilePhone);
    }
    public PersonImpl(){
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String NationalID) {
        this.NationalID = NationalID;
    }

    public Phone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Phone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString(){
        return String.format("%s %s",getFirstName(),getLastName());
    }
    public String toFile(){
        return String.format("%s \nDate of Birth: %s \nNationalID: %s \nMobile Phone: %s",toString(),getDOB(),getNationalID(),getMobilePhone());
    }

    

}

  