package br.edu.ifs.designpatterns.template;

import java.util.ArrayList;
import java.util.List;

public abstract class Bebida {

  public final List<String> obterPreparo() {
    List<String> preparo = new ArrayList<>();
    preparo.add(prepararXicara());
    preparo.addAll(prepararIngredientes());
    preparo.add(prepararAguaQuente());
    return preparo;
  }

  protected abstract List<String> prepararIngredientes();

  private String prepararXicara() {
    return "Preparar uma xícara";
  }

  private String prepararAguaQuente() {
    return "Adicionar água quente";
  }
}