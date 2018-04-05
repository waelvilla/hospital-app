/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Address;


public class AddressImpl implements Address{
    private String streetName;
    private int streetNumber;
    private int zipCode;
    private String city;
    private String country;

    public AddressImpl(String streetName, int streetNumber, int zipCode, String city, String country) {
        setStreetName(streetName);
        setStreetNumber(streetNumber);
        setZipCode(zipCode);
        setCity(city);
        setCountry(country);
    }
    public AddressImpl(){
        
    }
    
    
    @Override
    public String getStreetName() {
        return streetName;
    }
    @Override
    public void setStreetName(String streetName) {
        this.streetName=streetName;
    }
    @Override
    public int getStreetNumber() {
        return streetNumber;
    }
    @Override
    public void setStreetNumber(int streetNumber) {
        this.streetNumber=streetNumber;
    }
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode=zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city=city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country=country;
    }

    @Override
    public String toString() {
        return String.format("Address: %s, %d, %d, %s, %s",getStreetName(),getStreetNumber(),getZipCode(),getCity(),getCountry());
    } 
}
