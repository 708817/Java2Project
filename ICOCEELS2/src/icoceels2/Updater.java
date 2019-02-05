/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Daniel U. Torralba 
 * CS182-1P / BT2 
 * January 25, 2019
 *
 */
public class Updater extends Person {
    
    File file;
    
    // Update the StudentLog or StaffLog
    public void updateLogM(File file, ArrayList<String> updatedArray, String roleHandle) throws FileNotFoundException, IOException {
        this.file = file;
        personInfoArray = updatedArray;
        role = roleHandle;
            
        // Set file to open StudentLog or StaffLog
        file = new File("MapuaLogInOut/Mapuan/" + role + "Log.txt");
        
        // If the log doesn't exist, create a new one
        if (!file.exists()) {
            PrintWriter newFile = new PrintWriter(file); // create new file
            newFile.close();
        }

        // Append the log for logging in or logging out
        FileWriter fw = new FileWriter(file, true);
        // BufferedWriter writer gives better performance
        BufferedWriter bw = new BufferedWriter(fw);
        
        // Update Date variable
        getDate();
        
        // Writes the log details
        String write = "[" + date + "]" + " " + personInfoArray.get(1) + " (" 
                + personInfoArray.get(0) + ")" + ": " + personInfoArray.get(3) ;
        if (personInfoArray.get(3).equals("Logged In")) {
            bw.write(write + " REASON: " + personInfoArray.get(5));
            bw.newLine();
        }
        else {
            bw.write(write);
            bw.newLine();
        }
        
        //Closing BufferedWriter Stream
        bw.close();
    }
    
    // Overwrite the Student/Staff status. Method to replace old file with new file
    public void overwriteStatusM() throws FileNotFoundException, IOException {
        // Delete the file to overwrite.
        file.delete();

        // Recreate the file to write the Student/Staff Information
        PrintWriter newFile = new PrintWriter(file);
        for (int i = 0; i < 4; i++) {
            newFile.println(personInfoArray.get(i));
        }
        newFile.close();

        // Append the content to file
        FileWriter fw = new FileWriter(file, true);
        // BufferedWriter writer give better performance
        BufferedWriter bw = new BufferedWriter(fw);
        getDate();
        bw.write(date);
        //Closing BufferedWriter Stream        
        bw.close();
    }
    
    public void overwriteStatusV(ArrayList<String> personInfoArray, File file) throws FileNotFoundException, IOException {
        getDate();
        personInfoArray.set(4, date);
        
        // Create new file to write the Visitor information
        PrintWriter newFile = new PrintWriter(file); 
        for (i = 0; i < 5; i++) {
            newFile.println(personInfoArray.get(i));
        }
        newFile.close();
        
        // Log in the Visitor and record his/her exit and time with reason
        updateLogV(personInfoArray, file, false);
        Popup.display("Log-in", "Log-In Successful");
        
    }
    
    public void updateLogV(ArrayList<String> personInfoArray, File file, boolean logoff) throws FileNotFoundException, IOException {
        // Delete the file of the Logged Off visitor
        if (logoff == true) {
            file.delete();
        }
        
        // Open the VisitorLog file to update the log
        File log = new File("MapuaLogInOut/Visitor/VisitorLog.txt");
        if (!log.exists()) {
            PrintWriter newFile = new PrintWriter(log); // create new file
            newFile.close();
        }

        // Append the content to file
        FileWriter fw = new FileWriter(log, true);
        // BufferedWriter writer give better performance
        BufferedWriter bw = new BufferedWriter(fw);
        // Update Date variable
        getDate();
        if (!file.exists()) {
            bw.write("[" + date + "]" + " " + personInfoArray.get(1) 
                    + ": Logged Off (CN: " + personInfoArray.get(0) + ")");
            bw.newLine();
        } else {
            bw.write("[" + date + "]" + " " + personInfoArray.get(1) 
                    + ": Logged In (CN: " + personInfoArray.get(0) + ")" + " REASON: " 
                    + personInfoArray.get(3));
            bw.newLine();
        }
        // Closing BufferedWriter Stream
        bw.close();
    }
}
