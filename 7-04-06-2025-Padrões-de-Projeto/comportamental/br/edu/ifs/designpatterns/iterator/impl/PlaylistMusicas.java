package br.edu.ifs.designpatterns.iterator.impl;

import br.edu.ifs.designpatterns.iterator.Agregador;
import br.edu.ifs.designpatterns.iterator.Iterador;
import java.util.ArrayList;
import java.util.List;

public class PlaylistMusicas implements Agregador<Musica> {
  private List<Musica> musicas = new ArrayList<>();

  public void adicionar(Musica musica) {
    musicas.add(musica);
  }

  @Override
  public Iterador<Musica> criarIterador() {
    return new IteradorMusicas(this.musicas);
  }
}