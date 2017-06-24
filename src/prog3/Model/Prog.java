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
            
            
            //---------------- LISTAS/HashMaps------------------
            //Scanner s = new Scanner(System.in);
		//s.close();
            int anoCredenciamento;
            String csvFile;
            String line="";
            String cvsSplitBy =";";
            Map<Long,Docente> docentes = new HashMap<>();;
            Map<String,Veiculo> veiculos = new HashMap<>();
            ArrayList<Publicacao> publicacoes = new ArrayList<>();
            ArrayList<Qualificacao> qualificacoes = new ArrayList<>();
            Regras regras;
            
            /*os parametros podem ser passados de qualquer forma
                sempre organizalos antes de ler os arquivos
            */
            //LEITURA DOS ARQUIVOS
           for(int j=2;j<args.length;j=j+2){
                String id = args[j];
                
                if(id.equals("-d")){
                     csvFile = "C:\\Users\\user\\Desktop\\Prog 3 - teste\\teaching-br-prog3-20171-script-java\\testes\\01\\in\\docentes.csv";
 
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
                            
                            if(docentes.containsKey(cod)){
                                System.out.println("Código repetido para Docente " + cod);
                            }else{
                                docentes.put(cod,new Docente(cod,token[1],date1,date2,coord));
                            }
 
                        }
                        
                        System.out.println("docentes.csv lido!");
                        
                    } catch (IOException e) {
                        
                        System.out.println("Erro de I/O");
                        
                    }

                }else if(id.equals("-v")){
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
                            
                             if(veiculos.containsKey(sigla)){
                                System.out.println("Código repetido para Veiculo " + sigla);
                             }else{
                                veiculos.put(sigla,new Veiculo(sigla,nome,tipo,fdi,issn));
                             }
 
                        }
                        System.out.println("veiculos.csv lido!");
                    } catch (IOException e) {
                       System.out.println("Erro de I/O");
                    }
                }
                else if(id.equals("-p")){
                    csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\publicacoes.csv";
 
                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        line = br.readLine();
 
                        while ((line = br.readLine()) != null) {
                            String[] token = line.split(cvsSplitBy,'\n');
 
                            int ano = Integer.parseInt(token[0].trim());
                            String siglaVeiculo = token[1].trim();                           
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
                                Publicacao c1 = new Conferencia(localConf,ano,titulo,numero,veiculos.get(siglaVeiculo),listaAutores,pagInicial,pagFinal);
                                publicacoes.add(c1);
                                
                                //adicionando as publicacoes nos veiculos 
                                veiculos.get(siglaVeiculo).setPublicacoesVeiculo(c1);
                                
                                for(int i=0;i<lineToken3.length;i++){
                                    //adicionando as conferencias aos respectivos docentes
                                    docentes.get(Long.parseLong(lineToken3[i])).setPublicacaoDocente(c1);
                                }
                               
                            }
                            else if(localConf.equals("")){
                                int volume = Integer.parseInt(volumeStr);
                                Publicacao p1 = new Periodico(volume,ano,titulo,numero,veiculos.get(siglaVeiculo),listaAutores,pagInicial,pagFinal);
                                publicacoes.add(p1);
                                
                                //adicionando as publicacoes nos veiculos 
                                veiculos.get(siglaVeiculo).setPublicacoesVeiculo(p1);
                                
                                for(int i=0;i<lineToken3.length;i++){
                                    //adicionando as periodicos aos respectivos docentes
                                    docentes.get(Long.parseLong(lineToken3[i])).setPublicacaoDocente(p1);
                                }
                            } 
 
                        }
                        System.out.println("publicacoes.csv lido!");
                    } catch (IOException e) {
                        
                        System.out.println("Erro de I/O");
                        
                    }

                }
                else if(id.equals("-q")){
                    
                       csvFile = "C:\\Users\\user\\Desktop\\Prog 3 - teste\\teaching-br-prog3-20171-script-java\\testes\\01\\in\\qualis.csv";
 
                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        line = br.readLine();
 
                        while ((line = br.readLine()) != null) {
                            String[] token = line.split(cvsSplitBy,'\n');
 
                            int ano = Integer.parseInt(token[0].trim());
                            String siglaVeiculo = token[1].trim();
                            String nomeQualis =token[2].trim();
                            
                            if(nomeQualis.equals("A1") || nomeQualis.equals("A2")
                                    || nomeQualis.equals("B1") || nomeQualis.equals("B2")
                                            || nomeQualis.equals("B3")||
                                    nomeQualis.equals("B4") || nomeQualis.equals("B5")
                                    || nomeQualis.equals("C")){
                            
                            Qualis qualis = new Qualis(nomeQualis);
                            Qualificacao q1 = new Qualificacao(ano,qualis,veiculos.get(siglaVeiculo));
                            qualificacoes.add(q1);
                            
                            //adicionar as qualificacoes nos veiculos
                            veiculos.get(siglaVeiculo).setQualificacoesVeiculo(q1);
                            
                            }else{
                                System.out.println("Qualis desconhecido para qualificação do veículo " + siglaVeiculo + 
                                        " no ano " + ano + ": " + nomeQualis +".");
                            }
                            
                            
                        }
                        System.out.println("qualis.csv lido!");
                    } catch (IOException e) {
                        
                        System.out.println("Erro de I/O");
                    }
                    
                }
                else if(id.equals("-r")){
                    csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\regras.csv";
 
                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        line = br.readLine();
 
                        while ((line = br.readLine()) != null) {
                            String[] token = line.split(cvsSplitBy,'\n');
                            Date dateInicio = new SimpleDateFormat("dd/MM/yyyy").parse(token[0]);
                            Date dateFim = new SimpleDateFormat("dd/MM/yyyy").parse(token[1]);
 
                            String sQualis = token[2].trim();
                            String[] lineQualis = sQualis.split(",");
 
                            ArrayList<Qualis> arrayQualis = new ArrayList<>();
                            ArrayList<Pontuacao> arrayPontuacao = new ArrayList<>();
 
                            String sPontos = token[3].trim();
                            String[] linePontos = sPontos.split(",");
                            
                            
                            for(int i=0;i<lineQualis.length;i++){
                                Qualis qu1 = new Qualis(lineQualis[i]);
                                Pontuacao pont = new Pontuacao(Integer.parseInt(linePontos[i]));
                                
                                
                                pont.setQualisPontuacoes(qu1);
                            }
 
 
                            double fm = numberFormat.parse(token[4].trim()).doubleValue();
                            int qtdAnos = Integer.parseInt(token[5].trim());
                            int ptMinima = Integer.parseInt(token[6].trim());
 
                            regras = new Regras(fm,dateInicio,dateFim,qtdAnos,ptMinima,arrayPontuacao);
 
                        }
                        System.out.println("regras.csv lido!");
                    } catch (IOException e) {
                        
                        System.out.println("Erro de I/O");

                    }
                }else if(id.equals("-a")){
                    
                }

            }     
    
        
        
    }
        
}
	

