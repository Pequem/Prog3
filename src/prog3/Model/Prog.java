package prog3.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Prog {
	
	public static void main(String[] args) throws ParseException{
            
            Locale ptBR = new Locale( "pt", "BR" ); 
            NumberFormat numberFormat =  NumberFormat.getNumberInstance(ptBR); 
            
            //ler como parametros 
            if (args.length == 0) {

                System.out.println("No Command Line arguments");

            } else{

                System.out.println("You provided " + args.length 
                 + " arguments");

                for (int i = 0; i < args.length; i++) {
                 System.out.println("args[" + i + "]: "+ args[i]);
                }
            }
            
            
            //---------------- LISTAS------------------
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
            ArrayList<Regras> regras = new ArrayList<>();
            
            /*os parametros podem ser passados de qualquer forma
                sempre organizalos antes de ler os arquivos
            */
            //LEITURA DOS ARQUIVOS
            for(int j=2;j<14;j=j+2){
                
                String id = args[j];
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

                            long cod = Long.parseLong(token[0].trim());
                            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(token[2].trim());
                            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(token[3].trim());

                            boolean coord = false;
                            if("X".equals(token[4].trim())){
                                coord = true;
                            }
                            docentes.put(cod,new Docente(cod,token[1],date1,date2,coord));


                        }
                        System.out.println("docentes.csv lido!");
                    } catch (IOException e) {
                        //precisa  tratar outros erros as well 
                        e.printStackTrace();
                    }

                }
                else if(id.equals("-v")){
                    //veiculos
                    csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\veiculos.csv";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        line = br.readLine();

                        while ((line = br.readLine()) != null) {
                            String[] token = line.split(cvsSplitBy,'\n');

                            String sigla =token[0].trim();
                            String nome = token[1].trim();
                            char tipo = token[2].charAt(0);
                            double fdi = numberFormat.parse(token[3].trim()).doubleValue();
                            String issn = token[4].trim();

                            veiculos.put(sigla,new Veiculo(sigla,nome,tipo,fdi,issn));


                        }
                        System.out.println("veiculos.csv lido!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("-p")){
                    //publicacoes 
                    csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\publicacoes.csv";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        line = br.readLine();

                        while ((line = br.readLine()) != null) {
                            String[] token = line.split(cvsSplitBy,'\n');

                            int ano = Integer.parseInt(token[0].trim());
                            String siglaVeiculo = token[1].trim();
                            // esses hashmaps estao sendo criados para serem passados como parametros da publicacao 
                            Map<String,Veiculo> nVeiculos = new HashMap<>();
                            nVeiculos.put(siglaVeiculo,veiculos.get(siglaVeiculo));


                            String titulo = token[2].trim();

                            String cdAutores = token[3].trim();
                            String[] lineToken3 = cdAutores.split(",");
                            Map<Long,Docente> listaAutores = new HashMap<>();
                            for(int i=0;i<lineToken3.length;i++){
                                listaAutores.put(Long.parseLong(lineToken3[i]),docentes.get(Long.parseLong(lineToken3[i])));
                            }

                            int numero = Integer.parseInt(token[4].trim());
                            String volumeStr = token[5].trim(); // esperado vazio caso conferencia
                            String localConf = token[6].trim();
                            int pagInicial = Integer.parseInt(token[7].trim());
                            int pagFinal = Integer.parseInt(token[8].trim());


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
                        System.out.println("publicacoes.csv lido!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if(id.equals("-q")){
                    //qualificacoes

                    csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\qualis.csv";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        line = br.readLine();

                        while ((line = br.readLine()) != null) {
                            String[] token = line.split(cvsSplitBy,'\n');

                            int ano = Integer.parseInt(token[0].trim());
                            String siglaVeiculo = token[1].trim();
                            String nomeQualis =token[2].trim();
                            Qualis qualis = new Qualis(nomeQualis);
                            //colocar os parametros 
                            qualificacoes.add(new Qualificacao(ano,qualis,veiculos.get(siglaVeiculo)));


                        }
                        System.out.println("qualis.csv lido!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if(id.equals("-r")){
                    //regras pontuacoes
                    csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\regras.csv";

                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        line = br.readLine();

                        while ((line = br.readLine()) != null) {
                            String[] token = line.split(cvsSplitBy,'\n');
                            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(token[0]);
                            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(token[1]);

                            String sQualis = token[2].trim();
                            String[] lineQualis = sQualis.split(",");

                            List<Qualis> arrayQualis = new ArrayList<>();
                            List<Pontuacao> arrayPontuacao = new ArrayList<>();

                            for(int i=0;i<lineQualis.length;i++){
                              arrayQualis.add(new Qualis(lineQualis[i]));
                            }

                            String sPontos = token[3].trim();
                            String[] linePontos = sPontos.split(",");

                            for(int i=0;i<lineQualis.length;i++){
                              arrayPontuacao.add(new Pontuacao(Integer.parseInt(linePontos[i])));
                            }

                            Map<Qualis,Pontuacao> qualisPontuacao = new HashMap<>();
                            //assumindo que lineQualis e linePontos tem o mesmo tamanho

                            for(int i=0;i<lineQualis.length;i++){
                              qualisPontuacao.put(arrayQualis.get(i),arrayPontuacao.get(i));
                            }

                            double fm = numberFormat.parse(token[4].trim()).doubleValue();
                            int qtdAnos = Integer.parseInt(token[5].trim());
                            int ptMinima = Integer.parseInt(token[6].trim());

                            regras.add(new Regras(fm,date1,date2,qtdAnos,ptMinima,qualisPontuacao));

                        }
                        System.out.println("regras.csv lido!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if(id.equals("-a")){
                    //ano credenciamento
                    anoCredenciamento = Integer.parseInt(args[j+1]);
                }
                else{
                    System.out.println("Nao é um parametro valido");
                }
                

		
            }       
    }
	
}
