package br.ifs.grasp.model;

public class Produto {
    private String nome;
    private Moeda preco;
    private int quantidade;
    private String descricao;

    public Produto(String nome, Moeda preco, int quantidade, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    // Getters
    public String getNome() { return nome; }
    public Moeda getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public String getDescricao() { return descricao; }

    // Setters
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setPreco(Moeda preco) { this.preco = preco; }
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
