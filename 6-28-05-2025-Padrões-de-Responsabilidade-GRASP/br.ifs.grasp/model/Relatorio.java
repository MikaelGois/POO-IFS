package br.ifs.grasp.model;

public class Relatorio {
    private String dados; // Temporario, apenas para não ficar vazio

    public Relatorio(String dados) {
        this.dados = dados;
    }

    // Getters
    public String getDados() { return dados; }

    // Setters
    public void setDados(String dados) { this.dados = dados; }

    @Override
    public String toString() {
        return "Relatorio{" +
                "dados='" + dados + '\'' +
                '}';
    }
}
