package br.edu.ifs.designpatterns.iterator.impl;

import br.edu.ifs.designpatterns.iterator.Agregador;
import br.edu.ifs.designpatterns.iterator.Iterador;
import java.util.*;
import java.util.stream.Collectors;

public class PlaylistArtistas implements Agregador<Musica> {
  private Map<String, List<Musica>> musicasPorArtista = new TreeMap<>();

  public void adicionar(Musica musica) {
    musicasPorArtista.computeIfAbsent(musica.getArtista(), k -> new ArrayList<>()).add(musica);
  }

  @Override
  public Iterador<Musica> criarIterador() {
    List<Musica> listaOrdenada = musicasPorArtista.values().stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
    return new IteradorMusicas(listaOrdenada);
  }
}