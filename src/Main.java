import java.util.Scanner;


class Usuario{

    String usuario = "admin";
    String senha = "1234";

    public boolean verificarLogin(String nome, String senhaDigitada) {

        if (usuario.equals(nome) && senha.equals(senhaDigitada)) {
            return true;
        } else {
            return false;
        }
    }
}


class Impressora {

    int tipo;
    String modelo;
    String conexao;
    int parametro;

    boolean conexaoAberta = false;

}

public class Main {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        Scanner entrada = new Scanner(System.in);
        Usuario usuario = new Usuario();
        Impressora impressora = new Impressora();

        boolean entrou = false;

        for (int i = 1; i <= 3; i++) {

            System.out.print("Digite o usuario: ");
            String nome = entrada.nextLine();

            System.out.print("Digite a senha: ");
            String senha = entrada.nextLine();

            if (usuario.verificarLogin(nome, senha)) {

                System.out.println("Login realizado com sucesso!");
                entrou = true;
                break;

            } else {

                System.out.println("Usuario ou senha incorretos!");

                if (i < 3) {
                    System.out.println("Tentativas restantes: " + (3 - i));
                } else {
                    System.out.println("Numero maximo de tentativas atingido!");
                }
            }
        }

        if (entrou) {

            int opcao = 0;

            while (opcao != 11) {

                System.out.println("\n===== IMPRESSORA ELGIN =====");
                System.out.println("1 - Configurar conexao");
                System.out.println("2 - Abrir conexao");
                System.out.println("3 - Fechar conexao");
                System.out.println("4 - Imprimir texto");
                System.out.println("5 - Imprimir QR Code");
                System.out.println("6 - Imprimir codigo de barras");
                System.out.println("7 - Imprimir XML SAT");
                System.out.println("8 - Imprimir XML de cancelamento");
                System.out.println("9 - Abrir gaveta");
                System.out.println("10 - Emitir sinal sonoro");
                System.out.println("11 - Sair");

                System.out.print("Escolha uma opcao: ");
                opcao = entrada.nextInt();

                switch (opcao) {

                    case 1:
                        System.out.println("Configurar conexao selecionado.");
                        break;

                    case 2:
                        System.out.println("Abrir conexao selecionado.");
                        break;

                    case 3:
                        System.out.println("Fechar conexao selecionado.");
                        break;

                    case 4:
                        System.out.println("Imprimir texto selecionado.");
                        break;

                    case 5:
                        System.out.println("Imprimir QR Code selecionado.");
                        break;

                    case 6:
                        System.out.println("Imprimir codigo de barras selecionado.");
                        break;

                    case 7:
                        System.out.println("Imprimir XML SAT selecionado.");
                        break;

                    case 8:
                        System.out.println("Imprimir XML de cancelamento selecionado.");
                        break;

                    case 9:
                        System.out.println("Abrir gaveta selecionado.");
                        break;

                    case 10:
                        System.out.println("Emitir sinal sonoro selecionado.");
                        break;

                    case 11:
                        System.out.println("Programa encerrado!");
                        break;

                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            }

        } else {

            System.out.println("Acesso bloqueado!");
        }

        entrada.close();


    }
}