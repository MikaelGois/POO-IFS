package br.ifs.tdd.model;

import java.time.LocalDate;
import br.ifs.tdd.exception.ValidacaoException;

public class Lote {
    private Produto produto;
    private int quantidade;
    private LocalDate dataValidade;

    public Lote(Produto produto, int quantidade, LocalDate dataValidade) {
        if (produto == null) {
            throw new ValidacaoException("Produto inválido");
        }
        if (quantidade <= 0) {
            throw new ValidacaoException("Quantidade inválida (deve ser positiva)");
        }
        if (dataValidade == null) {
            throw new ValidacaoException("Data de validade obrigatória");
        }
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new ValidacaoException("Data de validade vencida");
        }

        this.produto = produto;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
