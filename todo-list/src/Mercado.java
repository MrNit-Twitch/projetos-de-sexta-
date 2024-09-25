/*## Programa

1. Ler um arquivo do tipo CSV chamado `frutas.csv`
        2. Pegar input com dados de novas frutas
3. Ao final, salvar os dados das frutas adicionadas com os dados antigos em um arquivo CSV de mesmo nome

## Requisitos

Biblioteca padrão da JDK 17+

        ## Arquivo

Arquivo no formato CSV para ser usado no programa.

```csv
        nome;quantidade;preço
        laranja;45;3.59
goiaba;34;2.89
maçã verde;20;10.99
        ```

        ## Código final

Nome do arquivo tem que ser `Mercado.java`.

        ```java*/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Mercado {

    /* Objeto do tipo Path com a referência para o arquivo 'frutas.csv'
     * Esses objetos não criam arquivos, apenas tem a referência do caminho (path)
     * para algum arquivo. Usado por outras classes do pacote I/O e NIO para
     * operações com arquivos, como criar, ler, escrever, etc.*/
    private static final Path CSV_FILE = Path.of("frutas.csv");

    public static void main(String[] args) {
         /*Scanner com try-with-resources para fechar o scanner automaticamente quando encerrar ou
         uma exceção for lançada*/
        try (Scanner scanner = new Scanner(System.in)) {

            // Iniciar programa com lista vazia de frutas
            List<Fruta> frutas = new ArrayList<>();

            /*Checar se o arquivo existe primeiro, caso exista, ler o arquivo e popular a lista 'frutas' com
            conteúdo do arquivo
            Caso contrário, prosseguir com a lista vazia e preenchê-la mais tarde*/
            if (Files.exists(CSV_FILE)) {
                frutas.addAll(Mercado.carregarArquivo());
            }

            // Instancia uma nova ArrayLista contendo novas frutas adicionadas ou não
            frutas = new ArrayList<>(adicionarNovasFrutas(scanner, frutas));

            // Salvar a lista de frutas atualizada no arquivo CSV
            salvarArquivo(frutas);

        }
        // Imprimir arquivo CSV no console
        imprimirLinhasArquivoCSV();
    }

    /**
     * Imprimir conteúdo do arquivo CSV
     */
    private static void imprimirLinhasArquivoCSV() {
        System.out.println("========= frutas.csv =========");
        // Ler arquivo com Files.lines(Path path), retorna um Stream<String>.
        // Try-with-resources para fechar o Stream automaticamente
        try (Stream<String> linhasCSV = Files.lines(CSV_FILE)) {
            linhasCSV.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Arquivo '" + CSV_FILE.getFileName() +"' não encontrado");
        }
    }

    /**
     * Loop para que o usuário possa inserir ou não novas frutas na lista
     *
     * @param scanner input
     * @param frutas  lista de frutas
     */
    private static List<Fruta> adicionarNovasFrutas(Scanner scanner, List<Fruta> frutas) {
        while (true) {
            System.out.println("Adicionar alguma fruta [S/N]?");
            String resposta = scanner.nextLine().toLowerCase();
            if ("s".equals(resposta)) {

                // Pegar input do usuário como Strings e depois converter para não se preocupar com '\n'
                System.out.print("Nome da fruta ==> ");
                String nomeFruta = scanner.nextLine();
                int quantidadeFruta = 0;
                double precoDaFruta = 0;
                try {
                    System.out.print("Quantidade de frutas (int) ==> ");
                    quantidadeFruta = Integer.parseInt(scanner.nextLine());
                    System.out.print("Preço das frutas (double) ==> ");
                    precoDaFruta = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println(e + " Tipo de dado de entrada incorreto. Encerrando programa");
                    System.exit(1);
                }

                // Criar objetos do tipo Fruta a partir dos dados do usuário
                frutas.add(new Fruta(nomeFruta, quantidadeFruta, precoDaFruta));
            } else if ("n".equals(resposta)) {
                break;
            }
        }
        return frutas;
    }

    /**
     * Ler arquivo saveFile, criar objetos do tipo Fruta e adicionar a Lista
     *
     * @return Lista com objetos tipo Fruta
     */
    private static List<Fruta> carregarArquivo() {
        List<Fruta> frutaList = new ArrayList<>();
        try {
            // O Files. readAllLines() lê o arquivo inteiro e armazena cada linha em uma lista
            // Cada linha é um elemento na lista
            List<String> linhas = Files.readAllLines(CSV_FILE);

            // Se o arquivo estiver vazio ou conter apenas o cabeçalho, retornar lista vazia
            if (linhas.isEmpty()) {
                return frutaList;
            }

            linhas.get(0); // remove a primeira linha da lista, pois contem o cabeçalho do arquivo

            for (String linha : linhas) {
                String[] dados = linha.split(";"); // Separar a string quando encontrar um ';'
                // Adicionar um novo objeto do tipo Fruta à lista
                frutaList.add(new Fruta(dados[0],
                        Integer.parseInt(dados[1]),
                        Double.parseDouble(dados[2])));
            }
        } catch (IOException e) {
            // Se ocorrer uma exceção, encerra programa e preserva arquivo
            System.err.println("Não foi possível ler o arquivo!");
            System.exit(1);
        }
        return frutaList;
    }

    /**
     * Escrever a lista de frutas atualizada no arquivo CSV. Com cabeçalho e dados.
     *
     * @param frutas lista final de frutas
     */
    private static void salvarArquivo(List<Fruta> frutas) {
        try {
            /*Escrever cabeçalho
             * O writeString() não adiciona nova linha. Se não houver '\n' o programa irá escrever
             * todas as strings eu uma linha.
             * Caso o arquivo 'frutas.csv' não exista, o programa irá criar um novo automaticamente
             * ao executar operações para escrever em arquivos*/
            Files.writeString(CSV_FILE, "nome;quantidade;preço\n");

            for (Fruta fruta : frutas) {
                String frutaDados = fruta.nome() + ";" + fruta.quantidade() + ";" + fruta.preco() + "\n";
                /*Por padrão o 3 parâmetro do writeString() é StandardOpenOption.WRITE
                 * Ou seja, cada vez que writeString() for chamado, ele irá sobre-escrever
                 * o conteúdo do arquivo. StandardOpenOption.APPEND irá adicionar sem sobre-escrever*/
                Files.writeString(CSV_FILE, frutaDados, StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.err.println("Não foi possível salvar o arquivo");
        }
    }

}

/**
 * Record para criar objeto que irá representar um dado tabular do arquivo csv
 * Record: classe especial usada para criar objetos imutáveis de uma forma menos verbosa
 * Os atributos são definidos entre parentêses e na copilação são criados automaticamente
 * um construtor com todos os parâmetros, getters com apenas o nome dos atributos (não há setters, pois implicitamente
 * os atributos são 'private final'), um toString(), hashCode() e equals()
 *
 * https://dev.java/learn/records/
 */
record Fruta(String nome, int quantidade, double preco) {

}