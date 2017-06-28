
import java.text.ParseException;

public class Prog {

    public static void main(String[] args) throws ParseException {

        Controller c = new Controller();
        //ler como parametros 
        if (args.length == 0) {
            System.out.println("No Command Line arguments");
        } else {
            System.out.println("You provided " + args.length
                    + " arguments");
            for (int i = 0; i < args.length; i++) {
                System.out.println("args[" + i + "]: " + args[i]);
            }
        }

        //---------------- LISTAS/HashMaps------------------
        //Scanner s = new Scanner(System.in);
        //s.close();
        
        int leu=0;
        int optionRead=0,optionWrite=0;
        
        
        //LEITURA DOS ARQUIVOS
        for(int k = 2; k< args.length;k++){
            if(args[k].compareTo("--read-only") == 0){
                optionRead=1;
            }
            if(args[k].compareTo("--write-only") == 0){
                optionWrite=1;
            }
        }
        if((optionRead ==0) || (optionRead==1)){
            for (int k = 2;k < args.length;k++){
                for (int j = 2; j < args.length;j++) {
                    if((leu == 0) && (args[j].compareTo("-d") == 0)){
                            c.ReadDocentes(args[++j]);
                            leu++;
                    }
                    else if((leu == 1) && (args[j].compareTo("-v") == 0)){
                            c.ReadVeiculos(args[++j]);
                            leu++;
                    }
                    else if((leu == 2) && (args[j].compareTo("-p") == 0)){
                            c.ReadPublicacoes(args[++j]);
                            leu++;
                    }
                    else if((leu == 3) && (args[j].compareTo("-q") == 0)){
                            c.ReadQualis(args[++j]);
                            leu++;
                    }
                    else if((leu == 4) && (args[j].compareTo("-r") == 0)){
                            c.ReadRegras(args[++j]);
                            leu++;
                    }
                    else if((leu == 5) && (args[j].compareTo("-a") == 0)){
                            c.ReadAnoCredenciamento(args[++j]);
                            leu++;
                    }

                }
            }
            
            if(optionRead == 1){
                //READ ONLY OPTION
                
                
            }
        
        }
       
        if(optionWrite ==0){
            c.WriteRecredenciamentoFile();
            c.WriteListaPublicacoes();
            c.WriteStatistics();
        }
        if(optionWrite ==1){
            //WRITE ONLY OPTION
        }
    }
}
