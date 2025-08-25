package br.edu.ifs.designpatterns.builder;

import br.edu.ifs.designpatterns.builder.impl.Computador;

public interface Construtor {
  void definirProcessador(String processador);

  void definirRAM(int RAM);

  void definirArmazenamento(int HD);

  Computador obterResultado();
}