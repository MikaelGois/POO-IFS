package br.edu.ifs.designpatterns.decorator.impl;

import br.edu.ifs.designpatterns.decorator.Component;

public class Acucar extends Decorator {
  public Acucar(Component component) {
    super(component);
  }

  @Override
  public double custo() {
    return super.custo() + 0.3;
  }

  @Override
  public String getDescricao() {
    return super.getDescricao() + ", com açúcar";
  }
}