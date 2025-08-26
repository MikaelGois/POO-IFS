package br.edu.ifs.designpatterns.interpreter.terminal;

import br.edu.ifs.designpatterns.interpreter.Expressao;

public class Variavel<T extends Comparable<T>> implements Expressao {
  private String nome;
  private T valor;

  public Variavel(String nome, T valor) {
    this.nome = nome;
    this.valor = valor;
  }

  public Variavel(T valor) {
    this.valor = valor;
    this.nome = valor.toString();
  }

  public T getValor() {
    return valor;
  }

  @Override
  public boolean interpretar() {
    return true;
  }

  @Override
  public String toString() {
    return nome;
  }
}