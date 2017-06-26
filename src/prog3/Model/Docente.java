package prog3.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

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
            //System.out.println(num2);
            return num2;
        }
        
        private double qualisAnoPublicacao(Veiculo v,int ano, Regras r){
           
            
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
        
        
        public void recredenciamento(int ano, double pontuacao,Regras r){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(nascimento);
            int monthBirth = cal.get(Calendar.MONTH);
            int yearBirth = cal.get(Calendar.YEAR);
            
            int AnoIngresso = Integer.parseInt(sdf.format(ingresso));
            int subAno = ano - AnoIngresso;
            
            int idade = ano - yearBirth;
            
            //porque sempre a data eh 01/01/anoCredenciamento
            if(monthBirth > 1){
                idade = idade- 1;
            }
            
            //DEPOIS ESCREVER EM UM ARQUIVO CSV 
            if(coordenador == true){
                
                System.out.println(nome +" "+ pontuacao + " Coordenador");
               
            }
            else if(subAno <  3){
                System.out.println(nome +" "+ pontuacao + " PPJ");
            }
            else if(idade > 60){
                System.out.println(nome +" "+ pontuacao + " PPS");
            }
            else if(pontuacao >= r.getPontuacaoMin()){
                System.out.println(nome +" "+ pontuacao + " Sim"); 
            }
            else{
                System.out.println(nome +" "+ pontuacao + " Nao");
            }
            
            
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
