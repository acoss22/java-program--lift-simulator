package tads;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public interface ArrayList<E> {

    public int size();

    public boolean isEmpty();

    public E get(int r) throws OutofBoundsException;

    public void add(int r, E elem) throws OutofBoundsException;

    public E remove(int r) throws OutofBoundsException;

    public E set(int r, E elem) throws OutofBoundsException;

}
