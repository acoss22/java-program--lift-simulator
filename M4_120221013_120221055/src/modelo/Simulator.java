/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Observable;
import tads.ObserverPessoa;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tads.ArrayListDynamic;
import tads.Subject;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class Simulator extends java.util.Observable implements Runnable, Subject {

    Random rd = new Random();
    private ArrayListDynamic<Pessoa> observers;
    private Edificio edificio;
    private int ticks;
    private int probabilidade;
    private float tempoEspera;

    public Simulator(int elevadores, int pisos, int maxCap, int ticks, int probabilidade) {
        this.ticks = ticks;
        if (pisos < 8) {
            pisos = 8;
        }
        if (pisos > 16) {
            pisos = 16;
        }
        if (elevadores < 2) {
            elevadores = 2;
        }
        if (elevadores > 4) {
            elevadores = 4;
        }
        this.edificio = new Edificio(elevadores, pisos, maxCap, this);
        this.observers = new ArrayListDynamic();
        this.probabilidade = probabilidade;
    }

    /**
     * Método responsavel para simulação do edificio e respectiva representação
     * gráfica (consola)
     *
     */
    @Override
    public void run() {

        while (true) {
            try {
                movimento();
            } catch (InvalidStateException ex) {
                Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setChanged();
            this.notifyObservers();
            System.out.println(toString());
            System.out.println(toStringInfo());
            System.out.println(Estatisticas());

            try {
                Thread.sleep(ticks);
            } catch (InterruptedException ex) {
                Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Método que gera um numero aleatorio entre 0 e 10 e caso esse numero seja
     * < a probilidade de gerar uma nova pessoa (numero < probabilidade) será
     * gerada uma nova pessoa utilizando o metodo getPessoa da classe
     * PessoaFactory. Caso contrario não será gerada uma nova pessoa.
     *
     * @return pessoa ou null
     */
    public Pessoa geraPessoas() {
        int aux = rd.nextInt(11);
        if (aux < probabilidade / 10) {
            return edificio.getFabricaP().getPessoa(edificio.getPisos().size(), this);
        } else {
            return null;
        }
    }

    /**
     * Método que percorre o array de pisos e verifica se ha alguma pessoa que
     * necessite de ser transportada. Caso encontre pessoas invoca o metodo
     * efectuarTrajeto() da classe elevador
     *
     */
    public void chamarElevador(Elevador e) {
        for (int j = 0; j < edificio.getPisos().size(); j++) {
            if (edificio.getPisos().get(j).hasPessoas()) {
                e.setPisoDestino(edificio.getPisos().get(j).getNumero());
                e.efectuarTrajeto();
            }
        }
    }

    /**Método evocado a partir do método run(), este método esta responsavel
     * por atribuir uma nova função aos elevadores e gerar novas pessoas cada 
     * vez que é evocado.
     *
     */
    public void movimento() throws InvalidStateException {
        for (int i = 0; i < edificio.getElevadores().size(); i++) {
            Elevador e = edificio.getElevadores().get(i);
            e.movimento();
        }
        Pessoa p = geraPessoas();

        if (p != null) {
            edificio.getPisos().get(p.getPisoOrigem()).getPessoasEspera().enqueue(p);
        }
    }

    /**Método que calcular o numero total de pessoas existentes no edificio
     * 
     * @return totalPessoas
     */
    public int numeroPessoasTotal() {
        int totalPessoas = 0;
        totalPessoas += calculaNPassageirosEspera() + calculaNPassageirosServidos();
        for (int i = 0; i < edificio.getElevadores().size(); i++) {
            Elevador elevador = edificio.getElevadores().get(i);
            totalPessoas += elevador.getPessoas().size();
        }
        return totalPessoas;
    }

    /**Método que percorre todos os pisos e recolhe o tempo de espera de cada
     * passageiro ainda por ser servido e calcula tempo de espera do coletivo.
     *
     * @return soma
     */
    public float calculaTempoEspera() {
        float soma = 0;
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i).getState() instanceof StatePWaiting) {
                soma += observers.get(i).getTempoEspera();
            }
        }
        return soma;
    }

    /**Método que calcula o numero de passageiros em espera
     *
     * @return nPassageirosEspera
     */
    public int calculaNPassageirosEspera() {
        int nPassageirosEspera = 0;
        for (int i = 0; i < edificio.getPisos().size(); i++) {
            Piso piso = edificio.getPisos().get(i);
            nPassageirosEspera += piso.getPessoasEspera().size();
        }
        return nPassageirosEspera;
    }

    /**Método que calcula o numero de passageiros servidos
     *
     * @return nPassageirosServidos
     */
    public int calculaNPassageirosServidos() {
        int nPassageirosServidos = 0;
        for (int i = 0; i < edificio.getPisos().size(); i++) {
            Piso piso = edificio.getPisos().get(i);
            nPassageirosServidos += piso.getPessoasServidas().size();
        }
        return nPassageirosServidos;
    }

    /**Método que calcula a distancia percorrida por todos os elevadores juntos
     *
     * @return distanciaTotal
     */
    public int calculaDistanciaTotal() {
        int distanciaTotal = 0;
        for (int r = 0; r < edificio.getElevadores().size(); r++) {
            int distancia = edificio.getElevadores().get(r).getDistanciaPercorrida();
            distanciaTotal += distancia;
        }
        return distanciaTotal;
    }

    /**Método que calcula o tempo de inactividade de cada um dos elevadores
     *
     * @return str
     */
    public String calculaTempoInativo() {
        String str = "";
        for (int i = 0; i < edificio.getElevadores().size(); i++) {
            float tempoInactivo = edificio.getElevadores().get(i).getTempoInativo();
            str += "\televador: " + (i + 1) + " " + tempoInactivo + "s\t";
        }
        return str;

    }

    /**Método que regista um novo observer(pessoa) ao array de observadores do
     * simulador
     *
     * @param o
     */
    @Override
    public void register(ObserverPessoa o) {
        observers.add(observers.size(), (Pessoa) o);
    }

    /**Método que remove um observador do array de observadores do simulador
     *
     * @param o
     */
    @Override
    public void unregister(ObserverPessoa o) {
        int N = observers.size();
        for (int i = 0; i < N ; i++) {
            ObserverPessoa p = observers.remove(0);
            if (p != o) {
                observers.add(observers.size(), (Pessoa)p);
            } 
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i).getState() instanceof StatePWaiting) {
                observers.get(i).update((float) ticks / (float) 1000);
            }
        }
    }

    public String toString() {
        String str = "";
        for (int i = (edificio.getPisos().size() - 1); i >= 0; i--) {
            str += String.format("%4.4s", "P" + i) + "  " + String.format("%60.60s", edificio.getPisos().get(i).getPessoasEspera().toString()) + "| ";
            for (int j = 0; j < edificio.getElevadores().size(); j++) {
                if (edificio.getElevadores().get(j).getPisoActual() == i) {
                    str += String.format("%6s", edificio.getElevadores().get(j).toString());
                } else {
                    str += String.format("%6s", "");
                }
                str += " | ";
            }
            str += String.format("%50s", edificio.getPisos().get(i).getPessoasServidas() + "\n");
        }
        return str;
    }

    public String toStringInfo() {
        String str = "\nInformaçoes: "
                + "\nNº Pisos: " + edificio.getPisos().size()
                + "\nNº Elevadores: " + edificio.getElevadores().size() + "\n";

        for (int i = 0; i < edificio.getElevadores().size(); i++) {
            Elevador elevador = edificio.getElevadores().get(i);
            str += "\nElevador " + (i + 1) + ": "
                    + "\nPiso Actual: " + elevador.getPisoActual() + "\t\tPiso Destino: " + elevador.getPisoDestino();
            str += "\t\tEstado: " + elevador.toStringEstados() + "\n";
        }
        return str;
    }

    public String Estatisticas() {
        int pessoasTotal = numeroPessoasTotal();
        float soma = calculaTempoEspera();
        int nPassageirosEspera = calculaNPassageirosEspera();
        int nPassageirosServidos = calculaNPassageirosServidos();
        int distanciaTotal = calculaDistanciaTotal();

        return "Passageiros á espera: " + nPassageirosEspera + "\t\tPassageiros servidos: " + nPassageirosServidos
                + "\nDistância percorrida pelos elevadores (em pisos): " + distanciaTotal
                + "\nTempo inativo: " + calculaTempoInativo() + "\n"
                + "Tempo médio de espera dos passageiros: " + soma / pessoasTotal
                + "\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
    }

    public ArrayListDynamic<Pessoa> getObservers() {
        return observers;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public int getTicks() {
        return ticks;
    }

    public int getProbabilidade() {
        return probabilidade;
    }

    public float getTempoEspera() {
        return tempoEspera;
    }

}
