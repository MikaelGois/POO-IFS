package br.edu.ifs.designpatterns.mediator;

import br.edu.ifs.designpatterns.mediator.impl.ControladorTrafego;

public abstract class Colaborador {
  protected Mediador mediador;
  protected String id;

  public Colaborador(String id, Mediador mediador) {
    this.id = id;
    this.mediador = mediador;
    // Colaborador se registra com o mediador concreto
    if (mediador instanceof ControladorTrafego) {
      ((ControladorTrafego) mediador).registrarColaborador(this);
    }
  }

  public String getId() {
    return id;
  }
}