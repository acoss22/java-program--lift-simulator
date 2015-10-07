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
public class StateWaitingOpen extends StateElevador {

    public StateWaitingOpen(Elevador e) {
        super(e);
    }

    @Override
    public void ascend() throws InvalidStateException{
       throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void descend() throws InvalidStateException{
       throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void open() throws InvalidStateException{
       throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void close() throws InvalidStateException{
        getElevador().setState(new StateWaitingClosed(getElevador()));
    }

    @Override
    public void loadUnload() throws InvalidStateException{
        getElevador().setState(new StateLoadUnload(getElevador()));
    }

    @Override
    public void stop() throws InvalidStateException{
        throw new InvalidStateException("Acção inválida!");
    }

    @Override
    public void movimento() throws InvalidStateException {
        Elevador e = this.getElevador();
        e.setOrdemPortas(false);
        e.carregarDescarregar();

    }

}
