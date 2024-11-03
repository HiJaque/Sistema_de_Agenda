import java.util.List;
import java.util.Scanner;

// Visão
public class SistemaDeAgenda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        Controlador controlador = new Controlador(agenda);
        ContatoFactory contatoFactory = new SimplesContatoFactory();

        while (true) {
            System.out.println("\nSistema de Agenda");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Listar contatos");
            System.out.println("3. Buscar contato");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Deseja adicionar um e-mail? (s/n): ");
                    String resposta = scanner.nextLine();
                    Contato novoContato = contatoFactory.criarContato(nome, telefone);

                    if (resposta.equalsIgnoreCase("s")) {
                        System.out.print("Digite o e-mail: ");
                        String email = scanner.nextLine();
                        novoContato = new ContatoComEmailDecorator(novoContato, email);
                    }

                    agenda.adicionarContato(novoContato);
                    System.out.println("Contato adicionado: " + novoContato);
                    break;
                case 2:
                    List<Contato> contatos = agenda.listarContatos();
                    if (contatos.isEmpty()) {
                        System.out.println("A agenda está vazia.");
                    } else {
                        for (Contato contato : contatos) {
                            System.out.println(contato);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Digite o nome do contato a buscar: ");
                    String nomeBusca = scanner.nextLine();
                    Contato contatoBuscado = agenda.buscarContato(nomeBusca);
                    if (contatoBuscado != null) {
                        System.out.println("Contato encontrado: " + contatoBuscado);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
