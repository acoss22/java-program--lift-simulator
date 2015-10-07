/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class ArrayListStatic<E> implements ArrayList<E> {

    private E[] array;
    private int size;
    private static final int MAX_CAP_INICIAL = 5;

    public ArrayListStatic() {
        array = (E[]) new Object[MAX_CAP_INICIAL];
        this.size = 0;
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
    public E get(int r) throws OutofBoundsException {
        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        return array[r];
    }

    @Override
    public void add(int r, E elem) throws OutofBoundsException {
        if (r < 0 || r > size) {
            throw new OutofBoundsException(r);
        }
        if (size == array.length) {
            E[] tableAux = (E[]) new Object[2 * array.length];
            System.arraycopy(array, 0, tableAux, 0, size);
            array = tableAux;
        }
        for (int i = size; i > r; i--) {
            array[i] = array[i - 1];
        }
        array[r] = elem;
        size++;
    }

    @Override
    public E remove(int r) throws OutofBoundsException {
        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        E elem = array[r];
        for (int i = r; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null;
        return elem;
    }

    @Override
    public E set(int r, E elem) throws OutofBoundsException {
        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        E elemAux = array[r];
        array[r] = elem;
        return elemAux;
    }
}
