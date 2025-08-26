package br.edu.ifs.designpatterns.interpreter.conditional;

import br.edu.ifs.designpatterns.interpreter.Expressao;
import br.edu.ifs.designpatterns.interpreter.relational.ExpressaoRelacional;

public class ExpressaoCondicional implements Expressao {
  private ExpressaoRelacional<?> condicao;
  private Expressao expressao;

  public ExpressaoCondicional(ExpressaoRelacional<?> condicao, Expressao expressao) {
    this.condicao = condicao;
    this.expressao = expressao;
  }

  @Override
  public boolean interpretar() {
    if (condicao.interpretar()) {
      return expressao.interpretar();
    }
    return false;
  }

  @Override
  public String toString() {
    return "se " + condicao.toString() + " então:\n" + expressao.toString();
  }
}