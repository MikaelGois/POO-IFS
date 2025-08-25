package br.edu.ifs.designpatterns.template.impl;

import br.edu.ifs.designpatterns.template.Bebida;
import java.util.ArrayList;
import java.util.List;

public class Cha extends Bebida {
  @Override
  protected List<String> prepararIngredientes() {
    List<String> ingredientes = new ArrayList<>();
    ingredientes.add("Adicionar um sachê de chá");
    return ingredientes;
  }
}