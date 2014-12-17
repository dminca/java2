/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client.obspattern;

/**
 *
 * @author student
 */
public interface PersoanaSubject {
    public void addPersoanaListener(PersoanaListener p);
    public void notifyPersoanaListeners();
}
