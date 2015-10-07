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
public class Edificio {

    private ArrayListDynamic<Elevador> elevadores;
    private ArrayListDynamic<Piso> pisos;
    private PessoaFactory fabricaP;
    private int nElevadores;
    private int nPisos;
    private int maxCap;
    private Simulator simulador;

    public Edificio(int elevadores, int pisos, int maxCap, Simulator simulador) {
        this.nElevadores = elevadores;
        System.out.println(elevadores);
        this.nPisos = pisos;
        System.out.println(pisos);
        this.maxCap = maxCap;
        this.simulador = simulador;
        this.fabricaP = new PessoaFactory();
        this.elevadores = new ArrayListDynamic<>();
        this.pisos = new ArrayListDynamic<>();

    }

    /**Método que insere os elevadores, os pisos e as pessoas no edificio ao ser
     * inicializada a simulação
     *
     */
    public void inserçãoInicial() {
        adicionarElevadores(nElevadores, maxCap);
        adicionarPisos(nPisos);
        adicionarPessoasAosPisos(nPisos);
    }

    /**Método que inicializa e adiciona um numero determinado de elevadores 
     * ao edificio 
     *
     * @param elevadores
     * @param maxCap
     */
    public void adicionarElevadores(int elevadores, int maxCap) {
        for (int k = 0; k < elevadores; k++) {
            this.getElevadores().add(k, new Elevador(0, maxCap, this));
        }
    }

    /**Método que inicializa e adiciona os pisos ao edificio
     *
     * @param pisos
     */
    public void adicionarPisos(int pisos) {
        for (int i = 0; i < pisos; i++) {
            this.getPisos().add(i, new Piso(i));
        }
    }

    /**Método que adiciona 30 pessoas aos pisos do edificio posicionando cada um
     * deles nos seus respectivos pisos actuais
     *
     * @param pisos
     */
    public void adicionarPessoasAosPisos(int pisos) {
        for (int i = 0; i < 30; i++) {
            Pessoa p = fabricaP.getPessoa(pisos, simulador);
            this.getPisos().get(p.getPisoOrigem()).getPessoasEspera().enqueue(p);
        }
    }

    /**Método que remove todas as pessoas que se encontram no elevador e que 
     * chegaram ao seu piso de destino
     *
     * @param elevador
     */
    public void removerPessoasElevador(Elevador elevador) {
        int pisoActual = elevador.getPisoActual();
        Piso piso = this.getPisos().get(pisoActual);
        for (int i = 0; i < elevador.getPessoas().size(); i++) {
            if (elevador.getPessoas().get(i).getPisoDestino() == pisoActual) {
                Pessoa removido = elevador.getPessoas().remove(i);
                piso.getPessoasServidas().add(0, removido);
                piso.getPessoasServidas().get(0).setState(new StatePServed(piso.getPessoasServidas().get(0)));
            }
        }
    }

    /**Método que remove as pessoas do piso em que o elevador se encontra
     * e insera-as no elevador
     *
     * @param elevador
     */
    public void adicionarPessoasAosElevadores(Elevador elevador) {
        if (elevador.getPessoas().size() < elevador.getMaxCap() && this.getPisos().get(elevador.getPisoActual()).hasPessoas()) {
            Pessoa p = this.getPisos().get(elevador.getPisoActual()).getPessoasEspera().dequeue();
            p.setState(new StatePMoving(p));
            elevador.getPessoas().add(0, p);
            p.unregister();
            adicionarPessoasAosElevadores(elevador);
        }
    }

    /**Método que percorre o array de pisos e verifica se ha alguma pessoa 
     * que necessite de ser transportada. Caso encontre pessoas invoca o metodo
     * efectuarTrajeto() da classe elevador
     *
     */
    public void chamarElevador() {
        int aux = elevadores.size();
        for (int i = 0; i < pisos.size(); i++) {
            if (pisos.get(i).hasPessoas()) {
                if (elevadores.get(aux).getState() instanceof StateWaitingClosed) {
                    elevadores.get(aux).efectuarTrajeto();
                } else {
                    aux--;

                    if (elevadores.get(aux).getState() instanceof StateWaitingClosed) {
                        elevadores.get(aux).efectuarTrajeto();
                    }
                }
            }
        }
    }

    /**Método toString que mostra as pessoas em espera, os estados dos elevadores
     * e as pessoas servidas
     *
     * @return str
     */
    @Override
    public String toString() {
        String str = "";
        if (elevadores.size() == 2) {
            for (int i = (pisos.size() - 1); i >= 0; i--) {
                str += "P" + i + " " + String.format("%60s", pisos.get(i).getPessoasEspera()) + "| ";
                if (elevadores.get(0).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(0).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";

                if (elevadores.get(1).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(1).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";
                str += String.format("%60s", pisos.get(i).getPessoasServidas() + "\n");
            }
            return str;
        }

        if (elevadores.size() == 3) {
            for (int i = (pisos.size() - 1); i >= 0; i--) {
                str += "piso " + i + " " + String.format("%60s", pisos.get(i).getPessoasEspera()) + "| ";
                if (elevadores.get(0).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(0).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";
                if (elevadores.get(1).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(1).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";

                if (elevadores.get(1).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(2).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";

                str += String.format("%60s", pisos.get(i).getPessoasServidas() + "\n");

            }
            return str;
        }
        if (elevadores.size() == 4) {
            for (int i = (pisos.size() - 1); i >= 0; i--) {
                str += "pisos" + i + " " + String.format("%60s", pisos.get(i).getPessoasEspera()) + "| ";

                if (elevadores.get(0).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(0).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";
                if (elevadores.get(1).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(1).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";
                if (elevadores.get(1).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(2).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";
                if (elevadores.get(1).getPisoActual() == i) {
                    str += String.format("%6s", elevadores.get(3).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";
                str += String.format("%60s", pisos.get(i).getPessoasServidas() + "\n");
            }
            return str;
        }
        return str;
    }

    public ArrayListDynamic<Elevador> getElevadores() {
        return elevadores;
    }

    public ArrayListDynamic<Piso> getPisos() {
        return pisos;
    }

    public PessoaFactory getFabricaP() {
        return fabricaP;
    }

    public Simulator getSimulador() {
        return simulador;
    }

    public int getnElevadores() {
        return nElevadores;
    }

    public int getnPisos() {
        return nPisos;
    }

    public int getCapacidadeElevadores() {
        return maxCap;
    }

}
