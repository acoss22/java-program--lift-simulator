/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import tads.ArrayListDynamic;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class Elevador {

    private Edificio edificio;
    private StateElevador state;
    private ArrayListDynamic<Pessoa> pessoas;
    private int maxCap;
    private int pisoActual;
    private int pisoDestino;
    private int distanciaPercorrida;
    private float tempoInativo;
    private boolean ordemPortas;

    public Elevador(int piso, int maxCap, Edificio edificio) {
        this.maxCap = maxCap;
        this.pisoActual = piso;
        this.ordemPortas = false;
        this.state = new StateWaitingClosed(this);
        this.pessoas = new ArrayListDynamic<>();
        this.distanciaPercorrida = 0;
        this.pisoDestino = 0;
        this.tempoInativo = 0;
        this.edificio=edificio;
    }

    public StateElevador getState() {
        return state;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setPessoas(ArrayListDynamic<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void setPisoActual(int pisoActual) {
        this.pisoActual = pisoActual;
    }

    public void setPisoDestino(int pisoDestino) {
        this.pisoDestino = pisoDestino;
    }

    public void setState(StateElevador state) {
        this.state = state;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public ArrayListDynamic<Pessoa> getPessoas() {
        return pessoas;
    }

    public int getPisoActual() {
        return pisoActual;
    }

    public int getPisoDestino() {
        return pisoDestino;
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public float getTempoInativo() {
        return tempoInativo;
    }

    public void setTempoInativo(float tempoInativo) {
        this.tempoInativo = tempoInativo;
    }

    public boolean isOrdemPortas() {

        return ordemPortas;
    }

    public void setOrdemPortas(boolean ordemPortas) {
        this.ordemPortas = ordemPortas;
    }

    /**Método que calcula qual o estado a que o elevador deverá passar, se
     * pisoDestino > pisoActual o estado do elevador passa para " a subir "
     * caso seja pisoDestino < pisoActual o stado do elevador passa para 
     * " a descer ". Na eventualidade de pisoDestino == pisoActual o estado do
     * elevador passa a "em espera e com portas fechadas"
     *
     */
    public void efectuarTrajeto() {
        int diferenca = pisoDestino - pisoActual;
        if (diferenca > 0) {
            this.setState(new StateAscendElevador(this));
        }
        if (diferenca < 0) {
            this.setState(new StateDescendElevador(this));
        }
        if (diferenca == 0) {
            setState(new StateWaitingClosed(this));
            setOrdemPortas(true);
        }
    }

    /**Método que incrementa o pisoActual do elevador e a sua distancia percorrida.
     * Caso o pisoActual for igual ao pisoDestino entao será evocado o metodo
     * paraElevador() e será passdo a true o atributo ordemPortas
     * 
     *
     */
    public void subirPiso() throws InvalidStateException {
        if ((state instanceof StateWaitingClosed || state instanceof StateAscendElevador) && pisoActual != pisoDestino) {
            pisoActual++;
            state.ascend();
            distanciaPercorrida++;

        }
        if (pisoActual == pisoDestino) {
            pararElevador();
            setOrdemPortas(true);

        }
    }

    /**Método que diminui em uma unidade o pisoActual do elevador e a sua 
     * distancia percorrida. Caso o pisoActual for igual ao pisoDestino entao 
     * será evocado o metodo paraElevador() e será passdo a true o atributo ordemPortas
     *
     */
    public void descerPiso() throws InvalidStateException {
        if ((state instanceof StateWaitingClosed || state instanceof StateDescendElevador) && pisoActual != pisoDestino) {
            pisoActual--;
            state.descend();
            distanciaPercorrida++;
        }
        if (pisoActual == pisoDestino) {
            pararElevador();
            setOrdemPortas(true);
        }
        if (pisoActual < pisoDestino && state instanceof StateDescendElevador) {
            pararElevador();
            subirPiso();
        }
    }

    /**Método que muda o estado do elevador para " a carregar/descarregar "
     *
     */
    public void carregarDescarregar() throws InvalidStateException {
        state.loadUnload();
    }

    /**Método que muda o estado do elevador para " parado e fechado "
     *
     */
    public void pararElevador() throws InvalidStateException {
        state.stop();
    }

    /**Método que muda o estado do elevador para " parado com portas abertas "
     *
     */
    public void abrirPortas() throws InvalidStateException {
        state.open();
    }

    /**Método que muda o estado do elevador para " parado com portas fechadas "
     * Este método deverá ser chamado apenas quando o elevador esta no estado 
     * " parado com portas abertas "
     *
     */
    public void fecharPortas() throws InvalidStateException {
        state.close();
    }

    /**Método que verifica todos os destinos das pessoas introduzidas no elevador
     * com a finalidade de calcular o trajeto mais curto que pode efectuar 
     * e muda o pisoDestino consoante a sua decisão. Depois de definir o novo
     * pisoDestino do elevador é evocado o método efectuarTrajeto() para dar
     * inicio ao movimento do elevador.
     *
     */
    public void calcularNovoTrajeto() {
        int minimo = Integer.MAX_VALUE;
        int diferenca = 0;
        int pisoActualAux = pisoActual;
        for (int i = 0; i < pessoas.size(); i++) {
            diferenca = pessoas.get(i).getPisoDestino() - pisoActualAux;
            if (diferenca < 0) {
                diferenca = -diferenca;
            }
            if (diferenca < minimo) {
                minimo = diferenca;
                pisoDestino = pessoas.get(i).pisoDestino;

            }

        }
        efectuarTrajeto();
    }

    /**Método que evoca o metodo movimento() da classe StateElevador
     *
     */
    public void movimento() throws InvalidStateException {
        this.state.movimento();
    }

    public String toStringEstados() {
        if (this.state instanceof StateLoadUnload) {
            return "Carregar/Descarregar";
        }
        if (this.state instanceof StateAscendElevador) {
            return "Subir";
        }
        if (this.state instanceof StateDescendElevador) {
            return "Descer";
        }
        if (this.state instanceof StateWaitingClosed) {
            return "Parado c/ porta fechada";
        }
        if (this.state instanceof StateWaitingOpen) {
            return "Parado c/ porta aberta";
        }
        return "";
    }

    @Override
    public String toString() {

        if (state instanceof StateAscendElevador) {
            String str = "|^" + String.format("%02d", pessoas.size()) + "^|";
            return str;
        }
        if (state instanceof StateDescendElevador) {
            String str = "|v" + String.format("%02d", pessoas.size()) + "v|";
            return str;
        }
        if (state instanceof StateWaitingClosed) {
            String str = "||" + String.format("%02d", pessoas.size()) + "||";
            return str;
        }
        if (state instanceof StateWaitingOpen) {
            String str = "|<" + String.format("%02d", pessoas.size()) + ">|";
            return str;
        }
        if (state instanceof StateLoadUnload) {
            String str = "->" + String.format("%02d", pessoas.size()) + "->";
            return str;
        }

        return "";

    }

}
