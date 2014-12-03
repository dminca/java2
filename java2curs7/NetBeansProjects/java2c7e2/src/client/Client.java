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
    public static Socket socket;
    public static PrintWriter out;
    public static BufferedReader in;
    public static void main(String [] args){
        try{
            socket = new Socket("192.168.1.200", 4321);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            
            new MainFrame().setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
