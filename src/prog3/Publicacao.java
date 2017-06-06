package prog3;

import java.util.ArrayList;
import java.util.Date;

public class Publicacao {
	private Date ano;
	private Veiculo veiculo;
	private String titulo;
	private ArrayList<Docente> autores;
	private int numero;
	private int volume;
	private String localCoferencia;
	private int pagInicial;
	private int pagFinal;
	
	public Publicacao(){
		setAutores(new ArrayList<Docente>());
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<Docente> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Docente> autores) {
		this.autores = autores;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getLocalCoferencia() {
		return localCoferencia;
	}

	public void setLocalCoferencia(String localCoferencia) {
		this.localCoferencia = localCoferencia;
	}

	public int getPagInicial() {
		return pagInicial;
	}

	public void setPagInicial(int pagInicial) {
		this.pagInicial = pagInicial;
	}

	public int getPagFinal() {
		return pagFinal;
	}

	public void setPagFinal(int pagFinal) {
		this.pagFinal = pagFinal;
	}
}
