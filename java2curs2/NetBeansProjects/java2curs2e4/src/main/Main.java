/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/java2curs2","root","");
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM java2curs2.persoane");
            while(rs.next()){
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String cnp = rs.getString("cnp");
                
                System.out.println(id+" "+nume+" "+cnp);
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
