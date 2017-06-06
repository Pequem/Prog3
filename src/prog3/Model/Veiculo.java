package prog3.Model;

public class Veiculo {
	private String sigla;
	private String nome;
	private double fatorDeImpacto;
	private String issn;
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getFatorDeImpacto() {
		return fatorDeImpacto;
	}
	public void setFatorDeImpacto(double fatorDeImpacto) {
		this.fatorDeImpacto = fatorDeImpacto;
	}
	public String getIssn() {
		return issn;
	}
	public void setIssn(String issn) {
		this.issn = issn;
	}
}
