import Analisador.Arquivo;

public class App {
    public static void main(String[] args) throws Exception {
        long startTime, estimatedTime;
        final String[] filePaths = { "./data/palavras_100k.txt", "./data/palavras_200k.txt", "./data/palavras_300k.txt",
                "./data/palavras_400k.txt", "./data/palavras_500k.txt", "./data/palavras_600k.txt",
                "./data/palavras_700k.txt" };
        final String[] outputFilePaths = { "./output/palavras_100k.txt", "./output/palavras_200k.txt",
                "./output/palavras_300k.txt",
                "./output/palavras_400k.txt", "./output/palavras_500k.txt", "./output/palavras_600k.txt",
                "./output/palavras_700k.txt" };
        final String outputFileTerminationFrequencia = "_frequencia";
        final String outputFileTerminationOrdenacao = "_ordenacao";

        for (int i = 0; i < filePaths.length; i++) {
            String filePath = filePaths[i];
            String outputPath = outputFilePaths[i];

            /* Caminho do arquivo de saida de cada operacao */

            String[] filePathSplitted = outputPath.substring(1).split("\\.");
            String outputPathOrdenacao = "." + filePathSplitted[0] + outputFileTerminationOrdenacao
                    + "." + filePathSplitted[1];
            String outputPathFrequencia = "." + filePathSplitted[0] + outputFileTerminationFrequencia
                    + "." + filePathSplitted[1];

            /* Ordenacao */

            Arquivo a = new Arquivo(filePath);

            System.out.println("Ordenando palavras do arquivo " + filePath + "...");
            startTime = System.currentTimeMillis();

            a.ordenacaoLexografica();
            estimatedTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Tempo total ordenação arquivo " + filePath + ": " + estimatedTime + "s");

            System.out.println("Gerando arquivo com o resultado da ordenação...");
            a.geraArquivoPalavras(outputPathOrdenacao, false);
            System.out.println("Arquivo gerado: " + outputPathOrdenacao);

            /* Frequencia */

            Arquivo b = new Arquivo(filePath);

            System.out.println("Calculando frequência das palavras do arquivo " + filePath + "...");
            startTime = System.currentTimeMillis();

            b.frequencia();
            estimatedTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Tempo total ordenação arquivo " + filePath + ": " + estimatedTime + "s");

            System.out.println("Gerando arquivo com o resultado da frequência...");
            b.geraArquivoPalavras(outputPathFrequencia, true);
            System.out.println("Arquivo gerado: " + outputPathFrequencia);
        }
    }
}
