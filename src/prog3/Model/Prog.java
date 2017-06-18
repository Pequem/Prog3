package prog3.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Prog {
	
	public static void main(String[] args) throws ParseException{
            
            Locale ptBR = new Locale( "pt", "BR" ); 
            NumberFormat numberFormat =  NumberFormat.getNumberInstance(ptBR); 
            //Essa parte toda esta em andameto . .. 
            String id = "-d";
            //Scanner s = new Scanner(System.in);
		//s.close();
            int anoCredenciamento;
            String csvFile;
            String line="";
            String cvsSplitBy =";";
            Map<Long,Docente> docentes = new HashMap<>();;
            Map<String,Veiculo> veiculos = new HashMap<>();;
            ArrayList<Periodico> periodicos = new ArrayList<>();;
            ArrayList<Conferencia> conferencias = new ArrayList<>();
            ArrayList<Qualificacao> qualificacoes = new ArrayList<>();
                    
                
            if(id.equals("-d")){
                //docentes 
                //Essa forma de leitura eh so p/ teste
                csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\docentes.csv";
                
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    line = br.readLine();

                    while ((line = br.readLine()) != null) {

                        // usar o ; e \n como delimitador 
                        String[] token = line.split(cvsSplitBy,'\n');
                        //array de docentes criados pata store all of the docentes created.

                        long cod = Long.parseLong(token[0]);
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(token[2]);
                        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(token[3]);

                        boolean coord = false;
                        if("X".equals(token[4])){
                            coord = true;
                        }
                        docentes.put(cod,new Docente(cod,token[1],date1,date2,coord));
                        //System.out.println(docentes.get(cod));
                        
                    }

                } catch (IOException e) {
                    //precisa  tratar outros erros as well 
                    e.printStackTrace();
                }
            
            }
            if(id.equals("-v")){
                //veiculos
                csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\veiculos.csv";
                
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    line = br.readLine();

                    while ((line = br.readLine()) != null) {
                        String[] token = line.split(cvsSplitBy,'\n');
                        
                        String sigla =token[0];
                        String nome = token[1];
                        char tipo = token[2].charAt(0);
                        double fdi = numberFormat.parse(token[3]).doubleValue();
                        String issn = token[4];
                        
                        veiculos.put(sigla,new Veiculo(sigla,nome,tipo,fdi,issn));
                        
                       
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(id.equals("-p")){
                //publicacoes 
                csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\publicacoes.csv";
                
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    line = br.readLine();

                    while ((line = br.readLine()) != null) {
                        String[] token = line.split(cvsSplitBy,'\n');
                        
                        int ano = Integer.parseInt(token[0]);
                        String siglaVeiculo = token[1];
                        // esses hashmaps estao sendo criados para serem passados como parametros da publicacao 
                        Map<String,Veiculo> nVeiculos = new HashMap<>();
                        nVeiculos.put(siglaVeiculo,veiculos.get(siglaVeiculo));
                        
   
                        String titulo = token[2];
                        
                        String cdAutores = token[3];
                        String[] lineToken3 = cdAutores.split(",");
                        Map<Long,Docente> listaAutores = new HashMap<>();
                        for(int i=0;i<lineToken3.length;i++){
                            listaAutores.put(Long.parseLong(lineToken3[i]),docentes.get(Long.parseLong(lineToken3[i])));
                        }
 
                        int numero = Integer.parseInt(token[4]);
                        String volumeStr = token[5]; // esperado vazio caso conferencia
                        String localConf = token[6];
                        int pagInicial = Integer.parseInt(token[7]);
                        int pagFinal = Integer.parseInt(token[8]);
                        
                        
                        if(volumeStr.equals("")){
                            conferencias.add(new Conferencia(localConf,ano,titulo,numero,nVeiculos,listaAutores,pagInicial,pagFinal));// colocar os parametros
                            //System.out.println("Here we go!");
                        }
                        else if(localConf.equals("")){
                            int volume = Integer.parseInt(volumeStr);
                            periodicos.add(new Periodico(volume,ano,titulo,numero,nVeiculos,listaAutores,pagInicial,pagFinal));//colocar os parametros
                            //System.out.println("Here we go again!");
                        } 
                       
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                  
            }
            if(id.equals("-q")){
                //qualificacoes
                
                csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\qualis.csv";
                
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    line = br.readLine();

                    while ((line = br.readLine()) != null) {
                        String[] token = line.split(cvsSplitBy,'\n');
                        
                        int ano = Integer.parseInt(token[0]);
                        String siglaVeiculo = token[1];
                        String nomeQualis =token[2];
                        
                        //colocar os parametros 
                        qualificacoes.add(new Qualificacao());
                        
                       
                      
                        
                       
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            if(id.equals("-r")){
                //regras pontuacoes
            }
            if(id.equals("-a")){
                //ano credenciamento
            }
            
		
                          
                
    }
	
}
