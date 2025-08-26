package br.edu.ifs.designpatterns.mediator;

public interface Mediador {
  String notificar(Colaborador colaborador, String evento);
}