package br.edu.ifs.designpatterns.memento;

import java.util.Stack;

public class Armazenador {
  private final Stack<Memento> mementos = new Stack<>();

  public void armazenar(Memento memento) {
    mementos.push(memento);
  }

  public Memento recuperar() {
    if (mementos.isEmpty()) {
      throw new IllegalStateException("Histórico vazio");
    }
    return mementos.pop();
  }
}