package br.edu.ifs.designpatterns.interpreter.sequential;

import br.edu.ifs.designpatterns.interpreter.Expressao;
import java.util.ArrayList;
import java.util.List;

public class ExpressaoSequencial implements Expressao {
  private List<Expressao> expressoes = new ArrayList<>();

  public void addExpressao(Expressao expressao) {
    expressoes.add(expressao);
  }

  @Override
  public boolean interpretar() {
    for (Expressao expressao : expressoes) {
      expressao.interpretar();
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("execute:\n");
    for (Expressao exp : expressoes) {
      builder.append(exp.toString()).append("\n");
    }
    return builder.toString();
  }
}