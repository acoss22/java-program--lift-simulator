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
public class PriorityQueueAdapter<O extends Comparable> implements PriorityQueue<O> {

    ArrayListDynamic<O> adaptee;
    private int size;

    public PriorityQueueAdapter() {
        adaptee = new ArrayListDynamic<>();
    }

    @Override
    public void enqueue(O elem) {
        if (isEmpty()) {
            adaptee.add(0, elem);
            size++;
        } else {
            for (int i = 0; i < adaptee.size(); i++) {
                if (elem.compareTo(adaptee.get(i)) > 0) {
                    adaptee.add(i, elem);
                    size++;
                    return;
                }
            }
            adaptee.add(size, elem);
            size++;
        }
    }

    @Override
    public O dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        O elem = adaptee.get(0);
        adaptee.remove(0);
        size--;
        return elem;
    }

    @Override
    public O peek() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        O elem = adaptee.get(0);
        return elem;
    }

    @Override
    public int size() {
        return adaptee.size();
    }

    @Override
    public boolean isEmpty() {
        return adaptee.size() == 0;
    }
    public String toString(){
        return adaptee.toString();
    }
    
    
}
