package br.edu.ifs.designpatterns.bridge.plat;

import br.edu.ifs.designpatterns.bridge.Plataforma;
import br.edu.ifs.designpatterns.bridge.obj.Ponto;

import java.util.ArrayList;
import java.util.List;

public class Playstation5 implements Plataforma {
  @Override
  public int getFPS() {
    return 3;
  }

  @Override
  public List<Ponto> animar(Ponto origem, Ponto destino) {
    List<Ponto> trajetória = new ArrayList<>();
    trajetória.add(origem);
    double deltaX = (destino.getX() - origem.getX()) / getFPS();
    double deltaY = (destino.getY() - origem.getY()) / getFPS();
    for (int i = 1; i <= getFPS(); i++) {
      trajetória.add(new Ponto(origem.getX() + i * deltaX, origem.getY() + i * deltaY));
    }
    return trajetória;
  }
}