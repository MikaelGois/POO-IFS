package br.edu.ifs.designpatterns.iterator;

public interface Iterador<G> {
  G proximo();

  boolean temProximo();
}