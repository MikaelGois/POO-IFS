package br.edu.ifs.designpatterns.decorator.impl;

import br.edu.ifs.designpatterns.decorator.Component;

public class Cafe implements Component {
  @Override
  public double custo() {
    return 4.0;
  }

  @Override
  public String getDescricao() {
    return "Café";
  }
}