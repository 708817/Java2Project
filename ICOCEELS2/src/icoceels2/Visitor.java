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
import javafx.scene.control.TextField;
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
public class Visitor extends Person {
    private String[] type = new String[]{"Logout", "Close"}; // For jOptionPane options only
    private File file;
    private String containerNum;
    
    // Input Visitor's container number depending on the user's choice
    public Visitor() {
        role = "Visitor";
    }
    public void findFile(int getText, Stage window) {
        this.window = window;
        new File("MapuaLogInOut/Visitor/").mkdirs(); // If directory doesn't exist, create???
            try {
                if (getText >= 0 && getText <= 30) {
                    // Will check if ID exists or not. See checkNum method
                    input = getText;
                    checkNum();
                } else {
                    Popup.display("Error", "Container Number Out of Bounds");
                    
                }
            } catch (Exception e) {
            }
    }
    

    private void displayInfo() throws FileNotFoundException, IOException {
        // Create a Scanner for the text file
        Scanner in = new Scanner(file);
        
        // Set elements of each array with Student/Staff information per line
        for (int m = 0; m < 5; m++) {
            personInfoArray.add(in.nextLine());
        }
        in.close();
        
        // Display Information from array and choose whether to LogIn/LogOut or Close display
        
        statusMethod(role, personInfoArray);
//        window.setScene(scene.statusMethod(role, name, personInfoArray, file));
    }
    
    // Checks container number. 
    private void checkNum() throws FileNotFoundException, IOException {
        File openFile = new File("MapuaLogInOut/Visitor/" + input + ".txt");
        file = openFile;
        if (openFile.exists()) {
            // Display if it's already occupied by a Visitor. See displayInfo() method.
            displayInfo();
        } else {
            // Store information on vacant container. See storeInfo() method.
            visitMethod();
        }
    }
    

    
    public void findLog(String role) {
//        new File("MapuaLogInOut/Visitor/" + role + "Log").mkdirs();
        do {
            try {
                File file = new File("MapuaLogInOut/Visitor/" + role + "Log.txt");

                if (!file.exists()) {
                    PrintWriter newFile = new PrintWriter(file); // create new file
                    newFile.close();
                }
                else {
                    this.role = role;
                    this.file = file;
                    displayLog(file);
                    waiter = false;
                }
            } catch (Exception e) {
                break;
            }
        } while (waiter != false);
    }
    
    public void displayLog(File file) throws FileNotFoundException {
        popup.display(file, role);
    }
    
    
    // Store information to the newly occupied container
    
    public void statusMethod(String role, 
            ArrayList<String> personInfoArray) {
        this.role = role;
        this.personInfoArray = personInfoArray;
        
        StackPane visitorLayout = formatVisitor(window);
        Scene statusScene = new Scene(visitorLayout, 320, 350);
        window.setScene(statusScene);
    }
    
    public void visitMethod() {
        containerNum = Integer.toString(input);
        
        StackPane visitLayout = formatVisiting();
        Scene visitScene = new Scene(visitLayout, 320, 350);
        window.setScene(visitScene);
    }
    
