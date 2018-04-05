package core;

import Interfaces.Department;
import Interfaces.Staff;

/**
 *
 * @author Wael
 */
public class DepartmentImpl implements Department{
    private String name;
    private Staff staff;

    public DepartmentImpl(String name) {
       setName(name);
    }

    public DepartmentImpl(String name, Staff staff) {
        setName(name);
        setStaff(staff);
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
}
