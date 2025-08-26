package br.edu.ifs.designpatterns.command.impl;

import br.edu.ifs.designpatterns.command.Comando;
import br.edu.ifs.designpatterns.command.Televisao;

public class LigarTV implements Comando {
  private final Televisao tv;

  public LigarTV(Televisao tv) {
    this.tv = tv;
  }

  @Override
  public void executar() {
    tv.ligar();
  }

  @Override
  public String toString() {
    return "ligar " + tv.toString();
  }
}