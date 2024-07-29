import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Olá, o que você gostaria de fazer?");
        System.out.println();

        var option = "";
        //Estudar essa linha melhor

        List<Task> task = new ArrayList<>();
        //task.add(new Task("Organização"));


        while (option != "5") {
            Main.imprimeMenu();
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    if (task.size() == 0) {
                        System.out.printf("Você ainda não adicionou nenhuma tarefa... \n Digite 2 para adicionar uma tarefa ou...");
                    } else {
                        System.out.println(task);
                    }

                    break;
                case "2":
                    System.out.print("Digite o titulo da tarefa: ");
                    var taskName = scanner.nextLine();
                    System.out.print("Digite a descrição da tarefa: ");
                    var description = scanner.nextLine();
                    System.out.print("Digite o estado da tarefa: ");
                    var status = scanner.nextLine();
                    task.add(new Task(taskName,description,status));

                    System.out.println();
                    System.out.println("Tarefa adicionada a sua lista de tarefas.");
                    System.out.println();
                    break;
                case "5":
                    return;
                default:
                    Main.imprimeMenu();
            }
        }
    }

    public static void imprimeMenu() {
        System.out.println("Digite o número referente a opção desejada e pressione ENTER");
        System.out.println();
        System.out.println("Opção 1: Visualizar Tarefas");
        System.out.println("Opção 2: Adicionar Tarefa");
        System.out.println("Opção 3: Editar Tarefa");
        System.out.println("Opção 4: Apagar tarefa");
        System.out.println("Opção 5: Encerrar Programa");
    }
}
