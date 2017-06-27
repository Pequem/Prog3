package prog3.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Publicacao {
	private int ano;
	private String titulo;
	private int numero;
	private int pagInicial;
	private int pagFinal;
	private Veiculo veiculo;
	private Map<Long,Docente> autores;
        
        
    public Publicacao(int ano,String titulo,int numero,Veiculo veiculo,
            Map<Long,Docente> autores,int pagInicial,int pagFinal){
        this.ano= ano;
        this.titulo = titulo;
        this.numero = numero;
        this.veiculo = veiculo;
        this.autores=autores;
        this.pagInicial=pagInicial;
        this.pagFinal=pagFinal;

    }
        
        
    public boolean existeAutorPublicacao(Long cod){
      return autores.containsKey(cod);
    }

    public int getAno() {
        return ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumero() {
        return numero;
    }

    public int getPagInicial() {
        return pagInicial;
    }

    public int getPagFinal() {
        return pagFinal;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Map<Long, Docente> getAutores() {
        return autores;
    }

    public double calcularPontosQualificacoesVeiculo(Veiculo v,int ano, Regras r){
                
            double num=0;
           
            for(Qualificacao qTemp : v.getQualificacoesVeiculo()){
                if(v.getTipo() == 'P'){
                    num += (r.getFatorMult()* r.getPontuacoesRegras().get(qTemp.getQualis()).getValor());
                }
                else if (v.getTipo() == 'C'){
                    num += r.getPontuacoesRegras().get(qTemp.getQualis()).getValor();
                }
            }
            
 
            return num;
    }
        
        
}