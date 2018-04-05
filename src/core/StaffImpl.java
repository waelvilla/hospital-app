/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Phone;
import Interfaces.Staff;
import refClasses.Date;
import refClasses.Time;



/*
    public  Phone getOfficePhone()
    public  void SetOfficePhone(Phone officePhone)
    public  Date getHireDate()
    public  void setHireDate(Date hireDate)
    public  void setSpeciality()
    public  void setStartWorkingTime()
    public  void setEndWorkingTime();
 */
public class StaffImpl  extends PersonImpl implements Staff{
    private Phone officePhone;
    private Date hireDate;
    private Time StartWorkingTime;
    private Time EndWorkingTime;
    private String speciality;
    
    public StaffImpl(String firstName, String LastName, Date DOB,Date hireDate,String NationalID, Phone mobilePhone,Phone officePhone, String speciality, Time StartWorkingTime, Time EndWorkingTime) {
        super(firstName, LastName, DOB, NationalID, mobilePhone);
        setOfficePhone(officePhone);
        setSpeciality(speciality);
        setHireDate(hireDate);
        setStartWorkingTime(StartWorkingTime);
        setEndWorkingTime(EndWorkingTime);
    }
    public StaffImpl(){
        
    }

    @Override
    public Phone getOfficePhone(){
        return officePhone;
    }
    
  @Override
    public void setOfficePhone(Phone officePhone){
        this.officePhone = officePhone;
    }

    @Override
    public Date getHireDate(){
        return hireDate;
    }

    @Override
    public void setHireDate(Date hireDate){
        this.hireDate = hireDate;
    }

    @Override
    public Time getStartWorkingTime(){
        return StartWorkingTime;
    }

    @Override
    public void setStartWorkingTime(Time StartWorkingTime){
        this.StartWorkingTime = StartWorkingTime;
    }

    @Override
    public Time getEndWorkingTime(){
        return EndWorkingTime;
    }

    @Override
    public void setEndWorkingTime(Time EndWorkingTime){
        this.EndWorkingTime = EndWorkingTime;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    @Override
    public String toString(){
        return String.format("%s %s",getFirstName(),getLastName());
    }
    
    public String toFile(){
        return String.format("%s \nHire Date: %s \nOffice Phone: %s \nSpeciality: %s \nStart Working Time:%s\nEnd Working Time:%s"
                ,super.toFile(),getHireDate(),getOfficePhone(),getSpeciality(),getStartWorkingTime(),getEndWorkingTime());
    }  

 

}
