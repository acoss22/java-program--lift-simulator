/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public abstract class StateElevador {

    private Elevador elevador;

    public StateElevador(Elevador e) {
        this.elevador = e;
    }

    public Elevador getElevador() {
        return elevador;
    }

    public abstract void stop() throws InvalidStateException;

    public abstract void ascend() throws InvalidStateException;

    public abstract void descend() throws InvalidStateException;

    public abstract void open() throws InvalidStateException;

    public abstract void close() throws InvalidStateException;

    public abstract void loadUnload() throws InvalidStateException;

    public abstract void movimento() throws InvalidStateException;
}
