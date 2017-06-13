package prog3.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prog {
	
	public static void main(String[] args) throws ParseException{
            
            //Essa parte toda esta em andameto . .. 
            String csvFile = "\\Users\\user\\Desktop\\Prog 3 - teste\\teaching-br-prog3-20171-script-java\\testes\\01\\in\\docentes.csv";
            String line = "";
            String cvsSplitBy = ";";

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
		//Scanner s = new Scanner(System.in);
		//s.close();
	}
	
}
