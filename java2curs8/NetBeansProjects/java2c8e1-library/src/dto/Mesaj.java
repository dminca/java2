/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dto;

/**
 *
 * @author student
 */
public class Mesaj implements java.io.Serializable{
    public static final int REQUEST_INREGISTRARE = 1;
    public static final int REQUEST_CONECTARE = 4;
    public static final int REQUEST_ADAUGA_PRODUS = 5;
    public static final int REQUEST_GET_PRODUSE = 6;
    public static final int REQUEST_STERGE_PRODUS = 7;
    
    public static final int RESPONSE_ACCEPT = 2;
    public static final int RESPONSE_FAIL = 3;
    
    private int cod;

    public Mesaj() {
    }

    public Mesaj(int cod) {
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    
}
