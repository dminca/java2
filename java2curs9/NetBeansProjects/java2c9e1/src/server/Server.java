/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.InterfataRemote;

/**
 *
 * @author student
 */
public class Server extends UnicastRemoteObject implements InterfataRemote{
    private Registry registry;
    
    public Server() throws RemoteException{
        registry = LocateRegistry.createRegistry(4321);
        registry.rebind("server", this);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("SERVER");
            new Server();
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sayHello() throws RemoteException {
        System.out.println("HELLO WORLD!");
    }
    
}
