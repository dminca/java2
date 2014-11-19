/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author student
 */
public class UserController {
    private static UserController singleton;
    
    private Connection con;
    
    private PreparedStatement ps1,ps2;
    
    private UserController(Connection con){
        try {
            this.con = con;
            ps1 = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps2 = con.prepareStatement("INSERT INTO users VALUES (NULL,?,?)");
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static UserController getInstance(Connection con){
        if(singleton == null){
            singleton = new UserController(con);
        }
        return singleton;
    }
    
    public User getUser(String username){
        try{
            ps1.setString(1, username);
            ResultSet rs = ps1.executeQuery();
            
            if(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setParola(rs.getString("parola"));
                return u;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void adaugaUser(User user){
        try {
            ps2.setString(1, user.getUsername());
            ps2.setString(2, user.getParola());
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
