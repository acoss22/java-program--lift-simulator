/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class ArrayListDynamic<E> implements ArrayList<E> {

    private DNode<E> head;
    private DNode<E> tail;
    private int size;

    public ArrayListDynamic() {
        this.head = new DNode<>(null, null, null);
        this.tail = new DNode<>(null, head, null);
        this.head.setNext(tail);
        this.size = 0;
    }
    
    public ArrayListDynamic(int r, E elem){
        this();
        for (int i = 0; i < r; i++) {
            add(i, elem);
        }
    }

    private DNode<E> nodeAtRank(int rank) throws OutofBoundsException {

        DNode<E> nodeAux = head.getNext();

        for (int i = 0; i < rank; i++) {
            nodeAux = nodeAux.getNext();
        }

        return nodeAux;

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
        return nodeAtRank(r).getElem();

    }

    @Override
    public void add(int r, E elem) throws OutofBoundsException {
        if (r < 0 || r > size) {
            throw new OutofBoundsException(r);
        }
        DNode<E> aux = nodeAtRank(r);
        DNode<E> prev = aux.getPrevious();
        DNode<E> newNode = new DNode<>(elem, prev, aux);
        prev.setNext(newNode);
        aux.setPrevious(newNode);
        size++;
    }

    @Override
    public E remove(int r) throws OutofBoundsException {
        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        DNode<E> aux = nodeAtRank(r);
        E elem = aux.getElem();
        DNode<E> prev = aux.getPrevious();
        DNode<E> next = aux.getNext();
        prev.setNext(next);
        next.setPrevious(prev);
        size--;
        return elem;
    }

    @Override
    public E set(int r, E elem) throws OutofBoundsException {

        if (r < 0 || r >= size) {
            throw new OutofBoundsException(r);
        }
        DNode<E> nodeAux = nodeAtRank(r);
        E elemAux = nodeAux.getElem();
        nodeAux.setElem(elem);
        return elemAux;

    }
public String toString(){
    String str="";
    for (int i = size-1; i>=0; i--) {
       str+= nodeAtRank(i).getElem().toString()+"->";
    }
     return str;   
    }
}
    
 
