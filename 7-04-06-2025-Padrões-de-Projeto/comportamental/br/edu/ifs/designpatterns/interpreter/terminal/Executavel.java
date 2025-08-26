package br.edu.ifs.designpatterns.interpreter.terminal;

import br.edu.ifs.designpatterns.command.Comando;
import br.edu.ifs.designpatterns.interpreter.Expressao;

public class Executavel implements Expressao {
  private Comando comando;

  public Executavel(Comando comando) {
    this.comando = comando;
  }

  @Override
  public boolean interpretar() {
    comando.executar();
    return true;
  }

  @Override
  public String toString() {
    return "\t" + comando.toString();
  }
}