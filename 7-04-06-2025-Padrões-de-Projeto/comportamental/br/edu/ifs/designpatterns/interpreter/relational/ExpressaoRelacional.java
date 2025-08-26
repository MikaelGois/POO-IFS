package br.edu.ifs.designpatterns.interpreter.relational;

import br.edu.ifs.designpatterns.interpreter.Expressao;
import br.edu.ifs.designpatterns.interpreter.terminal.Variavel;

public abstract class ExpressaoRelacional<T extends Comparable<T>> implements Expressao {
  protected Variavel<T> termo1;
  protected Variavel<T> termo2;

  public ExpressaoRelacional(Variavel<T> termo1, Variavel<T> termo2) {
    this.termo1 = termo1;
    this.termo2 = termo2;
  }
}