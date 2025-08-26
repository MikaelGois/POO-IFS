package br.edu.ifs.designpatterns.command.impl;

import br.edu.ifs.designpatterns.command.Comando;
import br.edu.ifs.designpatterns.command.Televisao;

public class RetrocederCanalTV implements Comando {
  private Televisao tv;

  public RetrocederCanalTV(Televisao tv) {
    this.tv = tv;
  }

  @Override
  public void executar() {
    tv.retrocederCanal();
  }
}