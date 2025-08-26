package br.edu.ifs.designpatterns.chain.impl;

import br.edu.ifs.designpatterns.chain.Analisador;

public class AnalistaCredito extends Analisador {
  @Override
  public Emprestimo processarRequisição(RequisicaoEmprestimo requisicao) {
    if (requisicao.getValor() > requisicao.getRenda() * 0.3) {
      throw new IllegalStateException("Analista de crédito: Reprovado! Renda insuficiente.");
    }
    return (sucessor != null) ? sucessor.processarRequisição(requisicao) : new Emprestimo(requisicao.getValor());
  }
}