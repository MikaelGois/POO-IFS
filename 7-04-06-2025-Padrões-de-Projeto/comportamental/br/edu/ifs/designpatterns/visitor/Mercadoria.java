package br.edu.ifs.designpatterns.visitor;

public abstract class Mercadoria {
  protected double custo;

  public Mercadoria(double custo) {
    this.custo = custo;
  }

  public double getCusto() {
    return custo;
  }

  public abstract double aceitar(Taxador taxador);
}