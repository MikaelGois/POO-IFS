package br.edu.ifs.designpatterns.builder.impl;

public class Computador {

  private String processador;
  private int RAM;
  private int HD;

  public Computador(String processador, int RAM, int HD) {
    this.processador = processador;
    this.RAM = RAM;
    this.HD = HD;
  }

  public String getProcessador() {
    return processador;
  }

  public int getRAM() {
    return RAM;
  }

  public int getHD() {
    return HD;
  }
}