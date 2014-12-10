/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.controllers;

import dto.User;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.InterfataRemote;

/**
 *
 * @author student
 */
public class ClientController {
    private static ClientController singleton;
    private Registry registry;
    private InterfataRemote server;
    
    private ClientController(){
        try {
            registry = LocateRegistry.getRegistry("192.168.1.200", 4321);
            server = (InterfataRemote) registry.lookup("server");
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ClientController getInstance(){
        if(singleton == null){
            singleton = new ClientController();
        }
        return singleton;
    }
    
    public User login(String username, String parola){
        try {
            return server.login(new User(username, parola));
        } catch (RemoteException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
