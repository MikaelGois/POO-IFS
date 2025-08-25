package br.edu.ifs.designpatterns.prototype.impl;

import br.edu.ifs.designpatterns.prototype.Robo;
import java.util.Objects;

public class RoboTanque implements Robo {

  private int forca;
  private int velocidade;
  private String armamento;

  public RoboTanque(int forca, int velocidade, String armamento) {
    this.forca = forca;
    this.velocidade = velocidade;
    this.armamento = armamento;
  }

  @Override
  public Robo clonar() {
    return new RoboTanque(this.forca, this.velocidade, this.armamento);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    RoboTanque that = (RoboTanque) obj;
    return forca == that.forca &&
        velocidade == that.velocidade &&
        Objects.equals(armamento, that.armamento);
  }

  @Override
  public int hashCode() {
    return Objects.hash(forca, velocidade, armamento);
  }
}