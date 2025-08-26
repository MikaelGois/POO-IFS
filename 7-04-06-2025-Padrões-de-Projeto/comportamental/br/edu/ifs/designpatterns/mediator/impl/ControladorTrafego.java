package br.edu.ifs.designpatterns.mediator.impl;

import br.edu.ifs.designpatterns.mediator.Colaborador;
import br.edu.ifs.designpatterns.mediator.Mediador;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorTrafego implements Mediador {
  private List<Aeronave> filaPouso = new ArrayList<>();
  private List<Aeronave> filaDecolagem = new ArrayList<>();
  private Metereologia metereologia;
  private Oficina oficina;

  public void registrarColaborador(Colaborador colaborador) {
    if (colaborador instanceof Metereologia) {
      this.metereologia = (Metereologia) colaborador;
    } else if (colaborador instanceof Oficina) {
      this.oficina = (Oficina) colaborador;
    }
  }

  @Override
  public String notificar(Colaborador colaborador, String evento) {
    if (colaborador instanceof Aeronave) {
      return processarSolicitacaoAeronave((Aeronave) colaborador, evento);
    } else if (colaborador instanceof Metereologia && "condicoes".equals(evento)) {
      return processarFilas();
    }
    return "";
  }

  private String processarSolicitacaoAeronave(Aeronave aeronave, String evento) {
    if ("decolagem".equals(evento)) {
      if (!metereologia.condicoesFavoraveis()) {
        filaDecolagem.add(aeronave);
        return aeronave.getId() + ": Decolagem negada - condições metereológicas.";
      }
      if (oficina.necessitaManutencao(aeronave)) {
        return aeronave.getId() + ": Decolagem negada - manutenção.";
      }
      return aeronave.getId() + ": Decolagem autorizada.";
    } else if ("pouso".equals(evento)) {
      if (!metereologia.condicoesFavoraveis()) {
        filaPouso.add(aeronave);
        return aeronave.getId() + ": Pouso negado - condições metereológicas.";
      }
      return aeronave.getId() + ": Pouso autorizado.";
    }
    return "";
  }

  private String processarFilas() {
    String pousos = filaPouso.stream()
        .map(a -> a.getId() + ": Pouso autorizado.")
        .collect(Collectors.joining(", "));

    String decolagens = filaDecolagem.stream()
        .map(a -> notificar(a, "decolagem"))
        .collect(Collectors.joining(", "));

    filaPouso.clear();
    filaDecolagem.clear();

    return String.format("[%s]\n[%s]", pousos, decolagens);
  }
}