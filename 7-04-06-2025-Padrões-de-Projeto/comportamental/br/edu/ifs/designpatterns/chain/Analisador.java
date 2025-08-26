package br.edu.ifs.designpatterns.chain;

import br.edu.ifs.designpatterns.chain.impl.Emprestimo;
import br.edu.ifs.designpatterns.chain.impl.RequisicaoEmprestimo;

public abstract class Analisador {
  protected Analisador sucessor;

  public void definirSucessor(Analisador sucessor) {
    this.sucessor = sucessor;
  }

  public abstract Emprestimo processarRequisição(RequisicaoEmprestimo requisicao);
}