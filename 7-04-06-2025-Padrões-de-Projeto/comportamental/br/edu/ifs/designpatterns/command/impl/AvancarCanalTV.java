package br.edu.ifs.designpatterns.command.impl;

import br.edu.ifs.designpatterns.command.Comando;
import br.edu.ifs.designpatterns.command.Televisao;

public class AvancarCanalTV implements Comando {
  private Televisao tv;

  public AvancarCanalTV(Televisao tv) {
    this.tv = tv;
  }

  @Override
  public void executar() {
    tv.avancarCanal();
  }
}