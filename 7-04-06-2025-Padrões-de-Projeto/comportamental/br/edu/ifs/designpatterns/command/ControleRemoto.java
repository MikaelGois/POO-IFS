package br.edu.ifs.designpatterns.command;

public class ControleRemoto {
  private Comando comando;

  public void definirComando(Comando comando) {
    this.comando = comando;
  }

  public void pressionarBotao() {
    if (comando == null) {
      throw new IllegalStateException("Comando não definido");
    }
    comando.executar();
  }
}