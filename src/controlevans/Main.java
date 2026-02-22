package controlevans;

public class Main {
    public static void main(String[] args) {
        SistemaDados.inicializarSistema();
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }
}