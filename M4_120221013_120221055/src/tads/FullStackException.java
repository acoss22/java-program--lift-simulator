/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class FullStackException extends RuntimeException {

    /**
     * Creates a new instance of <code>FullStackException</code> without detail
     * message.
     */
    public FullStackException() {
        super("The Stack is full");
    }

    /**
     * Constructs an instance of <code>FullStackException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FullStackException(String msg) {
        super(msg);
    }
}
