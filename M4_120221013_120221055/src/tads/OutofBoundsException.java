/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tads;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class OutofBoundsException extends RuntimeException {

    public OutofBoundsException(int r) {
        super("O rank " + r + " é inválido");
    }

}
