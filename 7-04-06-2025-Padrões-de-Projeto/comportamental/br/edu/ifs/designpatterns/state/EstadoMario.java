package br.edu.ifs.designpatterns.state;

public interface EstadoMario {
  EstadoMario pegarCogumelo();

  EstadoMario pegarFlor();

  EstadoMario pegarPena();

  EstadoMario sofrerDano();

  String atacar();

  String obterEstado();
}