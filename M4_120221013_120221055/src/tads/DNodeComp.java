/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;


/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class DNodeComp<O extends Comparable> implements Comparable<O> {

    private DNodeComp<O> next, previous;
    private O elem;

    public DNodeComp(O elem, DNodeComp<O> previous, DNodeComp<O> next) {
        this.next = next;
        this.previous = previous;
        this.elem = elem;
    }

    public DNodeComp<O> getNext() {
        return next;
    }

    public void setNext(DNodeComp<O> next) {
        this.next = next;
    }

    public DNodeComp<O> getPrevious() {
        return previous;
    }

    public void setPrevious(DNodeComp<O> previous) {
        this.previous = previous;
    }

    public O getElem() {
        return elem;
    }

    public void setElem(O elem) {
        this.elem = elem;
    }

    @Override
    public int compareTo(O elem) {
        return this.elem.compareTo(elem);
    }
}
