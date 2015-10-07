/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author André
 */
public class InvalidStateException extends Exception {

    /**
     * Creates a new instance of <code>InvalidStateException</code> without
     * detail message.
     */
    public InvalidStateException() {
    }

    /**
     * Constructs an instance of <code>InvalidStateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidStateException(String msg) {
        super(msg);
    }
}
