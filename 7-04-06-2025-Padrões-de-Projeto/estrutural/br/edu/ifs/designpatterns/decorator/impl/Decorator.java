package br.edu.ifs.designpatterns.decorator.impl;

import br.edu.ifs.designpatterns.decorator.Component;

public abstract class Decorator implements Component {
  protected Component component;

  public Decorator(Component component) {
    this.component = component;
  }

  @Override
  public double custo() {
    return component.custo();
  }

  @Override
  public String getDescricao() {
    return component.getDescricao();
  }
}