/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import dto.Persoana;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.InterfataRemote;
import server.controllers.MainController;

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
    
    public static void main(String[] args) {
        try {
            new Server();
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adaugaPersoana(Persoana p) throws RemoteException {
        MainController.getInstance().adaugaPersoana(p.getNume(), p.getPrenume());
    }

    @Override
    public ArrayList<Persoana> getPersoane() throws RemoteException {
        return MainController.getInstance().getPersoane();
    }

    @Override
    public void stergePersoana(Persoana p) throws RemoteException {
        MainController.getInstance().stergePersoana(p.getId());
    }
    
}
