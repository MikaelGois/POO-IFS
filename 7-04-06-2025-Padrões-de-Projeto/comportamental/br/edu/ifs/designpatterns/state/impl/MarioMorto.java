package br.edu.ifs.designpatterns.state.impl;

import br.edu.ifs.designpatterns.state.EstadoMario;

public class MarioMorto implements EstadoMario {
  private void throwGameOver() {
    throw new IllegalStateException("Mario está sem vida!");
  }

  @Override
  public EstadoMario pegarCogumelo() {
    throwGameOver();
    return this;
  }

  @Override
  public EstadoMario pegarFlor() {
    throwGameOver();
    return this;
  }

  @Override
  public EstadoMario pegarPena() {
    throwGameOver();
    return this;
  }

  @Override
  public EstadoMario sofrerDano() {
    throwGameOver();
    return this;
  }

  @Override
  public String atacar() {
    throwGameOver();
    return "";
  }

  @Override
  public String obterEstado() {
    return "Mario morto";
  }
}