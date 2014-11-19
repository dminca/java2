/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author student
 */
public class Server {
    private static ServerSocket ss;
    private static BufferedReader in;
    private static PrintWriter out;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ss = new ServerSocket(4321); // deschide portul
            Socket socket = ss.accept(); // asteapta pana cand un client se conecteaza, iar cand s-a conectat trimite socketul acesta
            
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String line = in.readLine();
            StringBuffer sb = new StringBuffer(line);
            sb.reverse();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
