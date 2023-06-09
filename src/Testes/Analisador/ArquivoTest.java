package Testes.Analisador;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;

import Analisador.Arquivo;
import Analisador.Palavra;

public class ArquivoTest {
    private final String basePath = "/home/vncsmyrnk/puc/5p/fpa/ordenacao-palavras-grasp/data";

    @Test
    public void testLeituraArquivo() throws IOException {
        Arquivo a = new Arquivo(
                this.basePath + "/arquivo-teste.txt");

        LinkedList<Palavra> palavrasEsperadas = new LinkedList<>();
        palavrasEsperadas.add(new Palavra("palavra1"));
        palavrasEsperadas.add(new Palavra("palavra2"));
        palavrasEsperadas.add(new Palavra("palavra1"));
        palavrasEsperadas.add(new Palavra("palavra3"));

        assertEquals(palavrasEsperadas, a.readFile());
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
        palavrasEsperadas.add(p2);
        palavrasEsperadas.add(p3);
        palavrasEsperadas.add(new Palavra("quisquam"));
        palavrasEsperadas.add(new Palavra("porro"));
        palavrasEsperadas.add(new Palavra("ipsum"));
        palavrasEsperadas.add(new Palavra("dolor"));
        palavrasEsperadas.add(new Palavra("consectetur"));

        a.frequencia();
        LinkedList<Palavra> palavrasMaisFrequentes = a.ordenacaoFrequencia();

        assertEquals(palavrasEsperadas, palavrasMaisFrequentes);
    }
}
