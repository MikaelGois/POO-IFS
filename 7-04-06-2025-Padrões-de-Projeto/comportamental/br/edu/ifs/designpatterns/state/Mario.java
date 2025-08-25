package br.edu.ifs.designpatterns.state;

import br.edu.ifs.designpatterns.state.impl.MarioPequeno;

public class Mario {
  private EstadoMario estado;

  public Mario() {
    this.estado = new MarioPequeno();
  }

  public void pegarCogumelo() {
    this.estado = estado.pegarCogumelo();
  }

  public void pegarFlor() {
    this.estado = estado.pegarFlor();
  }

  public void pegarPena() {
    this.estado = estado.pegarPena();
  }

  public void sofreDano() {
    this.estado = estado.sofrerDano();
  }

  public String atacar() {
    return estado.atacar();
  }

  public String obterEstado() {
    return estado.obterEstado();
  }
}