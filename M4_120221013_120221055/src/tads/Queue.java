/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public interface Queue<O> {

    public void enqueue(O elem) throws FullQueueException;

    public O dequeue() throws EmptyQueueException;

    public O peek() throws EmptyQueueException;

    public boolean isFull();

    public int size();

    public boolean isEmpty();
}
