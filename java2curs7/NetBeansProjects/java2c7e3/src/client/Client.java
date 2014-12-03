/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author student
 */
public class Client {
    private static  Socket socket;
    private static BufferedReader cons;
    private static BufferedReader in;
    private static PrintWriter out;
    
    public static void main(String [] args){
        try{
            socket = new Socket("192.168.1.200", 4321);
            cons = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
            while(true){
                System.out.print("CLIENT: ");
                String line = cons.readLine();
                out.println(line);
                line = in.readLine();
                System.out.println("SERVER: "+line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
