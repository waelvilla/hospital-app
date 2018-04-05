/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.Phone;


public class PhoneImpl implements Phone{
    private int countryCode;
    private int cityCode;
    private int extension;

    public PhoneImpl(int countryCode, int cityCode, int extension) {
        setCountryCode(countryCode);
        setCityCode(cityCode);
        setExtension(extension);
    }
    public PhoneImpl(){
        
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return String.format(getCountryCode() + "-" +getCityCode() + "-" +getExtension());
    }
    
}
