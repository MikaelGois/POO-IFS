package br.edu.ifs.designpatterns.bridge;

import br.edu.ifs.designpatterns.bridge.obj.Ponto;
import java.util.List;

public interface Plataforma {
  int getFPS();

  List<Ponto> animar(Ponto origem, Ponto destino);
}