import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Usuario {

    String usuario = "admin";
    String senha = "1234";

    public boolean verificarLogin(String nome, String senhaDigitada) {
        return usuario.equals(nome) && senha.equals(senhaDigitada);
    }
}

class Impressora {

    int tipo;
    String modelo;
    String conexao;
    int parametro;
    boolean conexaoAberta = false;
    boolean configurada = false;

    public void configurar(int tipo, String modelo, String conexao, int parametro) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.conexao = conexao;
        this.parametro = parametro;
        this.configurada = true;
        System.out.println("Conexao configurada!");
    }
