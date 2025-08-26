package br.edu.ifs.designpatterns.mediator.impl;

import br.edu.ifs.designpatterns.mediator.Colaborador;
import br.edu.ifs.designpatterns.mediator.Mediador;
import java.util.ArrayList;
import java.util.List;

public class Oficina extends Colaborador {
  private List<Aeronave> necessitaManutencao = new ArrayList<>();

  public Oficina(String id, Mediador mediador) {
    super(id, mediador);
  }

  public void adicionar(Aeronave aeronave) {
    necessitaManutencao.add(aeronave);
  }

  public boolean necessitaManutencao(Aeronave aeronave) {
    return necessitaManutencao.contains(aeronave);
  }
}