package br.edu.ifs.designpatterns.composite.impl;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifs.designpatterns.composite.Componente;

public class Combo extends Componente {
  private List<Componente> componentes = new ArrayList<>();

  public Combo(String nome) {
    super(nome);
  }

  public void adicionar(Componente componente) {
    componentes.add(componente);
  }

  @Override
  public double calcularPreco() {
    return componentes.stream()
        .mapToDouble(Componente::calcularPreco)
        .sum();
  }
}