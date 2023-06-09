import Analisador.Arquivo;

public class App {
    public static void main(String[] args) throws Exception {
        Arquivo a = new Arquivo("./data/palavras_100k.txt");
        a.frequencia();
        a.geraArquivoPalavras("./output/palavras_100k_frequencia.txt", true);
    }
}
