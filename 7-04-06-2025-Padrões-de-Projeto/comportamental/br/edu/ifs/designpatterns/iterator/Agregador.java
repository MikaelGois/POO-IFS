package br.edu.ifs.designpatterns.iterator;

public interface Agregador<G> {
  Iterador<G> criarIterador();
}