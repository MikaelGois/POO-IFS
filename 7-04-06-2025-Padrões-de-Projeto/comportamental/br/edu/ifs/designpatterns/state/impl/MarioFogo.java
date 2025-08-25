package br.edu.ifs.designpatterns.state.impl;

import br.edu.ifs.designpatterns.state.EstadoMario;

public class MarioFogo implements EstadoMario {
  @Override
  public EstadoMario pegarCogumelo() {
    return this;
  }

  @Override
  public EstadoMario pegarFlor() {
    return this;
  }

  @Override
  public EstadoMario pegarPena() {
    return new MarioVoador();
  }

  @Override
  public EstadoMario sofrerDano() {
    return new MarioSuper();
  }

  @Override
  public String atacar() {
    return "Bola de fogo";
  }

  @Override
  public String obterEstado() {
    return "Mario de fogo";
  }
}