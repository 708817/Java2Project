/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Daniel U. Torralba 
 * CS182-1P / BT2 
 * January 25, 2019
 *
 */
public class Person extends ICOCEELS2 {
    public String name, reason, date, role;
    public boolean logstat; 
    public boolean waiter = true; // For do/while loop. See findFile() method.
    public int input, i;
//    public String[] personInfoArray = new String[7];
    public ArrayList<String> personInfoArray = new ArrayList<String>();
    public String[] logInfoArray = new String[999];
    public Popup popup = new Popup();
   
        // Get current date and time
    public void getDate() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string 
        // representation of a date with the defined format.
        this.date = df.format(today);
        
        // Source: https://tinyurl.com/y99jyxlw
    }
    
}