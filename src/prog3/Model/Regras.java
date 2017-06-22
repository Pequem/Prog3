/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author user
 */
public class Regras {
    private double fatorMult;
    private Date dataInicio;
    private Date dataFim;
    private int qtdAnos;
    private int pontuacaoMin;
    private Map<Qualis,Pontuacao> qualisPontuacao;
    
    public Regras(double fatorMult,Date dataInicio,Date dataFim,int qtdAnos,
            int pontuacaoMin,Map<Qualis,Pontuacao> qualisPontuacao){
        this.fatorMult=fatorMult;
        this.dataInicio=dataInicio;
        this.dataFim=dataFim;
        this.qtdAnos=qtdAnos;
        this.pontuacaoMin=pontuacaoMin;
        this.qualisPontuacao=qualisPontuacao;
        
        
    }

    public double getFatorMult() {
        return fatorMult;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public int getQtdAnos() {
        return qtdAnos;
    }

    public int getPontuacaoMin() {
        return pontuacaoMin;
    }

    public Pontuacao getQualisPontuacao(Qualis q) {
        return qualisPontuacao.get(q);
    }
    
    
    
}
