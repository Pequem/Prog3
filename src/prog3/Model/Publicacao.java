package prog3.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Publicacao {
	protected int ano;
	protected String titulo;
	protected int numero;
	protected int pagInicial;
	protected int pagFinal;
	protected Map<String,Veiculo> veiculo;
	protected Map<Long,Docente> autores;
        
        
        public Publicacao(int ano,String titulo,int numero,Map<String,Veiculo> veiculo,
                Map<Long,Docente> autores,int pagInicial,int pagFinal){
            this.ano= ano;
            this.titulo = titulo;
            this.numero = numero;
            this.veiculo = veiculo;
            this.autores=autores;
            this.pagInicial=pagInicial;
            this.pagFinal=pagFinal;
            
        }
}