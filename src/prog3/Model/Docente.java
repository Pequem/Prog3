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
            double num2=0;
            int anoInicio = ano - regras.getQtdAnos();
            if(publicacoesDocente != null){
                for (Publicacao tp : publicacoesDocente){
                    
                    if(tp.getAno() >= anoInicio){
                    num2 += qualisAnoPublicacao(tp.getVeiculo(),ano,regras);
                    
                    }
                    //verificar se foi cadastrado ou nao 
                }
               
            }
            System.out.println(num2);
            return 0.0;
        }
        
        private double qualisAnoPublicacao(Veiculo v,int ano, Regras r){
            ArrayList<Qualis> lQualis = new ArrayList<>();
            
            double num=0;
            if(v.getTipo() == 'P'){
                
                for(Qualificacao qTemp : v.getQualificacoesVeiculo()){

                    num += (r.getFatorMult()* r.getPontuacoesRegras().get(qTemp.getQualis()).getValor());
                    
                }
            }
            else if(v.getTipo() == 'C'){
                
               for(Qualificacao temp : v.getQualificacoesVeiculo()){
                   
                        num += r.getPontuacoesRegras().get(temp.getQualis()).getValor();
                        
                } 
            }
           
            return num;
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
