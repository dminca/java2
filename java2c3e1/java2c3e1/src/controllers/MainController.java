/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Angajat;
import model.User;
import utils.Encrypt;

/**
 *
 * @author student
 */
public class MainController {
    private static MainController singleton;
    
    private Connection con;
    
    private AngajatController angajatController;
    private UserController userController;
    
    private MainController(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/java2curs3","root","");
            
            angajatController = AngajatController.getInstance(con);
            userController = UserController.getInstance(con);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public boolean inregistrare(String username, String parola){
        User u = userController.getUser(username);
        
        if(u == null){
            u = new User(username, Encrypt.encryptWithMD5(parola));
            userController.adaugaUser(u);
            return true;
        }
        
        return false;
    }
    
    public User login(String username,String parola){
        User u = userController.getUser(username);
        
        if(u != null){
            if(u.getParola().equals(Encrypt.encryptWithMD5(parola))){
                return u;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
    public void adaugaAngajat(String nume){
        angajatController.adaugaAngajat(new Angajat(nume));
    }
    
    public ArrayList<Angajat> getAngajati(){
        return angajatController.getAngajati();
    }
    
    public void stergeAngajat(Angajat a){
        angajatController.stergeAngajat(a.getId());
    }
    
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
