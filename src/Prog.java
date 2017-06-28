
import java.text.ParseException;
import prog3.Model.CustomException;

public class Prog {

    public static void main(String[] args) throws ParseException {

        //ler como parametros 
        if (args.length == 0) {
            System.out.println("No Command Line arguments");
            return;
        }

        //LEITURA DOS ARQUIVOS
        String d = null, v = null, p = null, q = null, r = null, a = null, f = null;
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
                case "--read-only":
                    f = "r";
                    break;
                case "--write-only":
                    f = "w";
                    break;
                default:
                    System.out.println("Comando " + args[j] + " é invalido");
                    return;
            }
        }
        try{
            if(f == null){
                new Controller(d,v,p,q,r,a);
            }else{
                new Controller(d,v,p,q,r,a,f);
            }
        }catch(CustomException e){
            System.out.println(e.getMessage()+".");
        }
    }
}
