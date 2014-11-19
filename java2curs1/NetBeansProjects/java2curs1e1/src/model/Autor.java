/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author student
 */
public class Autor implements java.io.Serializable{
    private String nume;
    private final ArrayList<Carte> carti = new ArrayList<>();

    public Autor() {
    }

    public Autor(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public ArrayList<Carte> getCarti() {
        return carti;
    }
    
    public void adaugaCarte(Carte c){
        this.carti.add(c);
    }
    
    public void eliminaCarte(Carte c){
        this.carti.remove(c);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.nume);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.nume, other.nume)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nume;
    }
    
    
}
