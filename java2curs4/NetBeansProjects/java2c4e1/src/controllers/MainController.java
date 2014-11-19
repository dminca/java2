/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class MainController {
    private static MainController singleton;
    
    private PreparedStatement ps1;
    
    private MainController(){
        try {
            Connection con = ConnectionController.getInstance().getConnection();
            ps1 = con.prepareStatement("INSERT INTO produse VALUES (NULL,?)");
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public void adaugaProdus(String nume){
        try {
            TransactionManager.getInstance().startTransaction();
            ps1.setString(1, nume);
            ps1.executeUpdate();
        } catch (SQLException ex) {
            try {
                TransactionManager.getInstance().releaseTransaction(false);
            } catch (SQLException ex1) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                TransactionManager.getInstance().releaseTransaction(true);
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
