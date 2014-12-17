/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.controllers;

import dto.Persoana;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import server.controllers.exceptions.NonexistentEntityException;
import server.db.PersoanaDB;

/**
 *
 * @author student
 */
public class MainController {
    private static MainController singleton;
    
    private EntityManagerFactory emf;
    private PersoanaDBJpaController persoaneController;
    
    private MainController(){
        emf = Persistence.createEntityManagerFactory("java2c9e3PU");
        persoaneController = new PersoanaDBJpaController(emf);
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public void adaugaPersoana(String nume, String prenume){
        PersoanaDB p = new PersoanaDB();
        p.setNume(nume);
        p.setPrenume(prenume);
        persoaneController.create(p);
    }
    
    public ArrayList<Persoana> getPersoane(){
        ArrayList<Persoana> persoane = new ArrayList<>();
        List<PersoanaDB> list = persoaneController.findPersoanaDBEntities();
        for(PersoanaDB p: list){
            persoane.add(new Persoana(p.getId(), p.getNume(), p.getPrenume()));
        }
        
        return persoane;
    }
    
    public void stergePersoana(int id){
        try {
            persoaneController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
