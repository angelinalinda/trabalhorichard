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
    
public void abrirConexao() {
        int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(
                tipo, modelo, conexao, parametro
        );

        if (retorno == 0) {
            conexaoAberta = true;
            System.out.println("Conexao aberta com sucesso!");
        } else {
            System.out.println("Erro ao abrir conexao. Codigo: " + retorno);
        }
    }

    public void fecharConexao() {
        int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

        if (retorno == 0) {
            conexaoAberta = false;
            System.out.println("Conexao fechada com sucesso!");
        } else {
            System.out.println("Erro ao fechar conexao. Codigo: " + retorno);
        }
    }

    public void imprimirTexto(String dados) {
        // 0 = alinhamento a esquerda, fonte A e tamanho normal.
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(dados, 0, 0, 0);

        // ImpressaoTexto e diferente dos outros comandos: retorna a quantidade de dados enviados.
        if (retorno >= 0) {
            System.out.println("Texto enviado. Quantidade de dados enviados: " + retorno);
        } else {
            System.out.println("Erro ao imprimir texto. Codigo: " + retorno);
        }
    }

    public void imprimirQRCode(String dados, int tamanho, int nivelCorrecao) {
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, tamanho, nivelCorrecao);
        mostrarRetorno("Impressao do QR Code", retorno);
    }

    public void imprimirCodigoBarras(int tipo, String dados, int altura, int largura, int hri) {
        int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(
                tipo, dados, altura, largura, hri
        );
        mostrarRetorno("Impressao do codigo de barras", retorno);
    }

    public void imprimirXMLSAT(String dados) {
        int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados, 0);
        mostrarRetorno("Impressao do XML SAT", retorno);
    }

    public void imprimirXMLCancelamentoSAT(String dados, String assinaturaQRCode) {
        int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(
                dados, assinaturaQRCode, 0
        );
        mostrarRetorno("Impressao do XML de cancelamento", retorno);
    }

    public void abrirGaveta(int pino, int ti, int tf) {
        int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(pino, ti, tf);
        mostrarRetorno("Abertura da gaveta", retorno);
    }

   public void sinalSonoro(int quantidade, int tempoInicio, int tempoFim) {
        int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(
                quantidade, tempoInicio, tempoFim
        );
        mostrarRetorno("Sinal sonoro", retorno);
    }

    private void mostrarRetorno(String operacao, int retorno) {
        if (retorno == 0) {
            System.out.println(operacao + " realizada com sucesso!");
        } else {
            System.out.println("Erro em: " + operacao + ". Codigo: " + retorno);
        }
    }
}
