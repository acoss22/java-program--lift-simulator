/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Random;
import tads.Subject;

/**
 *
 * @author @author André Benevides-120221013 e Ana Sequeira-120221055
 */
public class PessoaFactory {

    Random rd = new Random();
    Pessoa pessoa = null;

    /**Método que gera uma nova pessoa aleatoriamente. É gerado um numero ao 
     * acaso utilizando um Random (de 0 a 100) caso o numero gerado for <=60
     * será gerado um novo adulto. Caso o numero esteja entre 60 e 85 será gerada
     * uma nova criança, para os restantes casos será gerado um deficiente.
     *
     * @param maxPisos
     * @param subject
     * @return pessoa
     */
    public Pessoa getPessoa(int maxPisos, Subject subject) {
        int tipoPessoa = rd.nextInt(100);

        if (tipoPessoa <= 60) {
            do {
                pessoa = new Adulto(subject);
                pessoa.setPisoDestino(rd.nextInt(maxPisos));
                pessoa.setPisoOrigem(rd.nextInt(maxPisos));

            } while (pessoa.getPisoDestino() == pessoa.getPisoOrigem());
            return pessoa;
        }
        if (tipoPessoa > 60 && tipoPessoa < 85) {
            do {
                pessoa = new Crianca(subject);
                pessoa.setPisoDestino(rd.nextInt(maxPisos));
                pessoa.setPisoOrigem(rd.nextInt(maxPisos));
            } while (pessoa.getPisoDestino() == pessoa.getPisoOrigem());
            return pessoa;
        }
        if (tipoPessoa >= 85) {
            do {
                pessoa = new Deficiente(subject);
                pessoa.setPisoDestino(rd.nextInt(maxPisos));
                pessoa.setPisoOrigem(rd.nextInt(maxPisos));
            } while (pessoa.getPisoDestino() == pessoa.getPisoOrigem());
            return pessoa;
        }
        return null;
    }

}
