package prog3.Model;

import java.util.ArrayList;

public class Veiculo {
	private String sigla;
	private String nome;
        private char tipo;
	private double fatorDeImpacto;
	private String issn;
        private ArrayList<Publicacao> publicacoesVeiculo;
        private ArrayList<Qualificacao> qualificacoesVeiculo;
        
	
        public Veiculo(String sigla,String nome,char tipo,double fatorDeImpacto,String issn){
            this.sigla=sigla;
            this.nome=nome;
            this.tipo=tipo;
            this.fatorDeImpacto=fatorDeImpacto;
            this.issn=issn;
            
        }
        
        
        public void setPublicacoesVeiculo(Publicacao p){
            
            if(publicacoesVeiculo == null){
                publicacoesVeiculo = new ArrayList<>();
                publicacoesVeiculo.add(p);
            }
            else{
                publicacoesVeiculo.add(p);
            }
        }
        
         public void setQualificacoesVeiculo(Qualificacao q){
            
            if(qualificacoesVeiculo == null){
                qualificacoesVeiculo = new ArrayList<>();
                qualificacoesVeiculo.add(q);
            }
            else{
                qualificacoesVeiculo.add(q);
            }
        }
        
        
        public String getSigla() {
            return sigla;
        }

        public String getNome() {
            return nome;
        }

        public char getTipo() {
            return tipo;
        }

        public double getFatorDeImpacto() {
            return fatorDeImpacto;
        }

        public String getIssn() {
            return issn;
        }

    public ArrayList<Publicacao> getPublicacoesVeiculo() {
        return publicacoesVeiculo;
    }

    public ArrayList<Qualificacao> getQualificacoesVeiculo() {
        return qualificacoesVeiculo;
    }
        
        
        
        
        
        
}
