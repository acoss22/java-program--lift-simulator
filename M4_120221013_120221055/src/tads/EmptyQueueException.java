/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class EmptyQueueException extends RuntimeException {

    /**
     * Creates a new instance of <code>EmptyQueueException</code> without detail
     * message.
     */
    public EmptyQueueException() {
        super("The queue is empty");
    }

    /**
     * Constructs an instance of <code>EmptyQueueException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyQueueException(String msg) {
        super(msg);
    }
}
