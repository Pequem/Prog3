package prog3.Model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Docente implements Comparable<Docente>{
	private long codigo;
	private String nome;
	private Date nascimento;
	private Date ingresso;
	private boolean coordenador;
        private ArrayList<Publicacao> publicacoesDocente; 
        

        public Docente(long codigo, String nome, Date nascimento, Date ingresso, boolean coordenador){
            this.codigo = codigo;
            this.nome = nome;
            this.nascimento = nascimento;
            this.ingresso = ingresso;
            this.coordenador = coordenador;
        
        }
        
        @Override
        public String toString(){
            
            return codigo + " "+ nome + " "+ nascimento +" "+ ingresso +" " + coordenador +". " ;
        }
        
        public void setPublicacaoDocente(Publicacao p){
                if(publicacoesDocente == null){
                    publicacoesDocente = new ArrayList<>();
                    publicacoesDocente.add(p);
                }
                else{
                    publicacoesDocente.add(p);
                }
               
        }
        
        public double getPontuacaoDocente(int ano, Regras regras){
            ArrayList<Qualis> lq;
            double num2=0;
            int anoInicio = ano - regras.getQtdAnos();
            if(publicacoesDocente != null){
                for (Publicacao tp : publicacoesDocente){
                    
                    if(tp.getAno() >= anoInicio && tp.getAno() < ano){
                    num2 += tp.calcularPontosQualificacoesVeiculo(tp.getVeiculo(),ano,regras);
                    
                    }
                }
               
            }
           
            return num2;
        }
        
        

    public long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public Date getIngresso() {
        return ingresso;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public ArrayList<Publicacao> getPublicacoesDocente() {
        return publicacoesDocente;
    }

    @Override
    public int compareTo(Docente anotherDocente) {
    
        return nome.compareTo(anotherDocente.getNome());
    }
        
    
	
	
}
