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
public class StateWaitingClosed extends StateElevador {

    public StateWaitingClosed(Elevador e) {
        super(e);
    }

    @Override
    public void ascend() {
        getElevador().setState(new StateAscendElevador(getElevador()));
    }

    @Override
    public void descend() throws InvalidStateException{
        getElevador().setState(new StateDescendElevador(getElevador()));
    }

    @Override
    public void open() throws InvalidStateException{
        getElevador().setState(new StateWaitingOpen(getElevador()));
    }

    @Override
    public void close() throws InvalidStateException{
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void loadUnload() throws InvalidStateException{
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void stop() throws InvalidStateException{
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void movimento() throws InvalidStateException {
        Elevador e = this.getElevador();
        Edificio edificio = this.getElevador().getEdificio();
        if (e.getState() instanceof StateWaitingClosed && e.isOrdemPortas() == true) {
            e.abrirPortas();
            e.setOrdemPortas(false);
        } else {
            if (e.getState() instanceof StateWaitingClosed && !e.getPessoas().isEmpty() && e.isOrdemPortas() == false) {
                e.calcularNovoTrajeto();
            } else {
                if (e.getState() instanceof StateWaitingClosed && e.getPessoas().isEmpty() && e.isOrdemPortas() == false) {
                    e.setTempoInativo(e.getTempoInativo() + (float) edificio.getSimulador().getTicks() / 1000);
                    edificio.getSimulador().chamarElevador(e);
                }
            }
        }
    }

}
