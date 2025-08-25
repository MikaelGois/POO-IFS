package br.edu.ifs.designpatterns.state.impl;

import br.edu.ifs.designpatterns.state.EstadoMario;
import br.edu.ifs.designpatterns.state.Mario;

public class MarioSuper implements EstadoMario {
  @Override
  public EstadoMario pegarCogumelo() {
    return this; // Já é Super
  }

  @Override
  public EstadoMario pegarFlor() {
    return new MarioFogo();
  }

  @Override
  public EstadoMario pegarPena() {
    return new MarioVoador();
  }

  @Override
  public EstadoMario sofrerDano() {
    return new MarioPequeno();
  }

  @Override
  public String atacar() {
    return "Super salto";
  }

  @Override
  public String obterEstado() {
    return "Super Mario";
  }
}