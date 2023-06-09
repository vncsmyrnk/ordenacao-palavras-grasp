package Analisador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import Ordenador.MergeSort;

public class Arquivo {
    private String caminho;
    private LinkedList<Palavra> palavras;

    public Arquivo(String caminho) {
        this.caminho = caminho;
        this.palavras = new LinkedList<>();
    }

    /**
     * Percorre o arquivo inicializando as palavras
     */
    public LinkedList<Palavra> carregaArquivo() throws IOException {
        if (!this.palavras.isEmpty()) {
            return this.palavras;
        }

        LinkedList<Palavra> palavras = new LinkedList<>();
        try (FileInputStream fis = new FileInputStream(this.caminho);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] palavrasLinha = line.split(" ");

                Arrays.stream(palavrasLinha)
                        .forEach(palavra -> {
                            palavras.add(new Palavra(palavra));
                        });
            }
        }

        this.palavras = palavras;
        return this.palavras;
    }

    /**
     * Contabiliza a frequencia de cada palavra econtrada no arquivo
     * 
     * @return
     * @throws IOException
     */
    public LinkedList<Palavra> frequencia() throws IOException {
        LinkedList<Palavra> palavrasOrdenadas = this.ordenacaoLexografica();
        if (palavrasOrdenadas.size() == 0) {
            return palavrasOrdenadas;
        }

        /* Evita ConcurrentModificationException */
        for (int i = 1; i < palavrasOrdenadas.size(); i++) {
            Palavra atual = palavrasOrdenadas.get(i);
            Palavra anterior = palavrasOrdenadas.get(i - 1);

            if (atual.equals(anterior)) {
                palavrasOrdenadas.remove(atual);
                anterior.novaOcorrencia();
                palavrasOrdenadas.set(--i, anterior);
            }
        }

        this.ordenacaoFrequencia();

        this.palavras = palavrasOrdenadas;
        return this.palavras;
    }

    /**
     * Ordena o arquivo lexograficamente
     * 
     * @return
     * @throws IOException
     */
    public LinkedList<Palavra> ordenacaoLexografica() throws IOException {
        this.carregaArquivo();
        MergeSort<Palavra> ms = new MergeSort<>(this.palavras, new Comparator<Palavra>() {
            public int compare(Palavra p1, Palavra p2) {
                return p1.compareTo(p2);
            }
        });
        this.palavras = ms.sort();
        return this.palavras;
    }

    /**
     * Ordena o arquivo em funcao da frequencia de cada palavra
     * 
     * @return
     * @throws IOException
     */
    public LinkedList<Palavra> ordenacaoFrequencia() throws IOException {
        this.carregaArquivo();
        MergeSort<Palavra> ms = new MergeSort<>(this.palavras, new Comparator<Palavra>() {
            public int compare(Palavra p1, Palavra p2) {
                int ordenacaoFrequencia = p2.getFrequencia() - p1.getFrequencia();
                if (ordenacaoFrequencia == 0) {
                    return p1.compareTo(p2);
                }
                return ordenacaoFrequencia;
            }
        });
        this.palavras = ms.sort();
        return this.palavras;
    }

    /**
     * @throws IOException
     * 
     */
    public void geraArquivoPalavras(String nomeArquivo, boolean mostraFrequencia) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Palavra p : this.palavras) {
                writer.write(
                        (mostraFrequencia ? p.getFrequencia() : "") + " " + p.getConteudo() + "\n");
            }
        }
    }
}
