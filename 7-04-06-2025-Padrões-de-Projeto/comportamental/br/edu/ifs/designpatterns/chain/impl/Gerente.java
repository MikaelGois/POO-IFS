package br.edu.ifs.designpatterns.chain.impl;

import br.edu.ifs.designpatterns.chain.Analisador;

public class Gerente extends Analisador {
  @Override
  public Emprestimo processarRequisição(RequisicaoEmprestimo requisicao) {
    // O gerente é a última etapa, aprova a requisição se chegar até ele.
    return new Emprestimo(requisicao.getValor());
  }
}