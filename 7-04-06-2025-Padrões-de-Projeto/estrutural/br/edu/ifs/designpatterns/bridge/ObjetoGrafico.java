package br.edu.ifs.designpatterns.bridge;

import br.edu.ifs.designpatterns.bridge.obj.Ponto;
import java.util.List;

public abstract class ObjetoGrafico {
  protected Ponto posicao;
  protected Plataforma plataforma;

  public ObjetoGrafico(Plataforma plataforma) {
    this.plataforma = plataforma;
    this.posicao = new Ponto(0, 0);
  }

  public Ponto getPosicao() {
    return this.posicao;
  }

  protected List<Ponto> mover(double deltaX, double deltaY) {
    Ponto destino = new Ponto(posicao.getX() + deltaX, posicao.getY() + deltaY);
    List<Ponto> trajetoria = plataforma.animar(this.posicao, destino);
    this.posicao = destino;
    return trajetoria;
  }
}