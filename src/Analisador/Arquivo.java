package Analisador;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
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
     * 
     */
    public LinkedList<Palavra> readFile() throws IOException {
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

        this.palavras = palavrasOrdenadas;
        return this.palavras;
    }

    /**
     * 
     * @return
     * @throws IOException
     */
    public LinkedList<Palavra> ordenacaoLexografica() throws IOException {
        this.readFile();
        MergeSort<Palavra> ms = new MergeSort<>(this.palavras, new Comparator<Palavra>() {
            public int compare(Palavra p1, Palavra p2) {
                return p1.compareTo(p2);
            }
        });
        this.palavras = ms.sort();
        return this.palavras;
    }

    /**
     * 
     * @return
     * @throws IOException
     */
    public LinkedList<Palavra> ordenacaoFrequencia() throws IOException {
        // this.readFile();
        MergeSort<Palavra> ms = new MergeSort<>(this.palavras, new Comparator<Palavra>() {
            public int compare(Palavra p1, Palavra p2) {
                return p2.getFrequencia() - p1.getFrequencia();
            }
        });
        this.palavras = ms.sort();
        return this.palavras;
    }
}
