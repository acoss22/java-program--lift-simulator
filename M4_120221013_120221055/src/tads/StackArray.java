/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class StackArray<O> implements Stack<O> {

    private O[] array;
    private int size;
    private static final int MAX_CAP = 200; //Sujeito a mudanças

    public StackArray() {
        this.size = 0;
        array = (O[]) new Object[MAX_CAP];

    }

    public StackArray(int cap) {
        this.size = 0;
        array = (O[]) new Object[cap];

    }

    @Override
    public void push(O elem) throws FullStackException {
        if (size == array.length) {
            throw new FullStackException();
        }
        try {
            array[size] = elem;
            size++;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FullStackException();
        }

    }

    @Override
    public O pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        O aux = array[size - 1];
        array[size - 1] = null;
        size--;
        return aux;

    }

    @Override
    public O peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        O aux = array[size - 1];
        return aux;

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
