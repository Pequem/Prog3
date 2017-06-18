package prog3.Model;

public class Veiculo {
	private String sigla;
	private String nome;
        private char tipo;
	private double fatorDeImpacto;
	private String issn;
	
        public Veiculo(String sigla,String nome,char tipo,double fatorDeImpacto,String issn){
            this.sigla=sigla;
            this.nome=nome;
            this.tipo=tipo;
            this.fatorDeImpacto=fatorDeImpacto;
            this.issn=issn;
            
        }
        
        
        @Override
        public String toString(){
            
            return sigla + " "+ nome + " "+ tipo +" "+ fatorDeImpacto +" " + issn ;
        }
        
        
        
        
}
