package refClasses;

import Interfaces.Address;



/**
 *
 * @author akoubaa
 */
public class Address1 {
    private String StreetName;
    private String City;
    private String Country;
    private int zipCode;
    
    public Address1 (String sn, String city, String country){
        setStreetName(sn);
        City=city;
        Country=country;
    }
    
    public void SetAddress (String sn, String city, String country){
        StreetName=sn;
        City=city;
        Country=country;
    }
    
    public String getStreetName(){
        return StreetName;
    }
    
    public String getCity(){
        return City;
    }
    
    public String getCountry(){
        return Country;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }
    
    public int getZipCode() {
       return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode=zipCode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.StreetName, this.City, this.Country);
    }

    private void setStreetName(String sn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    



}
