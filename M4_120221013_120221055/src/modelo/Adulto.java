/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import tads.ObserverPessoa;
import tads.Subject;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class Adulto extends Pessoa implements ObserverPessoa {

    public Adulto(Subject subject) {
        super(subject);
        this.prioridade = 1;
    }

    @Override
    public String toString() {
        return "A(" + this.pisoOrigem + "," + this.pisoDestino + ")";
    }
}