    private StackPane formatVisiting() {
        personInfoArray.clear();
        
        Label nameLabel = new Label("Name:");
        Label idLabel = new Label("Type of ID Surrendered:");
        Label reasonLabel = new Label("Reason for Visit:");
        nameLabel.setFont(Font.font(15));
        idLabel.setFont(Font.font(15));
        reasonLabel.setFont(Font.font(15));
        
        TextField nameTextField = new TextField();
        TextField idTextField = new TextField();
        TextField reasonTextField = new TextField();
        nameTextField.setFont(Font.font(15));
        idTextField.setFont(Font.font(15));
        reasonTextField.setFont(Font.font(15));
        
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            if (!nameTextField.getText().equals("")
                    && !idTextField.getText().equals("")
                    && !reasonTextField.getText().equals("")) {
                System.out.println(containerNum);
                personInfoArray.add(containerNum);
                personInfoArray.add(nameTextField.getText());
                personInfoArray.add(idTextField.getText());
                personInfoArray.add(reasonTextField.getText());
                personInfoArray.add("");
                Updater updater = new Updater();

                try {
                    updater.overwriteStatusV(personInfoArray, file);
                } catch (IOException ex) {
                    Popup.display("Error", "File not found.");
                }
                Menu scenes = new Menu();
                window.setScene(scenes.mainMethod(window));
            }
        });
        saveButton.setFont(Font.font(15));
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            Menu scenes = new Menu();
            window.setScene(scenes.mainMethod(window));
        });
        cancelButton.setFont(Font.font(15));
        
        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(saveButton, cancelButton);
        buttonHBox.setAlignment(Pos.CENTER);
        
        GridPane recordGridPane = new GridPane();
        recordGridPane.add(nameLabel, 0, 0);
        recordGridPane.add(nameTextField, 0, 1);
        recordGridPane.add(idLabel, 0, 2);
        recordGridPane.add(idTextField, 0, 3);
        recordGridPane.add(reasonLabel, 0, 4);
        recordGridPane.add(reasonTextField, 0, 5);
        recordGridPane.add(buttonHBox, 0, 6);
        recordGridPane.setPadding(new Insets(10,10,10,10));
        recordGridPane.setHgap(10);
        recordGridPane.setVgap(10);
        recordGridPane.setAlignment(Pos.CENTER);
        
        VBox infoVBox = new VBox(5);
        infoVBox.getChildren().addAll(recordGridPane);
        infoVBox.setPadding(new Insets(10,10,10,10));
        infoVBox.setAlignment(Pos.CENTER);
        
        StackPane visitingLayout = new StackPane();
        visitingLayout.getChildren().addAll(infoVBox);
        
        return visitingLayout;
    }
    
    
    private StackPane formatVisitor(Stage window) {
        
        Label containerLabel = new Label("Container Number: ");
        Label nameLabel = new Label("Name: ");
        Label idLabel = new Label("ID Surrendered: ");
        Label reasonLabel = new Label("Reason: ");
        Label dateLabel = new Label("Log-in Date: ");
        containerLabel.setFont(Font.font(15));
        nameLabel.setFont(Font.font(15));
        idLabel.setFont(Font.font(15));
        reasonLabel.setFont(Font.font(15));
        dateLabel.setFont(Font.font(15));
        
        Label visitorContainer = new Label(personInfoArray.get(0));
        Label visitorName = new Label(personInfoArray.get(1));
        Label visitorID = new Label(personInfoArray.get(2));
        Label visitorReason = new Label(personInfoArray.get(3));
        Label visitorDate = new Label(personInfoArray.get(4));
        visitorContainer.setFont(Font.font(15));
        visitorName.setFont(Font.font(15));
        visitorID.setFont(Font.font(15));
        visitorReason.setFont(Font.font(15));
        visitorDate.setFont(Font.font(15));
        
        Button logoutButton = new Button("Log-Out");
        logoutButton.setOnAction(e -> {
            Updater updater = new Updater();
            try {
                updater.updateLogV(personInfoArray, file, true);
                Popup.display("Log-Out Info", "Log-Out Successful");
                Menu scenes = new Menu();
                window.setScene(scenes.mainMethod(window));
            } catch (IOException ex) {
                Popup.display("Error", "File Not Found.");
            }
        });
        logoutButton.setFont(Font.font(15));
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e-> {
            Menu scenes = new Menu();
            window.setScene(scenes.mainMethod(window));
        });
        closeButton.setFont(Font.font(15));
        
        GridPane infoGridPane = new GridPane();
        infoGridPane.add(containerLabel, 0, 0);
        infoGridPane.add(nameLabel, 0, 1);
        infoGridPane.add(idLabel, 0, 2);
        infoGridPane.add(reasonLabel, 0, 3);
        infoGridPane.add(dateLabel, 0, 4);
        infoGridPane.add(visitorContainer, 1, 0);
        infoGridPane.add(visitorName, 1, 1);
        infoGridPane.add(visitorID, 1, 2);
        infoGridPane.add(visitorReason, 1, 3);
        infoGridPane.add(visitorDate, 1, 4);
        infoGridPane.setAlignment(Pos.CENTER);
        
        HBox buttonsHBox = new HBox(5);
        buttonsHBox.getChildren().addAll(logoutButton, closeButton);
        buttonsHBox.setAlignment(Pos.CENTER);
        
        VBox infoVBox = new VBox(5);
        infoVBox.getChildren().addAll(infoGridPane, buttonsHBox);
        infoVBox.setPadding(new Insets(10,10,10,10));
        infoVBox.setAlignment(Pos.CENTER);
        
        StackPane visitorLayout = new StackPane();
        visitorLayout.getChildren().add(infoVBox);
        
        return visitorLayout;
    }
}
