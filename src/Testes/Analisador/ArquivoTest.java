package Testes.Analisador;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;

import Analisador.Arquivo;
import Analisador.Palavra;
import MochilaPalavras.Mochila;

public class ArquivoTest {
    private final String basePath = "/home/vncsmyrnk/puc/5p/fpa/ordenacao-palavras-grasp/data/testes";

    @Test
    public void testLeituraArquivo() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste.txt");

        LinkedList<Palavra> palavrasEsperadas = new LinkedList<>();
        palavrasEsperadas.add(new Palavra("palavra1"));
        palavrasEsperadas.add(new Palavra("palavra2"));
        palavrasEsperadas.add(new Palavra("palavra1"));
        palavrasEsperadas.add(new Palavra("palavra3"));

        assertEquals(palavrasEsperadas, a.carregaPalavras());
    }

    @Test
    public void testOrdenacaoArquivo() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste2.txt");

        LinkedList<Palavra> palavrasEsperadas = new LinkedList<>();

        palavrasEsperadas.add(new Palavra("adipisci"));
        palavrasEsperadas.add(new Palavra("amet"));
        palavrasEsperadas.add(new Palavra("consectetur"));
        palavrasEsperadas.add(new Palavra("dolor"));
        palavrasEsperadas.add(new Palavra("dolorem"));
        palavrasEsperadas.add(new Palavra("est"));
        palavrasEsperadas.add(new Palavra("ipsum"));
        palavrasEsperadas.add(new Palavra("neque"));
        palavrasEsperadas.add(new Palavra("porro"));
        palavrasEsperadas.add(new Palavra("qui"));
        palavrasEsperadas.add(new Palavra("quia"));
        palavrasEsperadas.add(new Palavra("quisquam"));
        palavrasEsperadas.add(new Palavra("sit"));
        palavrasEsperadas.add(new Palavra("velit"));

        assertEquals(palavrasEsperadas, a.ordenacaoLexografica());
    }

    @Test
    public void testFrequenciaArquivo() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste3.txt");

        LinkedList<Palavra> palavrasEsperadas = new LinkedList<>();

        Palavra p1 = new Palavra("velit");
        p1.novaOcorrencia();
        p1.novaOcorrencia();
        p1.novaOcorrencia();

        Palavra p2 = new Palavra("est");
        p2.novaOcorrencia();
        p2.novaOcorrencia();

        Palavra p3 = new Palavra("adipisci");
        p3.novaOcorrencia();
        p3.novaOcorrencia();

        palavrasEsperadas.add(p1);
        palavrasEsperadas.add(p3);
        palavrasEsperadas.add(p2);
        palavrasEsperadas.add(new Palavra("consectetur"));
        palavrasEsperadas.add(new Palavra("dolor"));
        palavrasEsperadas.add(new Palavra("ipsum"));
        palavrasEsperadas.add(new Palavra("porro"));
        palavrasEsperadas.add(new Palavra("quisquam"));

        assertEquals(palavrasEsperadas, a.frequencia());
    }

    @Test
    public void testMochila1() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste3.txt");

        Mochila m = new Mochila(a);
        int valorCalculado = m.valorMaximo(10);
        assertEquals(7, valorCalculado);
    }

    @Test
    public void testMochila2() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste3.txt");

        Mochila m = new Mochila(a);
        int valorCalculado = m.valorMaximo(11);
        assertEquals(9, valorCalculado);
    }

    @Test
    public void testMochila3() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste3.txt");

        Mochila m = new Mochila(a);
        int valorCalculado = m.valorMaximo(12);
        assertEquals(9, valorCalculado);
    }

    @Test
    public void testMochila4() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste3.txt");

        Mochila m = new Mochila(a);
        int valorCalculado = m.valorMaximo(16);
        assertEquals(10, valorCalculado);
    }

    @Test
    public void testMochila5() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste3.txt");

        Mochila m = new Mochila(a);
        int valorCalculado = m.valorMaximo(20);
        assertEquals(10, valorCalculado);
    }

    @Test
    public void testMochila100k_50() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/../palavras_100k.txt");

        Mochila m = new Mochila(a);

        int valorCalculado = m.valorMaximo(50);
        assertEquals(19987, valorCalculado);

        valorCalculado = m.valorMaximo(51);
        assertEquals(19987, valorCalculado);

        valorCalculado = m.valorMaximo(52);
        assertEquals(19987, valorCalculado);

        valorCalculado = m.valorMaximo(53);
        assertEquals(19987, valorCalculado);

        valorCalculado = m.valorMaximo(54);
        assertEquals(21231, valorCalculado);

        valorCalculado = m.valorMaximo(80);
        assertEquals(24628, valorCalculado);
    }
}
