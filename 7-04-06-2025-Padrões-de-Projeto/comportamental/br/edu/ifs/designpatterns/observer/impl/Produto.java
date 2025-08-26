package br.edu.ifs.designpatterns.observer.impl;

import br.edu.ifs.designpatterns.observer.Sujeito;

public class Produto extends Sujeito {
  private String nome;
  private int quantidade;

  public Produto(String nome) {
    this.nome = nome;
    this.quantidade = 0;
  }

  public void adicionar(int quantidade) {
    this.quantidade += quantidade;
    if (estaDisponivel()) {
      notificar();
    }
  }

  public void remover(int quantidade) {
    this.quantidade -= quantidade;
  }

  public boolean estaDisponivel() {
    return this.quantidade > 0;
  }

  public String getNome() {
    return nome;
  }

  public int getQuantidade() {
    return quantidade;
  }
}