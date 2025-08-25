package br.edu.ifs.designpatterns.decorator.impl;

import br.edu.ifs.designpatterns.decorator.Component;

public class Leite extends Decorator {
  public Leite(Component component) {
    super(component);
  }

  @Override
  public double custo() {
    return super.custo() + 0.5;
  }

  @Override
  public String getDescricao() {
    return super.getDescricao() + ", com leite";
  }
}