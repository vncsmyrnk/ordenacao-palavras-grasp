package MochilaPalavras;

import java.util.Comparator;

import Analisador.Palavra;

/*
 * Item de uma Mochila sem repeticoes 0/1 (https://pt.wikipedia.org/wiki/Problema_da_mochila)
 */

public class ItemPalavra implements Comparable<ItemPalavra> {
    private Palavra palavra;
    private int valor, peso;
    private boolean dentroMochila;

    public ItemPalavra(Palavra palavra) {
        this.palavra = palavra;
        this.valor = 0;
        this.peso = 0;
        this.dentroMochila = false;
    }

    public ItemPalavra(Palavra palavra, int valor, int peso) {
        this.palavra = palavra;
        this.valor = valor;
        this.peso = peso;
        this.dentroMochila = false;
    }

    public Palavra getPalavra() {
        return this.palavra;
    }

    public int getValor() {
        return this.valor;
    }

    public int getPeso() {
        return this.peso;
    }

    public boolean estaDentroMochila() {
        return this.dentroMochila;
    }

    public boolean naoEstaDentroMochila() {
        return !this.dentroMochila;
    }

    public void incluiNaMochila() {
        this.dentroMochila = true;
    }

    public void removeDaMochila() {
        this.dentroMochila = false;
    }

    /**
     * Retorna um comparator que ordena por ordem lexografica do conteudo
     * 
     * @return
     */
    public static Comparator<ItemPalavra> getComparatorPesoCrescente() {
        return new Comparator<ItemPalavra>() {
            public int compare(ItemPalavra p1, ItemPalavra p2) {
                int comparacao = p1.compareTo(p2);
                if (comparacao == 0) {
                    return p2.getValor() - p1.getValor();
                }
                return comparacao;
            }
        };
    }

    @Override
    public int compareTo(ItemPalavra p) {
        return this.getPeso() - p.getPeso();
    }
}
