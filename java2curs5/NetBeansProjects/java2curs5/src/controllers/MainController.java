/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.AngajatDB;
import model.DepartamentDB;
import model.UserDB;

/**
 *
 * @author student
 */
public class MainController {
    private static MainController singleton;
    
    private EntityManagerFactory emf;
    private UserDBJpaController userController;
    private DepartamentDBJpaController departmentController;
    private AngajatDBJpaController angajatController;
    
    private MainController(){
        emf = Persistence.createEntityManagerFactory("java2curs5PU");
        userController = new UserDBJpaController(emf);
        departmentController = new DepartamentDBJpaController(emf);
        angajatController = new AngajatDBJpaController(emf);
    }

    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public boolean inregistrare(String username, String parola){
        UserDB user = userController.getUserByUsername(username);
        
        if(user == null){
            user = new UserDB();
            user.setUsername(username);
            user.setParola(parola);
            userController.create(user);
            return true;
        }
        
        return false;
    }
    
    public UserDB login(String username, String parola){
        UserDB user = userController.getUserByUsername(username);
        
        if(user != null){
            if(user.getParola().equals(parola)){
                return user;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
    public void adaugaDepartament(String nume, UserDB user){
        DepartamentDB departament = new DepartamentDB();
        departament.setNume(nume);
        departament.setUser(user);
        departmentController.create(departament);
    }
    
    public List<DepartamentDB> getDepartamente(UserDB user){
        return departmentController.getDepartmentsForUser(user);
    }
    
    public void adaugaAngajat(String nume, DepartamentDB departament){
        AngajatDB a = new AngajatDB();
        a.setNume(nume);
        a.setDepartament(departament);
        angajatController.create(a);
    }
    
    public List<AngajatDB> getAngajati(DepartamentDB d){
        return angajatController.getAngajati(d);
    }
    
    public void stergeDepartament(DepartamentDB d){
        try{
            List<AngajatDB> angajati = getAngajati(d);
            for(AngajatDB a: angajati){
                angajatController.destroy(a.getId());
            }
            departmentController.destroy(d.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void modificaAngajat(AngajatDB a){
        try {
            angajatController.edit(a);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

