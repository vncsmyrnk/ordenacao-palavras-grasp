package MochilaPalavras;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import Analisador.Arquivo;
import Analisador.Palavra;
import Ordenador.MergeSort;

/*
 * Mochila de um problema da Mochila sem repeticoes 0/1 (https://pt.wikipedia.org/wiki/Problema_da_mochila)
 */

public class Mochila {
    private Arquivo arquivo;
    private LinkedList<ItemPalavra> itens;
    private HashMap<Integer, Integer> valoresMaximosAnteriores;
    private HashMap<Integer, Integer> pesosMaximosAnteriores;

    public Mochila(Arquivo arquivo) {
        this.itens = new LinkedList<>();
        this.arquivo = arquivo;
        this.valoresMaximosAnteriores = new HashMap<>();
        this.pesosMaximosAnteriores = new HashMap<>();
    }

    public Integer valorMaximo(Integer pesoMochila) throws IOException {
        this.carregaPrimeiras50Palavras();

        /* Busca informacoes de calculos anteriores */

        Integer pesoAtual = this.getPesoMaximo(pesoMochila);
        Integer valorAtual = this.getValorMaximo(pesoMochila);

        /* Nao foi encontrada o calculo do peso anterior. Redefine estatisticas */

        if (pesoAtual == 0) {
            this.redefineItens();
        }

        LinkedList<ItemPalavra> itensNaoIncluidos = this.itens
                .stream()
                .filter((item) -> item.naoEstaDentroMochila())
                .collect(Collectors.toCollection(LinkedList::new));

        /* Tenta incluir itens na mochila */

        for (ItemPalavra item : itensNaoIncluidos) {
            if (pesoAtual + item.getPeso() > pesoMochila) {
                break;
            }

            pesoAtual += item.getPeso();
            valorAtual += item.getValor();
            item.incluiNaMochila();
        }

        /* Salva valores ja calculados para os calculos futuros */

        this.pesosMaximosAnteriores.put(pesoMochila, pesoAtual);
        this.valoresMaximosAnteriores.put(pesoMochila, valorAtual);
        return valorAtual;
    }

    private Integer getPesoMaximo(Integer pesoMochila) {
        Integer pesoMaximo = this.pesosMaximosAnteriores.get(pesoMochila - 1);
        return pesoMaximo == null ? 0 : pesoMaximo;
    }

    private Integer getValorMaximo(Integer pesoMochila) {
        Integer valorMaximo = this.valoresMaximosAnteriores.get(pesoMochila - 1);
        return valorMaximo == null ? 0 : valorMaximo;
    }

    private void carregaPrimeiras50Palavras() throws IOException {
        if (!this.itens.isEmpty()) {
            return;
        }

        this.itens = new LinkedList<>();

        /*
         * Considera que as palavras obtidas do arquivo estao ordenadas por frequencia
         * em ordem decrescente
         */
        LinkedList<Palavra> palavrasFrequencia = this.arquivo.frequencia();
        palavrasFrequencia
                .subList(0, Math.min(palavrasFrequencia.size(), 50))
                .stream()
                .collect(Collectors.toCollection(LinkedList::new))
                .forEach((palavra) -> {
                    ItemPalavra item = new ItemPalavra(palavra, palavra.getFrequencia(),
                            PesosItem.PESOS[this.itens.size()]);
                    this.itens.add(item);
                });

        /* Ordena os itens pelo peso em ordem crescente */
        MergeSort<ItemPalavra> ms = new MergeSort<>(this.itens, ItemPalavra.getComparatorPesoCrescente());
        this.itens = ms.sort();
    }

    private void redefineItens() {
        this.itens
                .stream()
                .forEach((item) -> item.removeDaMochila());
    }
}
