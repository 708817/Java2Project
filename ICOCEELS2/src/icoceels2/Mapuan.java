/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Daniel U. Torralba 
 * CS182-1P / BT2 
 * January 25, 2019
 *
 */
public class Mapuan extends Person {
    private String[] type = new String[]{"Login/Logout", "Close"}; // For jOptionPane options only
    private File file;
    public String level; // Student or Staff
    private Stage window;

    // Input School ID Number
    public void findFile(int getText, Stage window) {
        this.window = window;
        new File("MapuaLogInOut/Mapuan/" + role).mkdirs(); // If directory doesn't exist, create???
            try {
                input = getText;
                // Will check if ID exists or not. See checkID method
                checkID(getText);
            } catch (Exception e) {
            }
    }
    
    public void findLog(String role) {
        do {
            try {
                File file = new File("MapuaLogInOut/Mapuan/" + role + "Log.txt");

                if (!file.exists()) {
                    PrintWriter newFile = new PrintWriter(file); // create new file
                    newFile.close();
                }
                else {
                    this.role = role;
                    this.file = file;
                    displayLog();
                    waiter = false;
                }
            } catch (Exception e) {
                break;
            }
        } while (waiter != false);
    }
    
    public void displayLog() throws FileNotFoundException {
        popup.display(file, role);
    }

    /*  Check School ID Number if it exists. Supposedly there's a pre-determined 
    text file with a filename of a student/staff's ID number    */
    private void checkID(int input) throws FileNotFoundException, IOException {
        // Open student/staff's text file
        File openFile = new File("MapuaLogInOut/Mapuan/" + role + "/" + input 
                + ".txt");
        if (openFile.exists()) {
            file = openFile;
            displayInfo();
        } else {
            Popup.display("Alert", "ID doesn't exist", "OK");
        }
    }

    private void displayInfo() throws FileNotFoundException, IOException {
        // Create a Scanner for the text file
        Scanner in = new Scanner(file);
        
        // Set elements of each array with Student/Staff information per line
        for (int m = 0; m < 4; m++) {
            personInfoArray.add(in.nextLine());
        }
        in.close();

        // Display Information from array and choose whether to LogIn/LogOut or Close display
        statusMethod(role, level, personInfoArray);
    }
       
    public void statusMethod(String role, String level, 
        ArrayList<String> personInfoArray) {
        this.role = role;
        this.level = level;
        this.personInfoArray = personInfoArray;

        StackPane mapuanLayout = formatMapuan(window);                        
        Scene statusScene = new Scene(mapuanLayout, 320, 350);
        window.setScene(statusScene);
    }
    private StackPane formatMapuan(Stage window) {
        Label idLabel = new Label(role + " ID: ");
        Label nameLabel = new Label("Name: ");
        Label levelLabel = new Label(level + ": ");
        Label statusLabel = new Label("Login Status: ");
        
        Label mapuanID = new Label(personInfoArray.get(0));
        Label mapuanName = new Label(personInfoArray.get(1));
        Label mapuanLevel = new Label(personInfoArray.get(2));
        Label mapuanStatus = new Label(personInfoArray.get(3));
        
        idLabel.setFont(Font.font(15));
        nameLabel.setFont(Font.font(15));
        levelLabel.setFont(Font.font(15));
        statusLabel.setFont(Font.font(15));
        
        mapuanID.setFont(Font.font(15));
        mapuanName.setFont(Font.font(15));
        mapuanLevel.setFont(Font.font(15));
        mapuanStatus.setFont(Font.font(15));
        
        Label reasonLabel = new Label("Reason:");
        reasonLabel.setFont(Font.font(15));
        TextArea reasonTextArea = new TextArea();
        reasonTextArea.setPrefRowCount(3);
        if (personInfoArray.get(3).equals("Logged In")) {
            reasonTextArea.setOpacity(50);
            reasonTextArea.setDisable(true);
        }
        
        Button logMapuan = new Button("Login/Logout");
        logMapuan.setOnAction(e -> {
        // If Chosen Login/Logout   
            if (personInfoArray.get(3).equals("Logged In")) {
                personInfoArray.set(3, "Logged Off");
                Popup.display("Log Status", "Log-In Successful");
            } else {
                personInfoArray.set(3, "Logged In");
                personInfoArray.add("");
                personInfoArray.add("");
                personInfoArray.set(5, reasonTextArea.getText());
                Popup.display("Log Status", "Log-In Successful");
            }
            
            Updater updater = new Updater();
            
            try {
                // Record the Login/Logout information on the StudentLog.txt or StaffLog.txt. See updateLog() method.
                updater.updateLogM(file, personInfoArray, role);
            } catch (IOException ex) {
                Popup.display("Error", "File not found");
            }
            try {
                // Overwrite login status. See overwriteStatus() method.
                updater.overwriteStatusM();
            } catch (IOException ex) {
                Popup.display("Error", "File not found");
            }
            
            Menu scenes = new Menu();
            window.setScene(scenes.mainMethod(window));
        });
        logMapuan.setFont(Font.font(15));
        
        Button closeScene = new Button("Close");
        closeScene.setOnAction(e -> {
            Menu scenes = new Menu();
            window.setScene(scenes.mainMethod(window));
        });
        closeScene.setFont(Font.font(15));
        
        GridPane infoGridPane = new GridPane();
        infoGridPane.add(idLabel, 0, 0);
        infoGridPane.add(nameLabel, 0, 1);
        infoGridPane.add(levelLabel, 0, 2);
        infoGridPane.add(statusLabel, 0, 3);
        infoGridPane.add(mapuanID, 1, 0);
        infoGridPane.add(mapuanName, 1, 1);
        infoGridPane.add(mapuanLevel, 1, 2);
        infoGridPane.add(mapuanStatus, 1, 3);
        infoGridPane.setHgap(10);
        infoGridPane.setVgap(5);
        
        VBox reasonVBox = new VBox(5);
        reasonVBox.getChildren().addAll(reasonLabel, reasonTextArea);
        reasonVBox.setPadding(new Insets(10,0,0,0));
        
        HBox buttonHBox = new HBox(5);
        buttonHBox.getChildren().addAll(logMapuan, closeScene);
        buttonHBox.setPadding(new Insets(10,0,0,0));
        buttonHBox.setAlignment(Pos.CENTER_RIGHT);
        
        VBox infoVBox = new VBox(10);
        infoVBox.getChildren().addAll(infoGridPane, reasonVBox, buttonHBox);
        infoVBox.setPadding(new Insets(10,10,10,10));
        infoVBox.setAlignment(Pos.CENTER);
        
        StackPane mapuanLayout = new StackPane();
        mapuanLayout.getChildren().add(infoVBox);
        return mapuanLayout;
    }
    
}
