package br.edu.ifs.designpatterns.bridge.obj;

public class Ponto {
  private double x, y;

  public Ponto(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public String toString() {
    return "{" + x + ", " + y + "}";
  }
}