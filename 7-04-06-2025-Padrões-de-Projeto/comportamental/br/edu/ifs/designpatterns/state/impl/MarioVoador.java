package br.edu.ifs.designpatterns.state.impl;

import br.edu.ifs.designpatterns.state.EstadoMario;

public class MarioVoador implements EstadoMario {
  @Override
  public EstadoMario pegarCogumelo() {
    return this;
  }

  @Override
  public EstadoMario pegarFlor() {
    return new MarioFogo();
  }

  @Override
  public EstadoMario pegarPena() {
    return this;
  }

  @Override
  public EstadoMario sofrerDano() {
    return new MarioSuper();
  }

  @Override
  public String atacar() {
    return "Capa giratória";
  }

  @Override
  public String obterEstado() {
    return "Mario voador";
  }
}