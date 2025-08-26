package br.edu.ifs.designpatterns.command.impl;

import br.edu.ifs.designpatterns.command.Comando;
import br.edu.ifs.designpatterns.command.Luz;

public class DesligarLuz implements Comando {
  private Luz luz;

  public DesligarLuz(Luz luz) {
    this.luz = luz;
  }

  @Override
  public void executar() {
    luz.desligar();
  }

  @Override
  public String toString() {
    return "desligar " + luz;
  }
}