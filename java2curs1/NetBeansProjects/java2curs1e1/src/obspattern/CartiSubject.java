/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package obspattern;

/**
 *
 * @author student
 */
public interface CartiSubject {
    public void addCartiListener(CartiListener cl);
    public void notifyCartiListeners();
}
