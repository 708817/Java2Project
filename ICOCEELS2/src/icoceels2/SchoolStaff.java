/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icoceels2;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Daniel U. Torralba 
 * CS182-1P / BT2 
 * January 25, 2019
 *
 */
public class SchoolStaff extends Mapuan {
    // set role and level variables for use in Mapuan.java. See Mapuan.java
    public SchoolStaff() throws FileNotFoundException, IOException {
        role = "Staff";
        level = "Department";
    }
}
