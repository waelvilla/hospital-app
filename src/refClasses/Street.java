/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refClasses;


/**
 *
 * @author akoubaa
 */
public class Street {
    

    public String name;
        public int number;
    public String quarter;
    
    public Street (String na,int n){
        setNumber(n);
        setName(na);
        
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    @Override
    public String toString() {
        return String.format("%d %s Street", this.number, this.name);
    }
    
    
 
}
