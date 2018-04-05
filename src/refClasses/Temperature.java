package refClasses;


public class Temperature {

    private double value;
    private char unit;
    private Date date;
    
    public Temperature (double v, char u){
        setValue(v);
        setUnit(u);
    }
    
    
    public Temperature  convertToCelsius(){
        if (unit == 'F'){
            /*Temperature t = new Temperature();
            t.setValue((value-32)/1.8);
            t.setUnit('C');
            t.setDate(date);
            */
            setValue((value-32)/1.8);
            setUnit('C');
            //return new Temperature((value-32)/1.8, 'C', date);
            
        }else{
            System.out.println("Same unit, no conversion");
        }
        return this;
    }
    
    public Temperature  convertToFahrenheit(){
        if (unit =='C'){
            setValue((value*1.8)+32);
            setUnit('F');
            //return new Temperature((value*1.8)+32, 'F', date);
        }else{
            System.out.println("Same unit, no conversion");
        }
       
        return this;
    }
    
    public double getValue(){
        return value;
    }
    
    public void setValue(double value){
        this.value = value;
    }
    
    public char getUnit(){
        return unit;
    }
    
    
    public void setUnit(char u){
        if ((u=='C')||(u=='F'))
            this.unit = u;
        else throw new IllegalArgumentException("Unit is not valid");
    }
    

    public String toString(){
        return String.format("%.2f %c ", this.getValue(), this.getUnit());
    }
    
    
}
