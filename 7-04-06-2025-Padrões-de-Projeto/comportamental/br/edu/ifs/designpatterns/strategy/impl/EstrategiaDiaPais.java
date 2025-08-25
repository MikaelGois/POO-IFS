package br.edu.ifs.designpatterns.strategy.impl;

import br.edu.ifs.designpatterns.strategy.EstrategiaDesconto;

public class EstrategiaDiaPais implements EstrategiaDesconto {
  @Override
  public double calcularDesconto() {
    return 0.4; // 60% de desconto (retorna o valor a ser pago)
  }
}