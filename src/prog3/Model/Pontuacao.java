/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3.Model;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Pontuacao {
    private int valor;
    private Regras regrasPontuacao;
    private ArrayList<Qualis> qualisPontuacoes;
    

    Pontuacao(int valor) {
        this.valor=valor;
    }

    public int getValor() {
        return valor;
    }
    
    
    
    
    
}
