package br.edu.ifs.designpatterns.bridge.obj;

import br.edu.ifs.designpatterns.bridge.ObjetoGrafico;
import br.edu.ifs.designpatterns.bridge.Plataforma;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class HollowKnight extends ObjetoGrafico {
  private final int velocidade = 15;
  private final int impulso = 15;

  public HollowKnight(Plataforma plataforma) {
    super(plataforma);
  }

  public int getVelocidade() {
    return this.velocidade;
  }

  public List<Ponto> andar(boolean paraFrente) {
    return mover(paraFrente ? velocidade : -velocidade, 0);
  }

  public List<Ponto> correr(boolean paraFrente) {
    return mover(paraFrente ? velocidade * 2.0 : -velocidade * 2.0, 0);
  }

  public List<Ponto> pular() {
    Ponto pInicial = new Ponto(this.posicao.getX(), this.posicao.getY());
    List<Ponto> subida = mover(0, impulso);
    List<Ponto> descida = mover(0, -impulso);
    this.posicao = pInicial; // Retorna à posição original após o pulo
    return Stream.concat(subida.stream(), descida.stream()).collect(Collectors.toList());
  }
}