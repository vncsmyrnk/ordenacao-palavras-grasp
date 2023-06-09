package Analisador;

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
