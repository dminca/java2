/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

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
public class Client {
    private static Registry registry;
    public static InterfataRemote server;
    public static void main(String [] args){
        try {
            registry = LocateRegistry.getRegistry("192.168.1.200",4321);
            server = (InterfataRemote) registry.lookup("server");
            new MainFrame().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
