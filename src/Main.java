
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




    }
}
