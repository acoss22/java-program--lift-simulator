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
public class PriorityQueueDynamic<O extends Comparable> implements PriorityQueue<O> {

    private DNodeComp<O> head, tail;
    private int size;

    public PriorityQueueDynamic() {
        this.size = 0;
        this.head = new DNodeComp<>(null, null, null);
        this.tail = new DNodeComp<>(null, head, null);
        head.setNext(tail);
    }

    @Override
    public void enqueue(O elem) {
        if (isEmpty()) {
            DNodeComp<O> aux = new DNodeComp<>(elem, head, tail);
            tail.setPrevious(aux);
            head.setNext(aux);
            size++;
        } else {
            DNodeComp<O> newNode = new DNodeComp<>(elem, null, null);
            DNodeComp<O> iterador = head.getNext();
            while (iterador.getElem() != null) {

                if (newNode.compareTo(iterador.getElem()) > 0) {

                    newNode.setNext(iterador);
                    newNode.setPrevious(iterador.getPrevious());
                    iterador.getPrevious().setNext(newNode);
                    iterador.setPrevious(newNode);
                    size++;
                    return;
                }
                if(newNode.compareTo(iterador.getElem())==0){
                    newNode.setPrevious(iterador);
                    newNode.setNext(iterador.getNext());
                    iterador.getNext().setPrevious(newNode);
                    iterador.setNext(newNode);
                    size++;
                    return;
                    
                }
                iterador = iterador.getNext();
            }
            DNodeComp<O> aux = new DNodeComp<>(elem, tail.getPrevious(), tail);
            tail.getPrevious().setNext(aux);
            tail.setPrevious(aux);
            size++;
        }
    }

    @Override
    public O dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        DNodeComp<O> aux = head.getNext();
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
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        PriorityQueueDynamic<O> aux = new PriorityQueueDynamic();    
        String str="";
        aux=this;
        for (int i = 0; i < this.size; i++) {
        str+=aux.dequeue().toString();
            
        }
        return str;
    }
} 