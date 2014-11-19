/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.ArrayList;
import model.Autor;
import model.Carte;
import obspattern.AutorListener;
import obspattern.AutorSubject;
import obspattern.CartiListener;
import obspattern.CartiSubject;

/**
 *
 * @author student
 */
public class MainController implements CartiSubject, AutorSubject{
    private static MainController singleton;
    
    private AutoriController autoriController;
    private CartiController cartiController;
    
    private ArrayList<CartiListener> cartiListeners = new ArrayList<CartiListener>();
    private ArrayList<AutorListener> autorListeners = new ArrayList<AutorListener>();
    
    private MainController(){
        autoriController = AutoriController.getInstance();
        cartiController = CartiController.getInstance();
    }
    
    public static MainController getInstance(){
        if(singleton == null){
            singleton = new MainController();
        }
        return singleton;
    }
    
    public void adaugaCarte(String titlu, String serie, double pret){
        cartiController.adaugaCarte(new Carte(titlu, serie, pret));
        notifyCartiListeners();
    }
    
    public ArrayList<Carte> getCarti(){
        return cartiController.getCarti();
    }
    
    public ArrayList<Autor> getAutori(){
        return autoriController.getAutori();
    }
    
    public void adaugaAutor(String nume){
        autoriController.adaugaAutor(new Autor(nume));
        notifyAutorListeners();
    }
    
    public void stergeCarte(Carte c){
        cartiController.stergeCarte(c);
        notifyCartiListeners();
    }
    
    public void stergeAutor(Autor a){
        autoriController.stergeAutor(a);
        notifyAutorListeners();
    }

    @Override
    public void addCartiListener(CartiListener cl) {
        cartiListeners.add(cl);
    }

    @Override
    public void notifyCartiListeners() {
        for(CartiListener cl: cartiListeners){
            cl.listaCartiModificata();
        }
    }

    @Override
    public void addAutorListener(AutorListener al) {
        autorListeners.add(al);
    }

    @Override
    public void notifyAutorListeners() {
        for(AutorListener al: autorListeners){
            al.listaAutoriModificata();
        }
    }
}
