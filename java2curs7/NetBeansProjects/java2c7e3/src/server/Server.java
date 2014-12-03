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
    private static BufferedReader cons;
    private static PrintWriter out;
    public static void main(String[] args) {
        try{
            ss = new ServerSocket(4321);
            cons = new BufferedReader(new InputStreamReader(System.in));
            
            Socket socket = ss.accept();
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            
            while(true){
                String line = in.readLine();
                System.out.println("CLIENT: "+line);
                System.out.print("SERVER: ");
                line = cons.readLine();
                out.println(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
