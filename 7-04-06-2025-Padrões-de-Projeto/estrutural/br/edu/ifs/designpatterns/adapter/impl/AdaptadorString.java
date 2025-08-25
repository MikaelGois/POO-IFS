package br.edu.ifs.designpatterns.adapter.impl;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifs.designpatterns.adapter.Repositorio;
import br.edu.ifs.designpatterns.adapter.RepositorioString;

public class AdaptadorString implements Repositorio {

  private RepositorioString repositorio;

  public AdaptadorString(RepositorioString repositorio) {
    this.repositorio = repositorio;
  }

  @Override
  public Map<String, String> obterAlunos() {
    String dados = repositorio.recuperarDados();
    Map<String, String> alunos = new HashMap<>();
    String[] linhas = dados.split(";");
    for (String linha : linhas) {
      String[] partes = linha.split("\\s+", 2);
      if (partes.length == 2) {
        alunos.put(partes[0], partes[1]);
      }
    }
    return alunos;
  }
}