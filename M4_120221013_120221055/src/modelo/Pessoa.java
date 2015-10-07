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
public class Pessoa implements Comparable, ObserverPessoa {

    protected Subject subject;
    protected int prioridade;
    protected int pisoOrigem;
    protected int pisoDestino;
    protected float tempoEspera;
    protected StatePessoa state;

    public Pessoa(Subject subject) {
        this.subject = subject;
        this.tempoEspera = 0;
        this.state = new StatePWaiting(this);
        subject.register(this);
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getPisoOrigem() {
        return pisoOrigem;
    }

    public int getPisoDestino() {
        return pisoDestino;
    }

    public void setPisoOrigem(int pisoOrigem) {
        this.pisoOrigem = pisoOrigem;
    }

    public void setPisoDestino(int pisoDestino) {
        this.pisoDestino = pisoDestino;
    }

    public float getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(float tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public StatePessoa getState() {
        return state;
    }

    public void setState(StatePessoa state) {
        this.state = state;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Pessoa) {
            int aux = this.prioridade - ((Pessoa) o).prioridade;
            return aux;
        }
        return 0;
    }

    @Override
    public void update(float time) {
        this.tempoEspera += time;
    }

    public void unregister() {
        subject.unregister(this);
    }
}
