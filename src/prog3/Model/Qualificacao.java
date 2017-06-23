package prog3.Model;


public class Qualificacao {
    private int ano;
    private Qualis qualis;
    private Veiculo veiculoQualificacao;
    
    
    
    public Qualificacao(int ano,Qualis qualis,Veiculo veiculoQualificacao){
        this.ano=ano;
        this.qualis=qualis;
        this.veiculoQualificacao=veiculoQualificacao;
    }
    
  
    
    public int getAno() {
        return ano;
    }

    public Qualis getQualis() {
        return qualis;
    }

    public Veiculo getVeiculoQualificacao() {
        return veiculoQualificacao;
    }
    
    
}
