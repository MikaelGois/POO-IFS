package br.ifs.grasp.service.validation;

import br.ifs.grasp.exception.ValidacaoException;
import br.ifs.grasp.model.Produto;

public interface IValidacao {
    void validarProduto(Produto produto) throws ValidacaoException;
    void validarEstoque(Produto produto, int quantidadeDesejada) throws ValidacaoException;
}
