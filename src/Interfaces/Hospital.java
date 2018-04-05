
package Interfaces;

public interface Hospital {
    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getWebLink();
    public abstract void setWebLink(String webLink);
    public abstract Address getAddress();
    public abstract void setAddress(Address address);
    public abstract Phone getPhone();
    public abstract void setPhone(Phone phone);
    public abstract String[] getDepartments();
    public abstract void loadFiles();
    public abstract void saveRecords();
    
}
