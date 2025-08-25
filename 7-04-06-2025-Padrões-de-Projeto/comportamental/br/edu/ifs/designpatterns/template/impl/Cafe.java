package br.edu.ifs.designpatterns.template.impl;

import br.edu.ifs.designpatterns.template.Bebida;
import java.util.ArrayList;
import java.util.List;

public class Cafe extends Bebida {
  @Override
  protected List<String> prepararIngredientes() {
    List<String> ingredientes = new ArrayList<>();
    ingredientes.add("Adicionar uma colher de café");
    return ingredientes;
  }
}