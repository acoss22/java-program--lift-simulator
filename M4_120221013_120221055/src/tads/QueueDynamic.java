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
public class QueueDynamic<O> implements Queue<O> {

    private DNode<O> head, tail;
    private int size;

    public QueueDynamic() {
        this.head = new DNode<>(null, null, null);
        this.tail = new DNode<>(null, head, null);
        this.size = 0;
        head.setNext(tail);
    }

    @Override
    public void enqueue(O elem) throws FullQueueException {
        DNode<O> newNode = new DNode<>(elem, tail.getPrevious(), tail);
        tail.getPrevious().setNext(newNode);
        tail.setPrevious(newNode);
        size++;
    }

    @Override
    public O dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        DNode<O> aux = head.getNext();
        head.setNext(aux.getNext());
        aux.getNext().setPrevious(head);
        size--;
        return aux.getElem();
    }

    @Override
    public O peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return head.getNext().getElem();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
