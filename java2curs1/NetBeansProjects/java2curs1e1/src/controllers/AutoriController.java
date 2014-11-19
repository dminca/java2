/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.ArrayList;
import model.Autor;

/**
 *
 * @author student
 */
public class AutoriController {
    private static AutoriController singleton;
    
    private final ArrayList<Autor> autori = new ArrayList<>();
    
    private AutoriController(){
        
    }
    
    public static AutoriController getInstance(){
        if(singleton == null){
            singleton = new AutoriController();
        }
        return singleton;
    }

    public ArrayList<Autor> getAutori() {
        return autori;
    }
    
    public void adaugaAutor(Autor a){
        this.autori.add(a);
    }
    
    public void stergeAutor(Autor a){
        this.autori.remove(a);
    }
    
}
