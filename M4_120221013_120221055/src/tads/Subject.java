/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;


/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public interface Subject {

    public void register(ObserverPessoa o);

    public void unregister(ObserverPessoa o);

    public void notifyObservers();
}
