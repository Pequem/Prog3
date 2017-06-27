
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
        
       c.WriteRecredenciamentoFile();
    }
}
