/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
The hospital is characterized by its name, its website link,
its address, phone number, etc. The address is composed of a 
street name, street number, zip code, city and country. 
The phone is characterized by a countryCode, a cityCode and 
an extension. 

 */
public interface Address {
    public abstract String getStreetName();
    public abstract void setStreetName(String streetName);
    public abstract int getStreetNumber();
    public abstract void setStreetNumber(int streetNumber);
    public abstract int getZipCode();
    public abstract void setZipCode(int zipCode);
    public abstract String getCity();
    public abstract void setCity(String city);
    public abstract String getCountry();
    public abstract void setCountry(String country);
}
