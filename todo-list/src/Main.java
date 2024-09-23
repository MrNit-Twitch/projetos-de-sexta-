import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\"- Olá, o que você gostaria de fazer?\""); // \ para adicionar simbolos como as aspas duplas em uma string.
        System.out.println();

        // CONTINUAR AQUI criar um arquivo CSV e salvar as Tasks nele (tarefas_savefile.csv)

        List<Task> tasks = new ArrayList<>(); //As listas de tarefas são salvas aqui;

        // Continuação = (vc abre um objeto do FileOutputStream, aponta no construtor para o endereço do seu arquivo. E faz um loop for-each ou similar pra escrever usando o método write do fileOutput)

        var option = "";//Variavel vazia onde posso colocar o scanner para o menu opções

        while (option != "5") {
            Main.imprimeMenu();
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    if (tasks.size() == 0) {
                        System.out.printf("= Você ainda não adicionou nenhuma tarefa... = \n- Digite 2 para adicionar uma tarefa ou...\n");
                    } else {
                        System.out.println();
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("TAREFA " + (i+1));
                            System.out.println(tasks.get(i));
                        }
                    }
                    break;
                case "2":
                    System.out.print("Digite o titulo da tarefa: ");
                    var taskName = scanner.nextLine();
                    System.out.print("Digite a descrição da tarefa: ");
                    var description = scanner.nextLine();
                    System.out.print("Digite o estado da tarefa: ");
                    var status = scanner.nextLine();
                    tasks.add(new Task(taskName,description,status));
                    Main.salvaArquivo(taskName, description, status);
                    System.out.println();
                    System.out.println("- Tarefa adicionada a sua lista de tarefas.");
                    System.out.println();
                    break;
                case "3":
                    if (tasks.size() == 0) {
                        System.out.printf("= Não existem tarefas para serem editadas = \n- Digite 2 para adicionar uma tarefa ou...\n");
                        break;
                    } else {
                        System.out.println();
                        System.out.println("- Digite o numero da tarefa que deseja editar.");
                        int n = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (n < 0 || n >= tasks.size()){
                            System.out.println("= Opção Inválida =");
                        } else {
                            System.out.println("- Digite o número referente a opção que deseja alterar e pressione ENTER.");
                            System.out.println();
                            System.out.println("Opção 1: Editar NOME");
                            System.out.println("Opção 2: Editar DESCRÇÃO");
                            System.out.println("Opção 3: Atualizar Status");
                            int n2 = scanner.nextInt();
                            if (n2 == 1){
                                System.out.println("Digite o novo nome para a tarefa");
                                scanner.nextLine();
                                String newTask = scanner.nextLine();
                                tasks.get(n).setTaskName(newTask);
                                System.out.println("= Tarefa editada com sucesso! =");
                                System.out.println();
                                System.out.println();
                            } else if (n2 ==2) {
                                System.out.println("Digite a nova descrição para a tarefa");
                                scanner.nextLine();
                                String newDescription = scanner.nextLine();
                                tasks.get(n).setDescription(newDescription);
                                System.out.println("= Tarefa editada com sucesso! =");
                                System.out.println();
                                System.out.println();
                            } else if (n2 == 3) {
                                System.out.println("Digite o novo status da tarefa");
                                scanner.nextLine();
                                String newStatus = scanner.nextLine();
                                tasks.get(n).setStatus(newStatus);
                                System.out.println("= Tarefa editada com sucesso! =");
                                System.out.println();
                                System.out.println();
                            } else {
                                System.out.println("= Opção Inválida =");
                                break;
                            }
                        }
                    }
                    break;
                case "4":
                    if (tasks.size() == 0) {
                        System.out.printf("= Não existem tarefas para serem apagadas = \n- Digite 2 para adicionar uma tarefa ou...\n");
                    } else {
                        System.out.println();
                        System.out.println(" - Digite o numero da tarefa que deseja apagar.");
                        int n = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (n < 0 || n >= tasks.size()){
                            System.out.println("= Opção Inválida =");
                        } else {
                            tasks.remove(n);
                            System.out.println("- Tarefa apagada com sucesso!");
                            System.out.println();
                            System.out.println();
                        }
                        }
                    break;
                case "5":
                    return;
                default:
                    System.out.println("= Opção Inválida =");
            }
        }
    }

    public static void imprimeMenu() {
        // Clase criada apenas para mostrar o menu, externa a classe principal
        //"Access Specifier - public" = Acessível a partir de qualquer objeto, independentemente do package.
        //"Keyword - static" - contém apenas membros estáticos, só podendo acessar seus membros usando o próprio nome da classe.
        //"Return type - void" - indica que o método "main" não retorna nenhum valor.
        //"Method name - imprimeMenu" - nome do metodo.
        //"Parametros - ()" - adiciona parametros ao metodo, Ex: String args, println("Escrevendo...").
        System.out.println("- Digite o número referente a opção desejada e pressione ENTER.");
        System.out.println();
        System.out.println("Opção 1: Visualizar Tarefas");
        System.out.println("Opção 2: Adicionar Tarefa");
        System.out.println("Opção 3: Editar Tarefa");
        System.out.println("Opção 4: Apagar tarefa");
        System.out.println("Opção 5: Encerrar Programa");
    }

    public static void salvaArquivo(String titulo, String descricao, String status) {
        try {
            FileWriter myWriter = new FileWriter("./file.csv");

            myWriter.write(String.format("%s;%s;%s\n", titulo, descricao, status));

            myWriter.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
