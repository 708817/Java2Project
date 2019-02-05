/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Daniel U. Torralba 
 * CS182-1P / BT2 
 * January 25, 2019
 *
 */
public class ICOCEELS2 extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        window = primaryStage; 
        window.getIcons().add(new Image("File:mapua.png")); 
        // Set Scene
        Menu scenes = new Menu();
        window.setTitle("ICOCELS v2");
        window.setScene(scenes.loginMethod(window));
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
