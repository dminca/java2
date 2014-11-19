/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controllers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.AngajatDB;
import obspattern.AngajatListener;
import obspattern.AngajatSubject;

/**
 *
 * @author student
 */
public class MainController implements AngajatSubject{
    private static MainController singleton;
    
    private EntityManagerFactory emf;
    private AngajatDBJpaController angajatController;
    
    private ArrayList<AngajatListener> angajatListener = new ArrayList<>();
    
    private MainController(){
        emf = Persistence.createEntityManagerFactory("java2c4e3PU");
        angajatController = new AngajatDBJpaController(emf);
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public void adaugaAngajat(String nume,String prenume){
        AngajatDB angajat = new AngajatDB();
        angajat.setNume(nume);
        angajat.setPrenume(prenume);
        angajatController.create(angajat);
        notifyAngajatListener();
    }
    
    public List<AngajatDB> getAngajati(){
        return angajatController.findAngajatDBEntities();
    }
    
    public void stergeAngajat(AngajatDB a){
        try {
            angajatController.destroy(a.getId());
            notifyAngajatListener();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addAngajatListener(AngajatListener al) {
        angajatListener.add(al);
    }

    @Override
    public void notifyAngajatListener() {
        for(AngajatListener al: angajatListener){
            al.listaModificata();
        }
    }
}
