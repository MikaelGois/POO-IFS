package br.ifs.tdd.model;

import br.ifs.tdd.exception.ValidacaoException;

public class Produto {
    private final String identificador;
    private final String nome;
    private final String descricao;
    private final double preco;

    public Produto(String identificador, String nome, String descricao, double preco) {
        if (identificador == null || identificador.isBlank()) {
            throw new ValidacaoException("Identificador obrigatório");
        }
        if (identificador.length() != 13)  {
            throw new ValidacaoException("Tamanho do identificador inválido (13 caracteres)");
        }
        if (!identificador.chars().allMatch(Character::isDigit))  {
            throw new ValidacaoException("Formato do identificador inválido (apenas números)");
        }
        if (nome == null || nome.isBlank())  {
            throw new ValidacaoException("Nome obrigatório");
        }
        if (nome.length() > 100)  {
            throw new ValidacaoException("Nome excede 100 caracteres");
        }
        if (descricao == null || descricao.isBlank())  {
            throw new ValidacaoException("Descrição obrigatória");
        }
        if (descricao.length() > 500)  {
            throw new ValidacaoException("Descrição excede 500 caracteres");
        }
        if (preco <= 0) {
            throw new ValidacaoException("Preço inválido (deve ser positivo)");
        }
        this.identificador = identificador;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getIdentificador() { return identificador; }
    public String getNome()         { return nome; }
    public String getDescricao()    { return descricao; }
    public double getPreco()        { return preco; }
}