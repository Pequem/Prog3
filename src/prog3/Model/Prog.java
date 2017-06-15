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
import java.util.Locale;

public class Prog {
	
	public static void main(String[] args) throws ParseException{
            
            //Locale.setDefault(new Locale("pt", "BR"));
            //DecimalFormat df = new java.text.DecimalFormat("###,####.##");
            Locale meuLocal = new Locale( "pt", "BR" ); 
            NumberFormat nfVal = NumberFormat.getCurrencyInstance( meuLocal );
            //Essa parte toda esta em andameto . .. 
            String id = "-v";
            //Scanner s = new Scanner(System.in);
		//s.close();
            String csvFile;
            String line="";
            String cvsSplitBy =";";
                    
                
            if(id.equals("-d")){
                //Essa forma de leitura eh so p/ teste
                csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\docentes.csv";
                
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    line = br.readLine();

                    while ((line = br.readLine()) != null) {

                        // usar o ; e \n como delimitador 
                        String[] token = line.split(cvsSplitBy,'\n');
                        //array de docentes criados pata store all of the docentes created.
                        ArrayList<Docente> docentes = new ArrayList<>();

                        long result = Long.parseLong(token[0]);
                        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(token[2]);
                        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(token[3]);

                        boolean coord = false;
                        if("X".equals(token[4])){
                            coord = true;
                        }
                        docentes.add(new Docente(result,token[1],date1,date2,coord));

                        for(Docente d : docentes){
                            System.out.println(docentes);
                        }
                    }

                } catch (IOException e) {

                    //precisa  tratar outros erros as well 
                    e.printStackTrace();
                }
            
            }
            if(id.equals("-v")){
                csvFile = "C:\\Users\\user\\Documents\\NetBeansProjects\\Prog3-master\\Prog3\\src\\prog3\\Model\\veiculos.csv";
                
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    line = br.readLine();

                    while ((line = br.readLine()) != null) {
                        String[] token = line.split(cvsSplitBy,'\n');
                        ArrayList<Veiculo> veiculos = new ArrayList<>();
                        
                        String sigla =token[0];
                        String nome = token[1];
                        char tipo = token[2].charAt(0);
                        //NUmber format problem
                        double fdi = Double.parseDouble("1.33");
                        String issn = token[4];
                        
                        veiculos.add(new Veiculo(sigla,nome,tipo,fdi,issn));
                        
                       
                    }

                } catch (IOException e) {
 
                    e.printStackTrace();
                }
            }
            
            
		
    }
	
}
