package prog3.Model;

import java.util.ArrayList;
import java.util.Date;

abstract class Publicacao {
	protected Date ano;
	protected String titulo;
	protected int numero;
	protected int pagInicial;
	protected int pagFinal;
	protected ArrayList<Veiculo> veiculo;
	protected ArrayList<Docente> autores;

    /**
     * @return the ano
     */
    public Date getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Date ano) {
        this.ano = ano;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the pagInicial
     */
    public int getPagInicial() {
        return pagInicial;
    }

    /**
     * @param pagInicial the pagInicial to set
     */
    public void setPagInicial(int pagInicial) {
        this.pagInicial = pagInicial;
    }

    /**
     * @return the pagFinal
     */
    public int getPagFinal() {
        return pagFinal;
    }

    /**
     * @param pagFinal the pagFinal to set
     */
    public void setPagFinal(int pagFinal) {
        this.pagFinal = pagFinal;
    }

    /**
     * @return the veiculo
     */
    public ArrayList<Veiculo> getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(ArrayList<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the autores
     */
    public ArrayList<Docente> getAutores() {
        return autores;
    }
   
    /**
     * @param autores the autores to set
     */
    public void setAutores(ArrayList<Docente> autores) {
        this.autores = autores;
    }
	
}
