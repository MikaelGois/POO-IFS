package br.edu.ifs.designpatterns.decorator.impl;

import br.edu.ifs.designpatterns.decorator.Component;

public class Creme extends Decorator {
  public Creme(Component component) {
    super(component);
  }

  @Override
  public double custo() {
    return super.custo() + 1.0;
  }

  @Override
  public String getDescricao() {
    return super.getDescricao() + ", com creme";
  }
}