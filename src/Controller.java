
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import prog3.Model.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rodri
 */
public final class Controller {

    private final Map<Long, Docente> docentes = new HashMap<>();
    private final Map<String, Veiculo> veiculos = new HashMap<>();
    private final ArrayList<Publicacao> publicacoes = new ArrayList<>();
    private final ArrayList<Qualis> qualis = new ArrayList<>();
    private final ArrayList<Qualificacao> qualificacoes = new ArrayList<>();
    private Regras regras = new Regras();
    private int anoCredenciamento;

    private final String cvsSplitBy = ";";

    public Controller() {

    }

    public Controller(String d, String v, String p, String q, String r, String a) throws CustomException {
        ReadDocentes(d);
        ReadVeiculos(v);
        ReadPublicacoes(p);
        ReadQualis(q);
        ReadRegras(r);
        ReadAnoCredenciamento(a);
        WriteRecredenciamentoFile();
        WriteListaPublicacoes();
        WriteStatistics();
    }
    
        public Controller(String d, String v, String p, String q, String r, String a, String func) throws CustomException {
        ReadDocentes(d);
        ReadVeiculos(v);
        ReadPublicacoes(p);
        ReadQualis(q);
        ReadRegras(r);
        ReadAnoCredenciamento(a);
    }


    public void ReadDocentes(String csvFile) throws CustomException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {

                // usar o ; e \n como delimitador 
                String[] token = line.split(cvsSplitBy, '\n');
                //array de docentes criados pata store all of the docentes created.

                long cod = Long.parseLong(token[0].trim());
                Date date1, date2;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(token[2].trim());
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(token[3].trim());

                    boolean coord = false;
                    if ("X".equals(token[4].trim())) {
                        coord = true;
                    }

                    if (docentes.containsKey(cod)) {
                        throw new CustomException("Código repetido para Docente " + cod);
                    } else {
                        docentes.put(cod, new Docente(cod, token[1], date1, date2, coord));
                    }
                } catch (ParseException ex) {
                    throw new CustomException("Erro de formatação");
                }

            }
        } catch (IOException e) {
            throw new CustomException("Erro de I/O");
        }
    }

    public void ReadVeiculos(String csvFile) throws CustomException {
        String line;
        Locale ptBR = new Locale("pt", "BR");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(ptBR);
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] token = line.split(cvsSplitBy, '\n');

                String sigla = token[0].trim();
                String nome = token[1].trim();
                char tipo = token[2].charAt(0);
                if(!((tipo == 'C') || (tipo == 'P'))){
                    throw new CustomException("Tipo de veículo desconhecido para veículo "+sigla+": "+tipo);
                }
                double fdi;
                try {
                    fdi = numberFormat.parse(token[3].trim()).doubleValue();
                    String issn = token[4].trim();

                    if (veiculos.containsKey(sigla)) {
                        throw new CustomException("Código repetido para Veiculo " + sigla);
                    } else {
                        veiculos.put(sigla, new Veiculo(sigla, nome, tipo, fdi, issn));
                    }
                } catch (ParseException ex) {
                    throw new CustomException("Erro de formatação");
                }

            }
        } catch (IOException e) {
            throw new CustomException("Erro de I/O");
        }
    }

    public void ReadPublicacoes(String csvFile) throws CustomException {
        String line;
        Docente d;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] token = line.split(cvsSplitBy, '\n');

                int ano = Integer.parseInt(token[0].trim());
                String siglaVeiculo = token[1].trim();
                String titulo = token[2].trim();
                if(!veiculos.containsKey(siglaVeiculo)){
                    throw new CustomException("Sigla de veículo não definida usada na publicação \""+titulo+"\": "+siglaVeiculo);
                }
                String cdAutores = token[3].trim();

                String[] lineToken3 = cdAutores.split(",");

                ArrayList<Docente> listaAutores = new ArrayList<>();

                for (String lineToken31 : lineToken3) {
                    d = docentes.get(Long.parseLong(lineToken31));
                    if (d == null) {
                        throw new CustomException("Código de docente não definido usado na publicação \""+titulo+"\": " + lineToken31);
                    }
                    if (!listaAutores.contains(d)) {
                        listaAutores.add(d);
                    }
                }

                int numero = Integer.parseInt(token[4].trim());
                String volumeStr = token[5].trim(); // esperado vazio caso conferencia
                String localConf = token[6].trim();
                int pagInicial = Integer.parseInt(token[7].trim());
                int pagFinal = Integer.parseInt(token[8].trim());

                if (volumeStr.equals("")) {
                    Publicacao c1 = new Conferencia(localConf, ano, titulo, numero, veiculos.get(siglaVeiculo), listaAutores, pagInicial, pagFinal);
                    publicacoes.add(c1);

                    //adicionando as publicacoes nos veiculos 
                    veiculos.get(siglaVeiculo).setPublicacoesVeiculo(c1);

                    for (String lineToken31 : lineToken3) {
                        //adicionando as conferencias aos respectivos docentes
                        docentes.get(Long.parseLong(lineToken31)).setPublicacaoDocente(c1);
                    }

                } else if (localConf.equals("")) {
                    int volume = Integer.parseInt(volumeStr);
                    Publicacao p1 = new Periodico(volume, ano, titulo, numero, veiculos.get(siglaVeiculo), listaAutores, pagInicial, pagFinal);
                    publicacoes.add(p1);

                    //adicionando as publicacoes nos veiculos 
                    veiculos.get(siglaVeiculo).setPublicacoesVeiculo(p1);

                    for (String lineToken31 : lineToken3) {
                        //adicionando as periodicos aos respectivos docentes
                        docentes.get(Long.parseLong(lineToken31)).setPublicacaoDocente(p1);
                    }
                }

            }
        } catch (IOException e) {
            throw new CustomException("Erro de I/O");
        }
    }

    public void ReadQualis(String csvFile) throws CustomException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] token = line.split(cvsSplitBy, '\n');

                int ano = Integer.parseInt(token[0].trim());
                String siglaVeiculo = token[1].trim();
                String nomeQualis = token[2].trim();
                if(!veiculos.containsKey(siglaVeiculo)){
                    throw new CustomException("Sigla de veículo não definida usada na qualificação do ano \""+ano+"\": "+siglaVeiculo);
                }
                if (nomeQualis.equals("A1") || nomeQualis.equals("A2")
                        || nomeQualis.equals("B1") || nomeQualis.equals("B2")
                        || nomeQualis.equals("B3")
                        || nomeQualis.equals("B4") || nomeQualis.equals("B5")
                        || nomeQualis.equals("C")) {

                    Qualis qualisQualif = Qualis.valueOf(nomeQualis);
                    //System.out.println(qualis.getNome());
                    Qualificacao q1 = new Qualificacao(ano, qualisQualif, veiculos.get(siglaVeiculo));
                    qualificacoes.add(q1);

                    //adicionar as qualificacoes nos veiculos
                    veiculos.get(siglaVeiculo).setQualificacoesVeiculo(q1);

                } else {
                    throw new CustomException("Qualis desconhecido para qualificação do veículo " + siglaVeiculo
                            + " no ano " + ano + ": " + nomeQualis);
                }
            }
        } catch (IOException e) {

            throw new CustomException("Erro de I/O");
        }
    }

    public void ReadRegras(String csvFile) throws CustomException {
        String line;
        Locale ptBR = new Locale("pt", "BR");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(ptBR);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] token = line.split(cvsSplitBy, '\n');
                Date dateInicio = new SimpleDateFormat("dd/MM/yyyy").parse(token[0]);
                Date dateFim = new SimpleDateFormat("dd/MM/yyyy").parse(token[1]);

                String sQualis = token[2].trim();
                String[] lineQualis = sQualis.split(",");

                ArrayList<Qualis> arrayQualis = new ArrayList<>();
                ArrayList<Pontuacao> arrayPontuacao = new ArrayList<>();
                Map<Qualis, Pontuacao> mqp = new HashMap<>();
                String sPontos = token[3].trim();
                String[] linePontos = sPontos.split(",");

                for (int i = 0; i < lineQualis.length; i++) {
                    Pontuacao pont = new Pontuacao(Integer.parseInt(linePontos[i]));
                    Qualis qu1 = Qualis.valueOf(lineQualis[i]);
                    Qualis qu2 = Qualis.valueOf(lineQualis[i]);
                    if ((i + 1) < lineQualis.length) {
                        qu2 = Qualis.valueOf(lineQualis[i + 1]);
                    }
                    for (Qualis temp : EnumSet.range(qu1, qu2)) {
                        pont.setQualisPontuacoes(temp);
                        mqp.put(temp, pont);

                    }

                }

                double fm = numberFormat.parse(token[4].trim()).doubleValue();
                int qtdAnos = Integer.parseInt(token[5].trim());
                int ptMinima = Integer.parseInt(token[6].trim());

                regras = new Regras(fm, dateInicio, dateFim, qtdAnos, ptMinima, mqp);

            }
        } catch (IOException e) {

            throw new CustomException("Erro de I/O");

        } catch (ParseException ex) {
            throw new CustomException("Erro de formatação");
        }
    }

    public void ReadAnoCredenciamento(String ano) {
        anoCredenciamento = Integer.parseInt(ano);
    }

    public void WriteRecredenciamentoFile() throws CustomException {

        //faz uma copia de docentes para docentesOrdem
        Map<Long, Docente> docentesOrdem = new HashMap<>();
        docentes.entrySet().stream().forEach((entrada) -> {
            docentesOrdem.put(entrada.getKey(), entrada.getValue());
        });

        Comparator<Long> comparator2 = new ValueComparator<>(docentesOrdem);
        TreeMap<Long, Docente> mapDocentesOrdenado = new TreeMap<>(comparator2);

        mapDocentesOrdenado.putAll(docentesOrdem); // estao ordenados em ordem alfabetica
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("1-recrendenciamento.csv");
            //cabeçalho do arquivo csv
            fileWriter.append("Docente;Pontuação;Recredenciado?");
            fileWriter.append("\n");

            for (Map.Entry<Long, Docente> entry : mapDocentesOrdenado.entrySet()) {
                double pontuacao = entry.getValue().getPontuacaoDocente(anoCredenciamento, regras);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                Calendar cal = Calendar.getInstance();
                cal.setTime(entry.getValue().getNascimento());
                int yearBirth = cal.get(Calendar.YEAR);
                int AnoIngresso = Integer.parseInt(sdf.format(entry.getValue().getIngresso()));
                int subAno = anoCredenciamento - AnoIngresso;
                int idade = anoCredenciamento - yearBirth;
                String sPonto = String.format("%.1f", pontuacao);

                //Escrita linha por linha segundo regras 
                if (entry.getValue().isCoordenador() == true) {
                    fileWriter.append(entry.getValue().getNome());
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append(sPonto);
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append("Coordenador");
                    fileWriter.append("\n");

                } else if (subAno < 3) {
                    fileWriter.append(entry.getValue().getNome());
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append(sPonto);
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append("PPJ");
                    fileWriter.append("\n");

                } else if (idade > 60) {
                    fileWriter.append(entry.getValue().getNome());
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append(sPonto);
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append("PPS");
                    fileWriter.append("\n");

                } else if (pontuacao >= regras.getPontuacaoMin()) {
                    fileWriter.append(entry.getValue().getNome());
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append(sPonto);
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append("Sim");
                    fileWriter.append("\n");

                } else {
                    fileWriter.append(entry.getValue().getNome());
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append(sPonto);
                    fileWriter.append(cvsSplitBy);
                    fileWriter.append("Não");
                    fileWriter.append("\n");

                }

            }

        } catch (IOException e) {
            throw new CustomException("Erro de I/O");
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                throw new CustomException("Erro de I/O");
            }

        }
    }

    public void WriteListaPublicacoes() throws CustomException {

        publicacoes.sort((Publicacao p1, Publicacao p2) -> p1.getTitulo().compareTo(p2.getTitulo()));

        publicacoes.sort((Publicacao p1, Publicacao p2) -> p1.getVeiculo().getSigla().compareTo(p2.getVeiculo().getSigla()));

        publicacoes.sort((Publicacao p1, Publicacao p2) -> p2.getAno() - p1.getAno());

        publicacoes.sort((Publicacao p1, Publicacao p2) -> p1.getVeiculo().getQualificacoesVeiculo().get(0).getQualis().compareTo(p2.getVeiculo().getQualificacoesVeiculo().get(0).getQualis()));
        try (FileWriter f = new FileWriter("2-publicacoes.csv")) {
            f.append("Ano" + cvsSplitBy + "Sigla Veículo" + cvsSplitBy + "Veículo"
                    + cvsSplitBy + "Qualis" + cvsSplitBy + "Fator de Impacto" + cvsSplitBy
                    + "Título" + cvsSplitBy + "Docentes\n");

            for (Publicacao _p : publicacoes) {
                String fatorImp = String.format("%.3f", _p.getVeiculo().getFatorDeImpacto());
                f.append(_p.getAno() + cvsSplitBy + _p.getVeiculo().getSigla() + cvsSplitBy
                        + _p.getVeiculo().getNome() + cvsSplitBy + _p.getVeiculo().getQualificacoesVeiculo().get(0).getQualis()
                        + cvsSplitBy + fatorImp + cvsSplitBy + _p.getTitulo() + cvsSplitBy);

                int tamanho = 0;
                for (Docente _d : _p.getAutores()) {

                    f.append(_d.getNome());
                    if (tamanho < (_p.getAutores().size() - 1)) {
                        f.append(",");
                    }
                    tamanho++;

                }
                f.append("\n");
            }
        } catch (IOException ex) {
            throw new CustomException("Erro de I/O");
        }
    }

    public void WriteStatistics() throws CustomException {
        ArrayList<Publicacao> pArray;

        double ratio;
        try (FileWriter f = new FileWriter("3-estatisticas.csv")) {

            f.append("Qualis" + cvsSplitBy + "Qtd. Artigos" + cvsSplitBy + "Média Artigos / Docente\n");

            for (Qualis q : Qualis.values()) {

                pArray = publicacoes.get(0).getAllByQualis(q, publicacoes);
                ratio = publicacoes.get(0).getRatioByQualis(q, publicacoes);
                String mediaArtigosPorDocente = String.format("%.2f", ratio);
                f.append(q.getNome() + cvsSplitBy + pArray.size() + cvsSplitBy + mediaArtigosPorDocente + "\n");

            }

        } catch (IOException ex) {
            throw new CustomException("Erro de I/O");
        }
    }

}
