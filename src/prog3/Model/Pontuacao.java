/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Pontuacao implements Serializable{
    private final int valor;
    private Regras regrasPontuacao;
    private ArrayList<Qualis> qualisPontuacoes;
    

    public Pontuacao(int valor) {
        this.valor=valor;
    }

    public void setRegrasPontuacao(Regras regrasPontuacao) {
        this.regrasPontuacao = regrasPontuacao;
    }
    
    
    public void setQualisPontuacoes(Qualis qualis){
        if(qualisPontuacoes == null){
            qualisPontuacoes = new ArrayList<>();
        }
        
        qualisPontuacoes.add(qualis);
 
    }

    public int getValor() {
        return valor;
    }
    
    
    
    
    
}
