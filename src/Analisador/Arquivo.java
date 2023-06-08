package Analisador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

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
     */
    public LinkedList<Palavra> frequencia() {
        return new LinkedList<>(null);
    }

    /**
     * 
     * @return
     */
    public LinkedList<Palavra> ordenacaoLexografica() {
        return new LinkedList<>(null);
    }
}
