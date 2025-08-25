package br.edu.ifs.designpatterns.builder.impl;

import br.edu.ifs.designpatterns.builder.Construtor;

public class ConstrutorComputador implements Construtor {

  private String processador;
  private int RAM;
  private int HD;

  @Override
  public void definirProcessador(String processador) {
    this.processador = processador;
  }

  @Override
  public void definirRAM(int RAM) {
    this.RAM = RAM;
  }

  @Override
  public void definirArmazenamento(int HD) {
    this.HD = HD;
  }

  @Override
  public Computador obterResultado() {
    return new Computador(processador, RAM, HD);
  }
}