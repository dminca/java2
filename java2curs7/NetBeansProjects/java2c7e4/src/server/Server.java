/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Server {
    private static ServerSocket ss;
    private static ArrayList<ServerThread> clienti = new ArrayList<ServerThread>();
    
    public static void main(String[] args) {
        try{
            ss = new ServerSocket(4321);
            
            while(true){
                ServerThread st = new ServerThread(ss.accept());
                st.start();
                clienti.add(st);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void trimiteMesajCatreTotiClientii(String mesaj){
        for(ServerThread st: clienti){
            st.trimiteMesaj(mesaj);
        }
    }
}
