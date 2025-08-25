package br.edu.ifs.designpatterns.state.impl;

import br.edu.ifs.designpatterns.state.EstadoMario;
import br.edu.ifs.designpatterns.state.Mario;

public class MarioPequeno implements EstadoMario {
  @Override
  public EstadoMario pegarCogumelo() {
    return new MarioSuper();
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
    return new MarioMorto();
  }

  @Override
  public String atacar() {
    return "Salto";
  }

  @Override
  public String obterEstado() {
    return "Mario pequeno";
  }
}