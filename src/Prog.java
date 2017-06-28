
import java.text.ParseException;

public class Prog {

    public static void main(String[] args) throws ParseException {

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

        //LEITURA DOS ARQUIVOS
        String d = null, v = null, p = null, q = null, r = null, a = null;
        for (int j = 2; j < args.length; j++) {
            switch (args[j]) {
                case "-d":
                    d = args[++j];
                    break;
                case "-v":
                    v = args[++j];
                    break;
                case "-p":
                    p = args[++j];
                    break;
                case "-q":
                    q = args[++j];
                    break;
                case "-r":
                    r = args[++j];
                    break;
                case "-a":
                    a = args[++j];
                    break;
                default:
                    System.out.println("Comando " + args[j] + " é invalido");
                    return;
            }
        }
        
        Controller c = new Controller(d,v,p,q,r,a);

        c.WriteRecredenciamentoFile();
        c.WriteListaPublicacoes();
        c.WriteStatistics();
    }
}
