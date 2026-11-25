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

public class Main {

    private static final String ASSINATURA_QR_CANCELAMENTO =
            "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0" +
            "E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQ" +
            "sE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+Z" +
            "fO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Usuario usuario = new Usuario();
        Impressora impressora = new Impressora();

        boolean entrou = realizarLogin(entrada, usuario);

        if (entrou) {
            menu(entrada, impressora);
        } else {
            System.out.println("Acesso bloqueado!");
        }

        entrada.close();
    }

    private static boolean realizarLogin(Scanner entrada, Usuario usuario) {
        for (int i = 1; i <= 3; i++) {
            System.out.print("Digite o usuario: ");
            String nome = entrada.nextLine();

            System.out.print("Digite a senha: ");
            String senha = entrada.nextLine();

            if (usuario.verificarLogin(nome, senha)) {
                System.out.println("Login realizado com sucesso!");
                return true;
            }

            System.out.println("Usuario ou senha incorretos!");
            if (i < 3) {
                System.out.println("Tentativas restantes: " + (3 - i));
            } else {
                System.out.println("Numero maximo de tentativas atingido!");
            }
        }
        return false;
    }
    
 private static void menu(Scanner entrada, Impressora impressora) {
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

            opcao = lerInteiro(entrada, "Escolha uma opcao: ");

            if (opcao >= 4 && opcao <= 10 && !impressora.conexaoAberta) {
                System.out.println("Conexao fechada. Abra a conexao antes.");
                continue;
            }

            try {
                switch (opcao) {
                    case 1:
                        if (impressora.conexaoAberta) {
                            System.out.println("Feche a conexao antes de reconfigurar.");
                        } else {
                            configurarConexao(entrada, impressora);
                        }
                        break;


                    case 2:
                        if (!impressora.configurada) {
                            System.out.println("Configure a conexao primeiro!");
                        } else if (impressora.conexaoAberta) {
                            System.out.println("A conexao ja esta aberta.");
                        } else {
                            impressora.abrirConexao();
                        }
                        break;

                    case 3:
                        if (!impressora.conexaoAberta) {
                            System.out.println("A conexao ja esta fechada.");
                        } else {
                            impressora.fecharConexao();
                        }
                        break;

                    case 4:
                        System.out.print("Digite o texto que deseja imprimir: ");
                        String texto = entrada.nextLine();
                        impressora.imprimirTexto(texto);
                        break;

                    case 5:
                        System.out.print("Digite os dados do QR Code: ");
                        String qr = entrada.nextLine();
                        int tamanhoQR = lerInteiroNoIntervalo(entrada, "Tamanho (1 a 6): ", 1, 6);
                        int nivel = lerInteiroNoIntervalo(entrada, "Nivel de correcao (1 a 4): ", 1, 4);
                        impressora.imprimirQRCode(qr, tamanhoQR, nivel);
                        break;

                    case 6:
                        int tipoCodigo = lerTipoCodigoBarras(entrada);
                        System.out.print("Digite os dados do codigo: ");
                        String codigo = entrada.nextLine();
                        int altura = lerInteiroNoIntervalo(entrada, "Altura (1 a 255): ", 1, 255);
                        int largura = lerInteiroNoIntervalo(entrada, "Largura (1 a 6): ", 1, 6);
                        int hri = lerInteiroNoIntervalo(entrada, "HRI (1 a 4): ", 1, 4);
                        impressora.imprimirCodigoBarras(tipoCodigo, codigo, altura, largura, hri);
                        break;

                    case 7:
                        String xmlSAT = lerArquivoXML("XMLSAT.xml");
                        if (xmlSAT != null) {
                            impressora.imprimirXMLSAT(xmlSAT);
                        }
                        break;

                    case 8:
                        String xmlCancelamento = lerArquivoXML("CANC_SAT.xml");
                        if (xmlCancelamento != null) {
                            impressora.imprimirXMLCancelamentoSAT(
                                    xmlCancelamento, ASSINATURA_QR_CANCELAMENTO
                            );
                        }
                        break;

                    case 9:
                        int pino = lerInteiroNoIntervalo(entrada, "Pino (0 ou 1): ", 0, 1);
                        int ti = lerInteiroNoIntervalo(entrada, "Tempo inicial (1 a 255): ", 1, 255);
                        int tf = lerInteiroNoIntervalo(entrada, "Tempo final (1 a 255): ", 1, 255);
                        impressora.abrirGaveta(pino, ti, tf);
                        break;

                    case 10:
                        int quantidade = lerInteiroNoIntervalo(entrada, "Quantidade de bips (1 a 63): ", 1, 63);
                        int tempoInicio = lerInteiro(entrada, "Duracao do bip (ex.: 50): ");
                        int tempoFim = lerInteiroNoIntervalo(entrada, "Pausa entre bips (1 a 25): ", 1, 25);
                        impressora.sinalSonoro(quantidade, tempoInicio, tempoFim);
                        break;

                    case 11:
                        if (impressora.conexaoAberta) {
                            impressora.fecharConexao();
                        }
                        System.out.println("Programa encerrado!");
                        break;

                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            } catch (LinkageError e) {
                System.out.println("Nao foi possivel carregar ou executar a DLL: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro ao executar a operacao: " + e.getMessage());
            }
        }
    }

    private static void configurarConexao(Scanner entrada, Impressora impressora) {
        System.out.println("Tipos: 1 = USB, 2 = Serial, 3 = Rede, 4 = Bluetooth");
        int tipo = lerInteiroNoIntervalo(entrada, "Tipo de conexao: ", 1, 4);

        System.out.print("Modelo da impressora (i8 ou i9): ");
        String modelo = entrada.nextLine();

        System.out.print("Conexao (USB, COM2 ou IP): ");
        String conexao = entrada.nextLine();

        int parametro;
        if (tipo == 1) {
            parametro = 0;
            System.out.println("Parametro USB definido como 0.");
        } else if (tipo == 2) {
            parametro = lerInteiro(entrada, "Velocidade da conexao serial: ");
        } else if (tipo == 3) {
            parametro = lerInteiro(entrada, "Porta da conexao de rede: ");
        } else {
            parametro = lerInteiro(entrada, "Parametro da conexao Bluetooth: ");
        }

        impressora.configurar(tipo, modelo, conexao, parametro);
    }

    private static int lerTipoCodigoBarras(Scanner entrada) {
        while (true) {
            int tipo = lerInteiro(entrada, "Tipo do codigo de barras (0, 2, 3, 4 ou 8): ");
            if (tipo == 0 || tipo == 2 || tipo == 3 || tipo == 4 || tipo == 8) {
                return tipo;
            }
            System.out.println("Tipo nao listado. Use 0, 2, 3, 4 ou 8.");
        }
    }

    private static int lerInteiro(Scanner entrada, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido!");
            }
        }
    }

    private static int lerInteiroNoIntervalo(Scanner entrada, String mensagem, int minimo, int maximo) {
        while (true) {
            int valor = lerInteiro(entrada, mensagem);
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.println("Digite um valor entre " + minimo + " e " + maximo + ".");
        }
    }

    private static String lerArquivoXML(String nomeArquivo) {
        try {
            return new String(
                    Files.readAllBytes(Paths.get(nomeArquivo)),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            System.out.println("Nao foi possivel encontrar ou ler o arquivo " + nomeArquivo + ".");
            System.out.println(e.getMessage());
            return null;
        }
    }
}

