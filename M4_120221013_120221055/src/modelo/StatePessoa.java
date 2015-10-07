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
public abstract class StatePessoa {

    private Pessoa pessoa;

    public StatePessoa(Pessoa p) {
        this.pessoa = p;
    }

    public Pessoa getPessoa() {
        return pessoa;

    }

    public abstract void entrarElevador();

    public abstract void sairElevador();

}
