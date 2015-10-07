/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class QueueArray<O> implements Queue<O> {

    private int begin, end, capacity;
    private O[] queue;

    public QueueArray(int capacity) {
        this.begin = 0;
        this.end = 0;
        this.capacity = capacity;
        queue = (O[]) new Object[capacity];

    }

    @Override
    public void enqueue(O elem) throws FullQueueException {
        if (isFull()) {
            throw new FullQueueException();
        }
        queue[end] = elem;
        end = (end + 1) % capacity;

    }

    @Override
    public O dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        O aux = queue[begin];
        queue[begin] = null;
        begin = (begin + 1) % capacity;
        return aux;

    }

    @Override
    public O peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue[begin];

    }

    @Override
    public boolean isFull() {
        return (begin == end && queue[begin] != null);

    }

    @Override
    public int size() {
        if (isFull()) {
            return this.capacity;
        }
        return ((begin + end) % capacity);

    }

    @Override
    public boolean isEmpty() {
        return size() == 0;

    }
}
