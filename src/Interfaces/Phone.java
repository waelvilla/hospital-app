
package Interfaces;

/*
The phone is characterized by a countryCode, a cityCode and an extension. 
 */
public interface Phone {
    public abstract int getCountryCode();
    public abstract void setCountryCode(int countryCode);
    public abstract int getCityCode();
    public abstract void setCityCode(int cityCode);
    public abstract int getExtension();
    public abstract void setExtension(int extension);
}
