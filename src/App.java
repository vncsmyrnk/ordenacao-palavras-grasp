import static org.junit.jupiter.api.Assertions.assertEquals;

import Analisador.Arquivo;
import MochilaPalavras.Mochila;

public class App {
    public static void main(String[] args) throws Exception {
        long startTime, estimatedTime;
        final String[] filePaths = {
                "./data/palavras_100k.txt", "./data/palavras_100k.txt", "./data/palavras_100k.txt",
                "./data/palavras_200k.txt", "./data/palavras_200k.txt", "./data/palavras_200k.txt",
                "./data/palavras_300k.txt", "./data/palavras_300k.txt", "./data/palavras_300k.txt",
                "./data/palavras_400k.txt", "./data/palavras_400k.txt", "./data/palavras_400k.txt",
                "./data/palavras_500k.txt", "./data/palavras_500k.txt", "./data/palavras_500k.txt",
                "./data/palavras_600k.txt", "./data/palavras_600k.txt", "./data/palavras_600k.txt",
                "./data/palavras_700k.txt", "./data/palavras_700k.txt", "./data/palavras_700k.txt" };
        final String[] outputFilePaths = {
                "./output/palavras_100k_1.txt", "./output/palavras_100k_2.txt", "./output/palavras_100k_3.txt",
                "./output/palavras_200k_1.txt", "./output/palavras_200k_2.txt", "./output/palavras_200k_3.txt",
                "./output/palavras_300k_1.txt", "./output/palavras_300k_2.txt", "./output/palavras_300k_3.txt",
                "./output/palavras_400k_1.txt", "./output/palavras_400k_2.txt", "./output/palavras_400k_3.txt",
                "./output/palavras_500k_1.txt", "./output/palavras_500k_2.txt", "./output/palavras_500k_3.txt",
                "./output/palavras_600k_1.txt", "./output/palavras_600k_2.txt", "./output/palavras_600k_3.txt",
                "./output/palavras_700k_1.txt", "./output/palavras_700k_2.txt", "./output/palavras_700k_3.txt" };
        final String outputFileTerminationFrequencia = "_frequencia";
        final String outputFileTerminationOrdenacao = "_ordenacao";

        System.out.println("Primeira etapa. Ordenação de palavras dos arquivos.");

        for (int i = 0; i < filePaths.length; i++) {
            String filePath = filePaths[i];
            String outputPath = outputFilePaths[i];

            /* Caminho do arquivo de saida de cada operacao */

            String[] filePathSplitted = outputPath.substring(1).split("\\.");
            String outputPathOrdenacao = "." + filePathSplitted[0] +
                    outputFileTerminationOrdenacao
                    + "." + filePathSplitted[1];
            String outputPathFrequencia = "." + filePathSplitted[0] +
                    outputFileTerminationFrequencia
                    + "." + filePathSplitted[1];

            /* Ordenacao */

            Arquivo a = new Arquivo(filePath);

            System.out.println("Ordenando palavras do arquivo " + filePath + "...");
            startTime = System.currentTimeMillis();

            a.ordenacaoLexografica();
            estimatedTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Tempo total ordenação arquivo " + filePath + ": " +
                    estimatedTime + "s");

            System.out.println("Gerando arquivo com o resultado da ordenação...");
            a.geraArquivoPalavras(outputPathOrdenacao, false);
            System.out.println("Arquivo gerado: " + outputPathOrdenacao);

            /* Frequencia */

            Arquivo b = new Arquivo(filePath);

            System.out.println("Calculando frequência das palavras do arquivo " +
                    filePath + "...");
            startTime = System.currentTimeMillis();

            b.frequencia();
            estimatedTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Tempo total ordenação arquivo " + filePath + ": " +
                    estimatedTime + "s");

            System.out.println("Gerando arquivo com o resultado da frequência...");
            b.geraArquivoPalavras(outputPathFrequencia, true);
            System.out.println("Arquivo gerado: " + outputPathFrequencia);
        }

        System.out.println(
                "Segunda Etapa. Calculando valores máximos para o problema da mochila com as informações do arquivo 'palavras_100k.txt'. São consideradas as 50 palavras mais frequentes do arquivo considerando a frequência como o valor e o peso como a expansão de \u03C0.");

        Arquivo a = new Arquivo("./data/palavras_100k.txt");
        Mochila m = new Mochila(a);

        for (int i = 50; i <= 80; i++) {
            System.out.println("Encontrando o valor máximo para um mochila de peso (W) como " + i + "...");
            System.out.println("Valor máximo: " + m.valorMaximo(i));
        }
    }
}
