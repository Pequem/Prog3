package prog3.Model;

import java.util.ArrayList;
import java.util.Date;

public class Docente {
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
            //precisa configurar a saida das datas 
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
            for (Publicacao tp : publicacoesDocente){
                
                lq = qualisAnoPublicacao(tp.getVeiculo(),ano,regras);
                //pegar o fator miltiplicador
                //converter qualis nos pontos e somar
            }
            
            
            
            return 0.0;
        }
        
        private ArrayList<Qualis> qualisAnoPublicacao(Veiculo v,int ano, Regras r){
            ArrayList<Qualis> lQualis = new ArrayList<>();
            int anoInicio = ano - r.getQtdAnos();
            
            for(Qualificacao qTemp : v.getQualificacoesVeiculo()){
                if(qTemp.getAno() >= anoInicio){
                    lQualis.add(qTemp.getQualis());
                }
            }
            
            return lQualis;
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
        
    
	
	
}
