/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public interface Stack<O> {

    public void push(O elem) throws FullStackException;

    public O pop() throws EmptyStackException;

    public O peek() throws EmptyStackException;

    public int size();

    public boolean isEmpty();
}
