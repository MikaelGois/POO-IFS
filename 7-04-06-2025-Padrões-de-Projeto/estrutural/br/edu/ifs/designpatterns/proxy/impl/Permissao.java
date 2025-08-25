package br.edu.ifs.designpatterns.proxy.impl;

public class Permissao {
  private final boolean leitura;
  private final boolean escrita;

  public Permissao(boolean leitura, boolean escrita) {
    this.leitura = leitura;
    this.escrita = escrita;
  }

  public boolean podeLer() {
    return leitura;
  }

  public boolean podeEscrever() {
    return escrita;
  }
}