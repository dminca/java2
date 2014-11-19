/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author student
 */
public class Angajat implements java.io.Serializable{
    private int id;
    private String nume;

    public Angajat() {
    }

    public Angajat(String nume) {
        this.nume = nume;
    }
    
    public Angajat(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    
    public String toString(){
        return this.nume;
    }
}
