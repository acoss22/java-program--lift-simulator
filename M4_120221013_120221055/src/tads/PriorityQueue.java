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
public interface PriorityQueue<O> {

    public void enqueue(O elem) throws FullQueueException;

    public O dequeue() throws EmptyQueueException;

    public O peek() throws EmptyQueueException;

    public int size();

    public boolean isEmpty();
}
