/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class FullQueueException extends RuntimeException {

    /**
     * Creates a new instance of <code>FullQueueException</code> without detail
     * message.
     */
    public FullQueueException() {
        super("The queue is full");
    }

    /**
     * Constructs an instance of <code>FullQueueException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FullQueueException(String msg) {
        super(msg);
    }
}
