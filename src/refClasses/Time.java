package refClasses;
public class Time implements Comparable<Time>{
    
    private int hour; //0-23
    private int minute;//0-59
    private int second;//0-59
    
   
    //default constructor
    public Time(){
        //setTime(0, 0, 0); 
        this(0,0,0);
        //System.out.println("default constructor");
    }
    
    //three argument constructor
    public Time(int hour, int minute, int second){
        //System.out.println("three-argument constructor");
        setTime(hour, minute, second);        
    }
    
    //two argument constructor  
    public Time(int hour, int minute){
        //setTime(hour, minute, 0);
        this(hour,minute,0);
        //System.out.println("two-argument constructor");
    }
    
    //one argument constructor  
    public Time(int h){
        this(h, 0,0);
        //System.out.println("one-argument constructor");
    }
    
    //copy constructor
    public Time(Time t){
        this(t.getHour(), t.getMinute(),t.getSecond());
    }
    
    
    public void setHour (int h){
        if ((h>=0) &&(h<24))
            this.hour = h;
        else
            throw new IllegalArgumentException ("hour was out of range");
    }
     public int getHour(){
         return hour;
     }
    
    public void setMinute (int minute){
        if ((minute>=0) &&(minute<60))
            this.minute = minute;
        else
            throw new IllegalArgumentException ("minute was out of range");
    }
    public int getMinute(){
         return minute;
     }
    
    public void setSecond (int s){
        if ((s>=0) &&(s<60))
            second = s;
        else
            throw new IllegalArgumentException ("second was out of range");
    }
    public int getSecond(){
         return second;
     }
    
    public void setTime (int h, int m, int s){
        setHour(h);
        setMinute(m);
        setSecond(s);
    }
    
    public String toString(){
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
    
    
    
    public void printTime (String name){
        System.out.println(name+" Universal time: "+this.toString());
        System.out.println(name+" Standard time (this): "+this);
    }

    @Override
    public int compareTo(Time t) {
        if(this.getHour()!=t.getHour())
            return this.getHour()-t.getHour();
        else if(this.getMinute()!=t.getMinute())
            return this.getMinute()-t.getMinute();
        else if(this.getSecond()!=t.getSecond())
            return this.getSecond()-t.getSecond();
        
        else return -9999;
    }
    
}

