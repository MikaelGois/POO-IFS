package br.edu.ifs.designpatterns.memento;

public class Memento {
  private final int saude;
  private final int vidas;

  public Memento(int saude, int vidas) {
    this.saude = saude;
    this.vidas = vidas;
  }

  public int getSaude() {
    return saude;
  }

  public int getVidas() {
    return vidas;
  }
}