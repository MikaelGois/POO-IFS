package br.edu.ifs.designpatterns.adapter;

import java.util.Map;

public interface Repositorio {
  Map<String, String> obterAlunos();
}