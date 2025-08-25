package br.edu.ifs.designpatterns.flyweight;

import br.edu.ifs.designpatterns.flyweight.impl.TipoBloco;
import java.util.HashMap;
import java.util.Map;

public class FabricaBlocos {
  private static final Map<String, Flyweight> flyweights = new HashMap<>();

  public static Flyweight obterTipo(String nome, boolean quebravel, boolean inflamavel, boolean empilhavel) {
    flyweights.putIfAbsent(nome, new TipoBloco(nome, quebravel, inflamavel, empilhavel));
    return flyweights.get(nome);
  }
}