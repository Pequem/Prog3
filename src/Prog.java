
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
        /*os parametros podem ser passados de qualquer forma
                sempre organizalos antes de ler os arquivos
         */
        //LEITURA DOS ARQUIVOS
        for (int j = 2; j < args.length;j++) {
            switch (args[j]) {
                case "-d":
                    c.ReadDocentes(args[++j]);
                    break;
                case "-v":
                    c.ReadVeiculos(args[++j]);
                    break;
                case "-p":
                    c.ReadPublicacoes(args[++j]);
                    break;
                case "-q":
                    c.ReadQualis(args[++j]);
                    break;
                case "-r":
                    c.ReadRegras(args[++j]);
                    break;
                case "-a":
                    c.ReadAnoCredenciamento(args[++j]);
                    break;
                default:
                    System.out.println("Comando " + args[j] + " é invalido");
                    return;
            }
        }
<<<<<<< HEAD
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
                optionWrite=2; // para nao escrever
                //READ ONLY OPTION
                System.out.println("READ ONLY ALL THE WAY");
                
            }
=======
>>>>>>> parent of 9b4eaff... 032
        
       c.WriteRecredenciamentoFile();
       c.WriteListaPublicacoes();
       c.WriteStatistics();
    }
}
