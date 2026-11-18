
import java.util.Scanner;

class Usuario {

    String login;
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




    }
}
