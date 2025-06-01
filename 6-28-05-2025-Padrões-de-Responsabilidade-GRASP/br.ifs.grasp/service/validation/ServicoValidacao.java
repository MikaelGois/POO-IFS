package br.ifs.grasp.service.validation;

import br.ifs.grasp.exception.ValidacaoException;
import br.ifs.grasp.model.Produto;

public class ServicoValidacao implements IValidacao {
    @Override
    public void validarProduto(Produto produto) throws ValidacaoException {
        if (produto == null) {
            throw new ValidacaoException("Produto não pode ser vazio.");
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new ValidacaoException("Nome do produto não pode ser vazio.");
        }
        if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
            throw new ValidacaoException("Descrição do produto não pode ser vazia.");
        }
        if (produto.getPreco() <= 0) {
            throw new ValidacaoException("Preço do produto invalido.");
        }
        System.out.println("Produto '" + produto.getNome() + "' validado com sucesso.");
    }

    @Override
    public void validarEstoque(Produto produto, int quantidadeDesejada) throws ValidacaoException {
        if (produto == null) {
            throw new ValidacaoException("Produto não pode ser vazio para validação de estoque.");
        }
        if (quantidadeDesejada <= 0) {
            throw new ValidacaoException("Quantidade desejada invalida.");
        }
        if (produto.getQuantidade() < 0) {
            throw new ValidacaoException("Quantidade do produto '" + produto.getNome() + "' está inválida.");
        }
        if (produto.getQuantidade() < quantidadeDesejada) {
            throw new ValidacaoException("Quantidade insuficiente para o produto '" + produto.getNome() +
                    "'. Disponível: " + produto.getQuantidade() +
                    ", Desejado: " + quantidadeDesejada);
        }
        System.out.println("Estoque para '" + produto.getNome() + "' validado com sucesso.");
    }
}
