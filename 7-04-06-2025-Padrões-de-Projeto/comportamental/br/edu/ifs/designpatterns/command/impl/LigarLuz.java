package br.edu.ifs.designpatterns.command.impl;

import br.edu.ifs.designpatterns.command.Comando;
import br.edu.ifs.designpatterns.command.Luz;

public class LigarLuz implements Comando {
  private final Luz luz;

  public LigarLuz(Luz luz) {
    this.luz = luz;
  }

  @Override
  public void executar() {
    luz.ligar();
  }

  @Override
  public String toString() {
    return "ligar " + luz.toString();
  }
}