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



}

public class Main {
    public static void main(String[] args) {


    }
}
