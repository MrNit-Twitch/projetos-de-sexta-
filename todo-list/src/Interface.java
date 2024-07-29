import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("O que você gostaria de fazer?");
        System.out.println("Digite o número referente a opção desejada e precione ENTER");
        System.out.println();
        System.out.println("Opção 1: Visualizar Tarefas");
        System.out.println("Opção 2: Adicionar Tarefa");
        System.out.println("Opção 3: Editar Tarefa");
        System.out.println("Opção 4: Apagar tarefa");
        System.out.println("Opção 5: Encerrar Programa");

        Integer option = Integer.parseInt(scanner.nextLine());
        //Estudar essa linha melhor

        List<Task> task = new ArrayList<>();
        //task.add(new Task("Organização"));

        int menu = scanner.nextInt();

        switch (menu){
            case 1:
                if (task.size() == 0) {
                    System.out.println("Não existem tarefas, escolha uma das opções anteriores");
                    //TODO: adicionar While para fazer o loop
                } else {
                    System.out.println(task);
                }
                break;
            case 2:
                System.out.print("Digite sua tarefa: ");
                var foo = scanner.nextLine();
                /*task.add(new Task(foo))*/;
                //TODO: Adicionar "tarefa adicionada"
                //TODO: While para voltar as tarefas anteriores
                System.out.println("Tarefa adicionada a sua lista de tarefas.");
                break;
        }

        /*if (option == 1) {
            if (task.size() == 0) {
                System.out.println("Não existem tarefas, escolha uma das opções anteriores");
                //TODO: adicionar While para fazer o loop
            } else {
                System.out.println(task);
            }
        } else if (option == 2){
            System.out.print("Digite sua tarefa: ");
            var foo = scanner.nextLine();
            task.add(new Task(foo));
            //TODO: Adicionar "tarefa adicionada"
            //TODO: While para voltar as tarefas anteriores
            System.out.println("Tarefa adicionada a sua lista de tarefas.");
        }*/
    }
}
