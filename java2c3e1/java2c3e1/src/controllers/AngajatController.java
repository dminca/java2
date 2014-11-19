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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Angajat;

/**
 *
 * @author student
 */
public class AngajatController {
    private static AngajatController singleton;
    
    private Connection con;
    
    private PreparedStatement ps1,ps2,ps3;
    
    private AngajatController(Connection con){
        try {
            this.con = con;
            ps1 = con.prepareStatement("INSERT INTO angajati VALUES (NULL,?)");
            ps2 = con.prepareStatement("SELECT * FROM angajati");
            ps3 = con.prepareStatement("DELETE FROM angajati WHERE id = ?");
        } catch (SQLException ex) {
            Logger.getLogger(AngajatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static AngajatController getInstance(Connection con){
        if(singleton == null){
            singleton = new AngajatController(con);
        }
        return singleton;
    }
    
    public void adaugaAngajat(Angajat a){
        try {
            ps1.setString(1, a.getNume());
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AngajatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Angajat> getAngajati(){
        ResultSet rs = null;
        ArrayList<Angajat> angajati = new ArrayList<>();
        try {
            rs = ps2.executeQuery();
            while(rs.next()){
                Angajat a = new Angajat();
                a.setId(rs.getInt("id"));
                a.setNume(rs.getString("nume"));
                angajati.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AngajatController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AngajatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return angajati;
    }
    
    public void stergeAngajat(int id){
        try {
            ps3.setInt(1, id);
            ps3.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AngajatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
