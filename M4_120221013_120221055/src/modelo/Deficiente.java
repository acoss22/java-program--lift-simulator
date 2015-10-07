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
public class Deficiente extends Pessoa implements ObserverPessoa {

    public Deficiente(Subject subject) {
        super(subject);
        this.prioridade = 3;
    }

    @Override
    public String toString() {
        return "D(" + this.pisoOrigem + "," + this.pisoDestino + ")";
    }
}
