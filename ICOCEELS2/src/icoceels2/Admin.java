/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import javafx.stage.Stage;

/**
 *
 * @author Daniel
 */
public class Admin {
    String username, password;
    String admin = "mapuafrancis", pass = "pass";
    
    public boolean Admin(String username, String password) {
        this.username = username;
        this.password = password;
        
        return loginAdmin();
    }
    
    private boolean loginAdmin() {
        boolean checker;
        if (username.equals(admin)
                && password.equals(pass)) {
            checker = true;
        } else {
            checker = false;
        }
        
        return checker;
    }
}
