/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class ServerThread extends Thread{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public ServerThread(Socket socket){
        try {
            this.socket = socket;
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        try{
            while(true){
                String mesaj = in.readLine();
                Server.trimiteMesajCatreTotiClientii(mesaj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void trimiteMesaj(String mesaj){
        out.println(mesaj);
    }
}
