/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Interfaces.SSN;

/**
 *
 * @author 215210704
 */
public class SSNImpl implements SSN{
    private String SSNnum;
    private char SSNClass;

    public SSNImpl(String SSNnum, char SSNClass) {
        setSSNnum(SSNnum);
        setSSNClass(SSNClass);
    }
    public SSNImpl(){
        
    }
    
    @Override
    public String getSSNnum() {
        return SSNnum;
    }

    @Override
    public void setSSNnum(String SSNnum) {
        if(SSNnum.charAt(3)=='-'&&SSNnum.charAt(6)=='-')
        this.SSNnum=SSNnum;
        else
            throw new IllegalArgumentException("SSN must be in format 999-99-999 A");
    }

    @Override
    public char getSSNClass() {
        return SSNClass;
    }

    @Override
    public void setSSNClass(char SSNClass) {
        this.SSNClass=SSNClass;
    }

    @Override
    public String toString() {
        return String.format("SSN: %s %c",getSSNnum(),getSSNClass());
    }
    
    
}
