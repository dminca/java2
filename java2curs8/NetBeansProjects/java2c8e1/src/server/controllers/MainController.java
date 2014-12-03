/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.controllers;

import dto.Produs;
import dto.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import server.controllers.exceptions.NonexistentEntityException;
import server.db.ProdusDB;
import server.db.UserDB;

/**
 *
 * @author student
 */
public class MainController {
    private static MainController singleton;
    
    private EntityManagerFactory emf;
    private UserDBJpaController userController;
    private ProdusDBJpaController produsController;
    
    private MainController(){
        emf = Persistence.createEntityManagerFactory("java2c8e1PU");
        userController = new UserDBJpaController(emf);
        produsController = new ProdusDBJpaController(emf);
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public boolean inregistrare(String username,String parola){
        UserDB user = userController.getUserByUsername(username);
        
        if(user != null){
            return false;
        }else{
            user = new UserDB();
            user.setUsername(username);
            user.setParola(parola);
            userController.create(user);
            return true;
        }
    }
    
    public User conectare(String username,String parola){
        UserDB user = userController.getUserByUsername(username);
        
        if(user!=null){
            if(user.getParola().equals(parola)){
                return new User(user.getId(), user.getUsername(), user.getParola());
            }
        }
        
        return null;
    }
    
    public void adaugaProdus(String nume){
        ProdusDB p = new ProdusDB();
        p.setNume(nume);
        produsController.create(p);
    }
    
    public ArrayList<Produs> getProduse(){
        ArrayList<Produs> produse = new ArrayList<>();
        
        List<ProdusDB> list = produsController.findProdusDBEntities();
        for(ProdusDB p: list){
            produse.add(new Produs(p.getId(), p.getNume()));
        }
        
        return produse;
    }
    
    public void stergeProdus(Produs p){
        try {
            produsController.destroy(p.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
