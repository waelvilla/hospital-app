
package Interfaces;

import refClasses.Date;
import refClasses.Time;

/*
Each medical staff has a first name, last name,
date of birth, hire date, national ID, mobile phone and office phone,
a specialty, start working time and end working time. 
*/
public interface Staff extends Person{

    public abstract Phone getOfficePhone();
    public abstract void setOfficePhone(Phone officePhone);
    public abstract Date getHireDate();
    public abstract void setHireDate(Date hireDate);
    public abstract String getSpeciality();
    public abstract void setSpeciality(String speciality);
    public abstract Time getStartWorkingTime();
    public abstract void setStartWorkingTime(Time StartWorkingTime);
    public abstract Time getEndWorkingTime();
    public abstract void setEndWorkingTime(Time EndWorkingTime);
}
