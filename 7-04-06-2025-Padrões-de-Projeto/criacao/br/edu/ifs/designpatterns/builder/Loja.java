package br.edu.ifs.designpatterns.builder;

import br.edu.ifs.designpatterns.builder.impl.Computador;

public class Loja {

  private Construtor construtor;

  public Loja(Construtor construtor) {
    this.construtor = construtor;
  }

  public Computador construirNotebook() {
    construtor.definirProcessador("Core™ i7-1355U");
    construtor.definirRAM(16);
    construtor.definirArmazenamento(512);
    return construtor.obterResultado();
  }

  public Computador construirNotebookGamer() {
    construtor.definirProcessador("Core i7-13650HX");
    construtor.definirRAM(16);
    construtor.definirArmazenamento(1024);
    return construtor.obterResultado();
  }

  public Computador construirNotebookUltra() {
    construtor.definirProcessador("Core Ultra 7 155H");
    construtor.definirRAM(32);
    construtor.definirArmazenamento(1024);
    return construtor.obterResultado();
  }
}