/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.ProdusDB;

/**
 *
 * @author student
 */
public class MainController {
    private static MainController singleton;
    
    private EntityManagerFactory emf;
    private ProdusDBJpaController produsController;
    
    private MainController(){
        emf = Persistence.createEntityManagerFactory("java2c4e2PU");
        produsController = new ProdusDBJpaController(emf);
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public void adaugaProdus(String nume){
        ProdusDB produs = new ProdusDB();
        produs.setNume(nume);
        produsController.create(produs);
    }
}
