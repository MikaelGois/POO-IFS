package br.edu.ifs.designpatterns.flyweight.impl;

import br.edu.ifs.designpatterns.flyweight.Flyweight;

public class TipoBloco implements Flyweight {
  // Estado Intrínseco
  private final String nome;
  private final boolean quebravel;
  private final boolean inflamavel;
  private final boolean empilhavel;

  public TipoBloco(String nome, boolean quebravel, boolean inflamavel, boolean empilhavel) {
    this.nome = nome;
    this.quebravel = quebravel;
    this.inflamavel = inflamavel;
    this.empilhavel = empilhavel;
  }

  @Override
  public String mostrar(int x, int y, int z) {
    return String.format("%s, %s, %s, %s, x: %d, y: %d, z: %d",
        nome,
        quebravel ? "quebrável" : "inquebrável",
        inflamavel ? "inflamável" : "inflamável",
        empilhavel ? "empilhável" : "não empilhável",
        x, y, z);
  }
}