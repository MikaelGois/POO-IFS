package br.edu.ifs.designpatterns.memento.impl;

import br.edu.ifs.designpatterns.memento.Memento;

public class Jogo {
  private int saude;
  private int vidas;

  public Jogo() {
    this.saude = 100;
    this.vidas = 3;
  }

  public void causarDano(int dano) {
    if (vidas == 0) {
      throw new IllegalStateException("Fim de Jogo");
    }

    this.saude -= dano;

    if (this.saude <= 0) {
      this.vidas--;
      if (this.vidas > 0) {
        this.saude = 100;
      } else {
        this.saude = 0;
        // Lança a exceção assim que as vidas acabam
        throw new IllegalStateException("Fim de Jogo");
      }
    }
  }

  public Memento salvar() {
    return new Memento(this.saude, this.vidas);
  }

  public void restaurar(Memento memento) {
    if (memento == null) {
      throw new IllegalStateException("Histórico vazio");
    }
    this.saude = memento.getSaude();
    this.vidas = memento.getVidas();
  }

  @Override
  public String toString() {
    return String.format("{saúde: %d, vidas: %d}", this.saude, this.vidas);
  }
}