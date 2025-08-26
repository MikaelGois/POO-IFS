package br.edu.ifs.designpatterns.command;

public class Televisao {
  private String local;
  private boolean ligada = false;
  private int canal = 1;

  public Televisao(String local) {
    this.local = local;
  }

  public void ligar() {
    this.ligada = true;
  }

  public void desligar() {
    this.ligada = false;
  }

  public void avancarCanal() {
    if (!ligada)
      throw new IllegalStateException("Televisão desligada");
    this.canal++;
  }

  public void retrocederCanal() {
    if (!ligada)
      throw new IllegalStateException("Televisão desligada");
    this.canal--;
  }

  public boolean estaLigada() {
    return ligada;
  }

  public int getCanal() {
    return canal;
  }

  @Override
  public String toString() {
    return this.local;
  }
}