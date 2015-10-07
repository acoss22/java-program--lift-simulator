/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class Node<O> {

    private O element;
    private Node<O> next;

    public Node(O element, Node<O> next) {
        this.element = element;
        this.next = next;
    }

    public O getElement() {
        return element;
    }

    public void setElement(O element) {
        this.element = element;
    }

    public Node<O> getNext() {
        return next;
    }

    public void setNext(Node<O> node) {
        this.next = node;
    }
}
