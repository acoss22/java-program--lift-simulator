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
public class Crianca extends Pessoa implements ObserverPessoa {

    public Crianca(Subject subject) {
        super(subject);
        this.prioridade = 2;
    }

    @Override
    public String toString() {
        return "C(" + this.pisoOrigem + "," + this.pisoDestino + ")";
    }
}
