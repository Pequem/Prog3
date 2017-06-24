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
public enum Qualis {
    A1("A1"),A2("A2"),B1("B1"),B2("B2"),B3("B3"),B4("B4"),B5("B5"),C("C");
    
    private String nome;
    private ArrayList<Qualificacao> qualificacoesQualis;
    private ArrayList<Pontuacao> pontuacoesQualis;

   
    
    private Qualis(String nome){
        this.nome=nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    
    public void setQualificacaoQualis(Qualificacao q){
        if(qualificacoesQualis == null){
            qualificacoesQualis = new ArrayList<>();
            qualificacoesQualis.add(q);
        }
        else{
            qualificacoesQualis.add(q);
        }
    }
    
    public void setPontuacoesQualis(Pontuacao ponto){
        if(pontuacoesQualis == null){
            pontuacoesQualis = new ArrayList<>();
            pontuacoesQualis.add(ponto);
        }
        else{
            pontuacoesQualis.add(ponto);
        }
    }
    
    
}
