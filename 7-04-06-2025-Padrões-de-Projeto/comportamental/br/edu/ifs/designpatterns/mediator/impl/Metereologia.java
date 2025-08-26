package br.edu.ifs.designpatterns.mediator.impl;

import br.edu.ifs.designpatterns.mediator.Colaborador;
import br.edu.ifs.designpatterns.mediator.Mediador;

public class Metereologia extends Colaborador {
  private boolean condicoesFavoraveis = false;

  public Metereologia(String id, Mediador mediador) {
    super(id, mediador);
  }

  public boolean condicoesFavoraveis() {
    return condicoesFavoraveis;
  }

  public void setCondicoesFavoraveis(boolean favoraveis) {
    this.condicoesFavoraveis = favoraveis;
  }

  public String informarCondicoes() {
    return mediador.notificar(this, "condicoes");
  }
}