package br.edu.ifs.designpatterns.command;

public class Luz {
  private String local;
  private boolean ligada = false;

  public Luz(String local) {
    this.local = local;
  }

  public void ligar() {
    this.ligada = true;
  }

  public void desligar() {
    this.ligada = false;
  }

  public boolean estaLigada() {
    return ligada;
  }

  @Override
  public String toString() {
    return this.local;
  }
}