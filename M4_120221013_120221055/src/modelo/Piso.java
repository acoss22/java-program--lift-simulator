/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import tads.ArrayListDynamic;
import tads.PriorityQueueAdapter;

/**
 *
 * @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class Piso {

    private final int numero;
    private PriorityQueueAdapter<Pessoa> pessoasEspera;
    private ArrayListDynamic<Pessoa> pessoasServidas;

    public Piso(int numero) {
        this.numero = numero;
        pessoasEspera = new PriorityQueueAdapter<>();
        pessoasServidas = new ArrayListDynamic<>();
    }

    public boolean hasPessoas() {
        return !pessoasEspera.isEmpty();
    }

    public int getNumero() {
        return numero;
    }

    public String toStringEspera() {
        String str = pessoasEspera.toString();
        return str;

    }

    public String toStringServidos() {
        String str2 = pessoasServidas.toString();
        return str2;

    }

    public PriorityQueueAdapter<Pessoa> getPessoasEspera() {
        return pessoasEspera;
    }

    public ArrayListDynamic<Pessoa> getPessoasServidas() {
        return pessoasServidas;
    }

    public void setPessoasEspera(PriorityQueueAdapter<Pessoa> pessoasEspera) {
        this.pessoasEspera = pessoasEspera;
    }

    public void setPessoasServidas(ArrayListDynamic<Pessoa> pessoasServidas) {
        this.pessoasServidas = pessoasServidas;
    }

}
