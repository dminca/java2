/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.controllers;

import dto.Mesaj;
import dto.Produs;
import dto.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class ClientController {
    private static ClientController singleton;
    
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    private ClientController(){
        try {
            socket = new Socket("192.168.1.200", 4321);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ClientController getInstance(){
        if(singleton == null){
            singleton = new ClientController();
        }
        return singleton;
    }
    
    public boolean inregistrare(String username, String parola){
        try {
            out.writeObject(new Mesaj(Mesaj.REQUEST_INREGISTRARE));
            out.writeObject(new User(username, parola));
            Mesaj m= (Mesaj) in.readObject();
            if(m.getCod() == Mesaj.RESPONSE_ACCEPT) return true;
        } catch (Exception ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public User conectare(String username, String parola){
        try{
            out.writeObject(new Mesaj(Mesaj.REQUEST_CONECTARE));
            out.writeObject(new User(username, parola));
            User user = (User) in.readObject();
            return user;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void adaugaProdus(String nume){
        try{
            out.writeObject(new Mesaj(Mesaj.REQUEST_ADAUGA_PRODUS));
            out.writeObject(new Produs(nume));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Produs> getProduse(){
        try{
            out.writeObject(new Mesaj(Mesaj.REQUEST_GET_PRODUSE));
            ArrayList<Produs> produse = (ArrayList<Produs>) in.readObject();
            return produse;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void stergeProdus(Produs p){
        try{
            out.writeObject(new Mesaj(Mesaj.REQUEST_STERGE_PRODUS));
            out.writeObject(p);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
