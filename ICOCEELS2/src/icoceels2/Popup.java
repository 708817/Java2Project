/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Daniel U. Torralba 
 * CS182-1P / BT2 
 * January 25, 2019
 *
 */
public class Popup {
    String check, disp;
    String array[] = new String[999];
    
    public static void display(String title, String message) {
        Stage window = new Stage();
        window.getIcons().add(new Image("File:mapua.png")); 
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        
        Label content = new Label();
        content.setText(message);
        Button okBtn = new Button("OK");
        okBtn.setOnAction(e -> {
            window.close();
        });
        
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(content, okBtn);
        mainLayout.setPadding(new Insets(10,10,10,10));
        mainLayout.setAlignment(Pos.CENTER);
        
        Scene mainScene = new Scene(mainLayout);
        window.setScene(mainScene);
        window.showAndWait();
    }
    
    public static void display(String title, String message, String button) {
        Stage window = new Stage();
        
        window.getIcons().add(new Image("File:mapua.png")); 
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        
        Label content = new Label();
        content.setText(message);
        Button okBtn = new Button(button);
        okBtn.setOnAction(e -> {
            window.close();
        });
        
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(content, okBtn);
        mainLayout.setPadding(new Insets(10,10,10,10));
        mainLayout.setAlignment(Pos.CENTER);
        
        Scene mainScene = new Scene(mainLayout);
        window.setScene(mainScene);
        window.showAndWait();
    }
    
    public void display(File file, String role) throws FileNotFoundException {
        // Set the details to display
        Scanner in = new Scanner(file);
        int m = 0;
        while(in.hasNext()) {
            array[m] = in.nextLine();
            m++;
        }
        in.close();
        
        m = 1;
        disp = array[0] + "\n";
        while(array[m] != null) {
            disp = disp + array[m] + "\n";
            m++;
        }
        
        // Create the stage
        Stage window = new Stage();
        
        window.getIcons().add(new Image("File:mapua.png")); 
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(role + " Record");
        
        Label content = new Label();
        content.setText(disp);
        Button okBtn = new Button("Close");
        okBtn.setOnAction(e -> {
            window.close();
        });
        
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(content, okBtn);
        mainLayout.setPadding(new Insets(10,10,10,10));
        mainLayout.setAlignment(Pos.CENTER);
        
        Scene mainScene = new Scene(mainLayout);
        window.setScene(mainScene);
        window.showAndWait();
    }
}