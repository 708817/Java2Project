/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
public class Menu extends ICOCEELS2 {
    
    private boolean login;
    private Scene loginScene, mainScene;
    
    public Scene loginMethod(Stage window) {
        Label lblUser = new Label("Admin Name");
        TextField userTextField = new TextField();

        Label passLabel = new Label("Password");
        PasswordField passTextField = new PasswordField();

        GridPane loginGridPane = new GridPane();
        loginGridPane.add(lblUser, 0, 0);
        loginGridPane.add(userTextField, 1, 0);
        loginGridPane.add(passLabel, 0, 1);
        loginGridPane.add(passTextField, 1, 1);
        loginGridPane.setAlignment(Pos.CENTER); 
        loginGridPane.setHgap(10);
        loginGridPane.setVgap(5);

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            Admin admin = new Admin();
            login = admin.Admin(userTextField.getText(), passTextField.getText());
            if (login == true) {
                Menu scene = new Menu();
                window.setScene(scene.mainMethod(window));
            } else {
                Popup.display("Error Logging In", "Log-in Failed. Try again.");
            }
        });

        VBox loginVBox = new VBox(10);
        loginVBox.getChildren().addAll(loginGridPane, loginBtn);
        loginVBox.setPadding(new Insets(10, 10, 10, 10));
        loginVBox.setAlignment(Pos.CENTER);

        StackPane loginLayout = new StackPane();
        loginLayout.getChildren().add(loginVBox);

        loginScene = new Scene(loginLayout, 320, 150);
        return loginScene;
    }
    
    public Scene mainMethod(Stage window) {
        
        TabPane mainTabPane = new TabPane();
        
        //mapuanTab
        
        Label typeLabel = new Label("Type:");
        typeLabel.setFont(Font.font(20));

        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.setValue("Student");
        typeComboBox.setMaxWidth(300);
        typeComboBox.getItems().addAll(
            "Student",
            "School Staff"
        );
        
        Label idLabel = new Label("ID Number:");
        idLabel.setFont(Font.font(20));
        TextField idTextField = new TextField();
        idTextField.setPrefSize(30, 30);
        idTextField.setFont(Font.font(15));
        
        Button displayButton1 = new Button("Display Log");
        displayButton1.setOnAction (e -> {
            if (typeComboBox.getValue().isEmpty()) {
                Popup.display("Error", "Log Not Found.");
            } else if (typeComboBox.getValue().equals("Student")) {
                try {
                    // See Student.java
                    Student student = new Student();
                    student.findLog(student.role);
                } catch (IOException ex) {
                    Popup.display("Error", "Log Not Found.");
                }
            } else if (typeComboBox.getValue().equals("School Staff")) {
                try {
                    // See SchoolStaff.java
                    SchoolStaff sstaff = new SchoolStaff();
                    sstaff.findLog(sstaff.role);
                } catch (IOException ex) {
                    Popup.display("Error", "Log Not Found.");
                }
            } else {
                Popup.display("Error", "Please input the Log-in Type", "OK");
            }
            idTextField.clear();
        });
        
        Button confirmButton1 = new Button("Confirm");
        confirmButton1.setOnAction(e -> {
            if (typeComboBox.getValue().equals("Student")) {
                try {
                    // See Student.java
                    Student student = new Student();
                    student.findFile(Integer.parseInt(idTextField.getText()), window);
                } catch (IOException ex) {
                    Popup.display("Error", "File Not Found", "OK");
                } catch (NumberFormatException ex) {
                    Popup.display("Error", "Please enter the ID", "OK");
                } catch (Exception ex) {
                    Logger.getLogger(ICOCEELS2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (typeComboBox.getValue().equals("School Staff")) {
                try {
                    // See SchoolStaff.java
                    SchoolStaff sstaff = new SchoolStaff();
                    sstaff.findFile(Integer.parseInt(idTextField.getText()),window);
                } catch (IOException ex) {
                    Popup.display("Error", "File Not Found", "OK");
                } catch (NumberFormatException ex) {
                    Popup.display("Error", "Please enter the Student ID", "OK");
                } catch (Exception ex) {
                    Logger.getLogger(ICOCEELS2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Popup.display("Error", "Please input the Log-in Type", "OK");
            }
            idTextField.clear();
        });
        
        displayButton1.setFont(Font.font(15));
        confirmButton1.setFont(Font.font(15));
        
        HBox buttonVBox1 = new HBox(10);
        buttonVBox1.getChildren().addAll(displayButton1);
        buttonVBox1.getChildren().addAll(confirmButton1);
        buttonVBox1.setAlignment(Pos.CENTER_RIGHT);
        buttonVBox1.setPadding(new Insets(15,10,10,10));
        
        GridPane mapuanGridPane = new GridPane();
        mapuanGridPane.add(typeLabel, 0, 0);
        mapuanGridPane.add(typeComboBox, 0, 1);
        mapuanGridPane.add(idLabel, 0, 2);
        mapuanGridPane.add(idTextField, 0, 3);
        mapuanGridPane.add(buttonVBox1, 0, 4);
        mapuanGridPane.setPadding(new Insets(10, 10, 10, 10));
        mapuanGridPane.setHgap(10);
        mapuanGridPane.setVgap(5);
        mapuanGridPane.setAlignment(Pos.CENTER);
        
        VBox mainVBox1 = new VBox(10);
        mainVBox1.getChildren().addAll(mapuanGridPane);
        mainVBox1.setAlignment(Pos.CENTER);
        
        Tab mapuanTab = new Tab("Campus Member");
        mapuanTab.setContent(mainVBox1);
        
        //visitorTab
        
        Label conLabel = new Label("Container Number:");
        conLabel.setFont(Font.font(15));
        TextField conTextField = new TextField();
        conTextField.setPrefSize(30, 30);
        conTextField.setFont(Font.font(15));
        
        Button displayButton2 = new Button("Display Log");
        displayButton2.setOnAction (e -> {
            Visitor visitor = new Visitor();
            visitor.findLog("Visitor");
        });
        displayButton2.setFont(Font.font(15));
        
        Button confirmButton2 = new Button("Confirm");
        confirmButton2.setOnAction(e -> {
            Visitor visitor = new Visitor();
            try {
                visitor.findFile(Integer.parseInt(conTextField.getText()), window);
            } catch (NumberFormatException ex) {
                Popup.display("Error", "Please enter the Number Container.");
            }
            conTextField.clear();
        });
        confirmButton2.setFont(Font.font(15));
        
        HBox buttonHBox2 = new HBox(10);
        buttonHBox2.getChildren().addAll(displayButton2);
        buttonHBox2.getChildren().addAll(confirmButton2);
        buttonHBox2.setAlignment(Pos.CENTER_RIGHT);
        
        GridPane visitorGridPane = new GridPane();
        visitorGridPane.add(conLabel, 0, 0);
        visitorGridPane.add(conTextField, 0, 1);
        visitorGridPane.add(buttonHBox2, 0, 2);
        visitorGridPane.setPadding(new Insets(10, 10, 10, 10));
        visitorGridPane.setHgap(10);
        visitorGridPane.setVgap(10);
        visitorGridPane.setAlignment(Pos.CENTER);
        
        VBox mainVBox2 = new VBox(5);
        mainVBox2.getChildren().addAll(visitorGridPane);
        mainVBox2.setAlignment(Pos.CENTER);
        
        Tab visitorTab = new Tab("Visitor");
        visitorTab.setContent(mainVBox2);
        
        mainTabPane.getTabs().addAll(mapuanTab, visitorTab);
        mainTabPane.tabMinWidthProperty().set(100);
        mainTabPane.tabMaxWidthProperty().set(100);
        mainTabPane.setMinWidth((100 * mainTabPane.getTabs().size()) + 55);
        mainTabPane.setPrefWidth((100 * mainTabPane.getTabs().size()) + 55);
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().add(mainTabPane);

        mainScene = new Scene(mainLayout, 320, 350);
        return mainScene;
    }
}