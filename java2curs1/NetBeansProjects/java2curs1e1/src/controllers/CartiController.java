/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.ArrayList;
import model.Carte;

/**
 *
 * @author student
 */
public class CartiController {
    private static CartiController singleton;
    
    private final ArrayList<Carte> carti = new ArrayList<Carte>();
    
    private CartiController(){
        
    }
    
    public static CartiController getInstance(){
        if(singleton == null){
            singleton = new CartiController();
        }
        
        return singleton;
    }

    public ArrayList<Carte> getCarti() {
        return carti;
    }
    
    public void adaugaCarte(Carte c){
        this.carti.add(c);
    }
    
    public void stergeCarte(Carte c){
        this.carti.remove(c);
    }
    
}
