/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3.Model;

import java.util.ArrayList;

/**
 *
 * @author rodri
 */
public class Periodico extends Publicacao{
    private int volume;
    
    public Periodico(int volume, int ano,String titulo,int numero,Veiculo veiculo,
                ArrayList<Docente> autores,int pagInicial,int pagFinal){
        super(ano,titulo,numero,veiculo,autores,pagInicial,pagFinal);
        this.volume=volume;
        
    }
}
