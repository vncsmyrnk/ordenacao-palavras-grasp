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
}
