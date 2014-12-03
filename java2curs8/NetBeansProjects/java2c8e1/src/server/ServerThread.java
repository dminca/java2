/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

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
import server.controllers.MainController;

/**
 *
 * @author student
 */
public class ServerThread extends Thread{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public ServerThread(Socket socket){
        try {
            this.socket = socket;
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        try{
            Mesaj m = null;
            while(true){
                m = (Mesaj) in.readObject();
                
                switch(m.getCod()){
                    case Mesaj.REQUEST_INREGISTRARE:{
                        User user = (User) in.readObject();
                        boolean b = MainController.getInstance().inregistrare(user.getUsername(), 
                                                                              user.getParola());
                    
                        Mesaj resp = null;
                        if(b){
                            resp = new Mesaj(Mesaj.RESPONSE_ACCEPT);
                        }else{
                            resp = new Mesaj(Mesaj.RESPONSE_FAIL);
                        }
                        
                        out.writeObject(resp);
                    }break;
                        
                    case Mesaj.REQUEST_CONECTARE:{
                        User user = (User) in.readObject();
                        
                        user = MainController.getInstance().conectare(user.getUsername(), 
                                                                      user.getParola());
                        
                        out.writeObject(user);
                    }break;
                        
                    case Mesaj.REQUEST_ADAUGA_PRODUS:{
                        Produs p = (Produs) in.readObject();
                        MainController.getInstance().adaugaProdus(p.getNume());
                    }break;   
                        
                    case Mesaj.REQUEST_GET_PRODUSE:{
                        ArrayList<Produs> produse = MainController.getInstance().getProduse();
                        out.writeObject(produse);
                    }break;  
                        
                    case Mesaj.REQUEST_STERGE_PRODUS:{
                        Produs p = (Produs) in.readObject();
                        MainController.getInstance().stergeProdus(p);
                    }break;    
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
