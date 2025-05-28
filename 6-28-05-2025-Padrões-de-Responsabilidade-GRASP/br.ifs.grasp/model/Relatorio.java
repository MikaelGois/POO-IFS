package br.ifs.grasp.model;

public class Relatorio {
    private String temp; // Temporario, apenas para não ficar vazio

    public Relatorio(String dados) {
        this.temp = dados;
    }

    // Getters
    public String getTemp() { return temp; }

    // Setters
    public void setTemp(String temp) { this.temp = temp; }

    @Override
    public String toString() {
        return "Relatorio{" +
                "dados='" + temp + '\'' +
                '}';
    }
}
