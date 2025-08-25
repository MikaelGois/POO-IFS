package br.edu.ifs.designpatterns.adapter.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifs.designpatterns.adapter.Repositorio;
import br.edu.ifs.designpatterns.adapter.RepositorioLista;

public class AdaptadorLista implements Repositorio {

  private RepositorioLista repositorio;

  public AdaptadorLista(RepositorioLista repositorio) {
    this.repositorio = repositorio;
  }

  @Override
  public Map<String, String> obterAlunos() {
    List<String> dados = repositorio.recuperarDados();
    Map<String, String> alunos = new HashMap<>();
    for (String linha : dados) {
      String[] partes = linha.split("\\s+", 2);
      if (partes.length == 2) {
        alunos.put(partes[0], partes[1]);
      }
    }
    return alunos;
  }
}