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
    public static void main(String [] args){
        try{
            Socket socket = new Socket("", 4321);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println("HELLO WORLD!");
            String line = in.readLine();
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
