package br.edu.ifs.designpatterns.visitor.impl;

import br.edu.ifs.designpatterns.visitor.Mercadoria;
import br.edu.ifs.designpatterns.visitor.Taxador;

public class Vinho extends Mercadoria {
  public Vinho(double custo) {
    super(custo);
  }

  @Override
  public double aceitar(Taxador taxador) {
    return taxador.visitar(this);
  }
}