/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class StackLinked<O> implements Stack<O> {

    private Node<O> top;
    private int size;
    private int maxCap;

    public StackLinked(int maxCap) {
        this.top = null;
        this.size = 0;
        this.maxCap = maxCap;
    }

    public StackLinked() {
        this.top = null;
        this.size = 0;
        this.maxCap = 10000;
    }

    @Override
    public void push(O elem) throws FullStackException {
        if (size == maxCap) {
            throw new FullStackException();
        }
        Node<O> aux = new Node<>(elem, null);
        aux.setNext(top);
        top = aux;
        size++;
    }

    @Override
    public O pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        O aux = top.getElement();
        top = top.getNext();
        size--;
        return aux;
    }

    @Override
    public O peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getElement();
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
