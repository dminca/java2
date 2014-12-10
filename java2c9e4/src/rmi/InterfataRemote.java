/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import dto.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author student
 */
public interface InterfataRemote extends Remote{
    public User login(User user) throws RemoteException;
}
