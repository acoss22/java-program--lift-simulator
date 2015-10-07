/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class DNode<O> {

    private DNode<O> next, previous;
    private O elem;

    public DNode(O elem, DNode<O> previous, DNode<O> next) {
        this.next = next;
        this.previous = previous;
        this.elem = elem;
    }

    public DNode<O> getNext() {
        return next;
    }

    public void setNext(DNode<O> next) {
        this.next = next;
    }

    public DNode<O> getPrevious() {
        return previous;
    }

    public void setPrevious(DNode<O> previous) {
        this.previous = previous;
    }

    public O getElem() {
        return elem;
    }

    public void setElem(O elem) {
        this.elem = elem;
    }
}
