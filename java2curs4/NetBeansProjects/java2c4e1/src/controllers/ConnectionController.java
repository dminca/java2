/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class ConnectionController {
    private static ConnectionController singleton;
    
    private Connection con;
    
    private ConnectionController(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/java2curs4","root","");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionController getInstance(){
        if(singleton == null){
            singleton = new ConnectionController();
        }
        return singleton;
    }
    
    public Connection getConnection(){
        return con;
    }
}
