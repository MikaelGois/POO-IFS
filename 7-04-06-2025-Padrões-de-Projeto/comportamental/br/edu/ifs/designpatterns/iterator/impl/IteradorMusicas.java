package br.edu.ifs.designpatterns.iterator.impl;

import br.edu.ifs.designpatterns.iterator.Iterador;
import java.util.List;

public class IteradorMusicas implements Iterador<Musica> {
  private List<Musica> playlist;
  private int posicao = 0;

  public IteradorMusicas(List<Musica> playlist) {
    this.playlist = playlist;
  }

  @Override
  public Musica proximo() {
    return playlist.get(posicao++);
  }

  @Override
  public boolean temProximo() {
    return posicao < playlist.size();
  }
}