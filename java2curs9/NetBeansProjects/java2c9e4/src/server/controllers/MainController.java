/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.controllers;

import dto.User;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import server.db.UserDB;

/**
 *
 * @author student
 */
public class MainController {
    private static MainController singleton;
    
    private EntityManagerFactory emf;
    private UserDBJpaController usersController;
    
    private MainController(){
        emf = Persistence.createEntityManagerFactory("java2c9e4PU");
        usersController = new UserDBJpaController(emf);
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public User login(User user){
        UserDB u = usersController.getUserByUsername(user.getUsername());
        
        if(u != null){
            if(u.getParola().equals(user.getParola())){
                return new User(u.getId(), u.getUsername(), u.getParola());
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
