package br.edu.ifs.designpatterns.strategy;

public class Ingresso {
  private double valor;
  private EstrategiaDesconto estrategia;

  public Ingresso(double valor, EstrategiaDesconto estrategia) {
    this.valor = valor;
    this.estrategia = estrategia;
  }

  public double calcularValor() {
    if (estrategia.getClass().getSimpleName().equals("EstrategiaDiaPais")) {
      return valor * estrategia.calcularDesconto();
    }
    return valor * estrategia.calcularDesconto();
  }
}