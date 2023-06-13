package Analisador;

import java.util.Comparator;

public class Palavra implements Comparable<Palavra> {
    private String conteudo;
    private int frequencia;

    public Palavra(String conteudo) {
        this.conteudo = conteudo;
        this.frequencia = 1;
    }

    /**
     * Obtem o conteudo da palavra
     * 
     * @return
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Obtem a frequencia da palavra
     */
    public int getFrequencia() {
        return frequencia;
    }

    /**
     * Contabiliza uma ocorrencia da palavra
     */
    public void novaOcorrencia() {
        this.frequencia++;
    }

    /**
     * Retorna um comparator que ordena por ordem lexografica do conteudo
     * 
     * @return
     */
    public static Comparator<Palavra> getComparatorLexografico() {
        return new Comparator<Palavra>() {
            public int compare(Palavra p1, Palavra p2) {
                return p1.compareTo(p2);
            }
        };
    }

    /**
     * Retorna um comparator que retorna por ordem decrescente de frequencia e
     * crescente do conteudo
     * 
     * @return
     */
    public static Comparator<Palavra> getComparatorFrequencia() {
        return new Comparator<Palavra>() {
            public int compare(Palavra p1, Palavra p2) {
                int ordenacaoFrequencia = p2.getFrequencia() - p1.getFrequencia();
                if (ordenacaoFrequencia == 0) {
                    return p1.compareTo(p2);
                }
                return ordenacaoFrequencia;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        final Palavra p = (Palavra) o;
        return this.conteudo.equals(p.getConteudo());
    }

    @Override
    public int compareTo(Palavra p) {
        return this.getConteudo().compareTo(p.getConteudo());
    }

}
