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
public class StatePWaiting extends StatePessoa {

    public StatePWaiting(Pessoa p) {
        super(p);
    }

    @Override
    public void entrarElevador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sairElevador() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
