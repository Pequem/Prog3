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
    
    
    public double pontuacaoVeiculo(int ano,Veiculo v,Regras reg){
        double pontuacao = 0.0;
        
        //pega a pontuacao do qualis do veiculo
       
        Pontuacao pont = reg.getQualisPontuacao(qualis);

        if(v.getTipo() =='P'){
            pontuacao = v.getFatorDeImpacto()* reg.getFatorMult()* pont.getValor() ;
        }
        if(v.getTipo() == 'C'){
             pontuacao = v.getFatorDeImpacto() * pont.getValor() ;
        }

        return pontuacao;
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
