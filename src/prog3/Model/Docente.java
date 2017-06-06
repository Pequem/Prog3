package prog3.Model;

import java.util.Date;

public class Docente {
	private int codigo;
	private String nome;
	private Date nascimento;
	private Date ingresso;
	private boolean coordenador;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Date getIngresso() {
		return ingresso;
	}
	public void setIngresso(Date ingresso) {
		this.ingresso = ingresso;
	}
	public boolean isCoordenador() {
		return coordenador;
	}
	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}
	
	
}
