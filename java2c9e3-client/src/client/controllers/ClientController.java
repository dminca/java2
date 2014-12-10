/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.controllers;

import client.obspattern.PersoanaListener;
import client.obspattern.PersoanaSubject;
import dto.Persoana;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.InterfataRemote;

/**
 *
 * @author student
 */
public class ClientController implements PersoanaSubject{
    private static ClientController singleton;
    private Registry registry;
    private InterfataRemote server;
    
    private ArrayList<PersoanaListener> listeners = new ArrayList<PersoanaListener>();
    
    private ClientController(){
        try {
            registry = LocateRegistry.getRegistry(4321);
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
    
    public void adaugaPersoana(String nume,String prenume){
        try {
            server.adaugaPersoana(new Persoana(nume, prenume));
            notifyPersoanaListeners();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Persoana> getPersoane(){
        try {
            return server.getPersoane();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void stergePersoana(Persoana p){
        try {
            server.stergePersoana(p);
            notifyPersoanaListeners();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addPersoanaListener(PersoanaListener p) {
        listeners.add(p);
    }

    @Override
    public void notifyPersoanaListeners() {
        for(PersoanaListener p: listeners){
            p.listaModificata();
        }
    }
}
