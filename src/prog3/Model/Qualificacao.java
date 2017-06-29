package prog3.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Qualificacao implements Serializable {

    private int ano;
    private Qualis qualis;
    private Veiculo veiculoQualificacao;

    public Qualificacao() {

    }

    public Qualificacao(int ano, Qualis qualis, Veiculo veiculoQualificacao) {
        this.ano = ano;
        this.qualis = qualis;
        this.veiculoQualificacao = veiculoQualificacao;
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

    public ArrayList<Qualificacao> getAllByQuali(Qualis _q, ArrayList<Qualificacao> _qualificacoes) {
        ArrayList<Qualificacao> _qualificacoesFinal = new ArrayList<>();
        for (Qualificacao _qualificacao : _qualificacoes) {
            if (_qualificacao.qualis.equals(_q)) {
                _qualificacoesFinal.add(_qualificacao);
            }
        }

        return _qualificacoesFinal;
    }

}
