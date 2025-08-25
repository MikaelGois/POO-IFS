package br.edu.ifs.designpatterns.proxy;

public interface Arquivo {
  void criar();

  String recuperar();

  void escrever(String texto);

  void remover();
}