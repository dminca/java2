/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dto.Persoana;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author student
 */
public interface InterfataRemote extends Remote{
    public void adaugaPersoana(Persoana p) throws RemoteException;
    public ArrayList<Persoana> getPersoane() throws RemoteException;
    public void stergePersoana(Persoana p) throws RemoteException;
}
