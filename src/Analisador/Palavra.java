package Analisador;

public class Palavra {
    private String conteudo;
    private int frequencia;

    public Palavra(String conteudo) {
        this.conteudo = conteudo;
        this.frequencia = 0;
    }

    public String getConteudo() {
        return conteudo;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void novaOcorrencia() {
        this.frequencia++;
    }

    @Override
    public boolean equals(Object o) {
        final Palavra p = (Palavra) o;
        return this.conteudo.equals(p.getConteudo());
    }

}
