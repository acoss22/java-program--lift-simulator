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
public class StateDescendElevador extends StateElevador {

    public StateDescendElevador(Elevador e) {
        super(e);
    }

    @Override
    public void ascend() throws InvalidStateException {
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void descend() throws InvalidStateException {
        getElevador().setState(new StateDescendElevador(getElevador()));
    }

    @Override
    public void open() throws InvalidStateException {
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void close() throws InvalidStateException {
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void loadUnload() throws InvalidStateException {
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void stop() throws InvalidStateException{
        getElevador().setState(new StateWaitingClosed(getElevador()));
    }

    /**
     *
     */
    @Override
    public void movimento() throws InvalidStateException {
        Elevador e = this.getElevador();
        Edificio edificio = this.getElevador().getEdificio();
        if (e.getPessoas().size() < e.getMaxCap() && edificio.getPisos().get(e.getPisoActual()).hasPessoas()) {
            e.pararElevador();
            e.setOrdemPortas(true);
        } else {
            e.setOrdemPortas(false);
            e.descerPiso();
        }
    }

}
