package br.edu.ifs.designpatterns.composite.impl;

import br.edu.ifs.designpatterns.composite.Componente;

public class Item extends Componente {
  private double preco;

  public Item(String nome, double preco) {
    super(nome);
    this.preco = preco;
  }

  @Override
  public double calcularPreco() {
    return this.preco;
  }
}