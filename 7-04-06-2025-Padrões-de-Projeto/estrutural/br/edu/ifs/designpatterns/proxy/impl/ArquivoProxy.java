package br.edu.ifs.designpatterns.proxy.impl;

import br.edu.ifs.designpatterns.proxy.Arquivo;

public class ArquivoProxy implements Arquivo {
  private ArquivoReal arquivo;
  private final String nome;
  private final Permissao permissao;

  public ArquivoProxy(String nome, Permissao permissao) {
    this.nome = nome;
    this.permissao = permissao;
  }

  private void inicializarSeNecessario() {
    if (arquivo == null) {
      arquivo = new ArquivoReal(nome);
    }
  }

  @Override
  public void criar() {
    if (!permissao.podeEscrever()) {
      throw new IllegalStateException("Acesso negado!");
    }
    inicializarSeNecessario();
    arquivo.criar();
  }

  @Override
  public String recuperar() {
    if (!permissao.podeLer()) {
      throw new IllegalStateException("Acesso negado!");
    }
    inicializarSeNecessario();
    return arquivo.recuperar();
  }

  @Override
  public void escrever(String texto) {
    if (!permissao.podeEscrever()) {
      throw new IllegalStateException("Acesso negado!");
    }
    inicializarSeNecessario();
    arquivo.escrever(texto);
  }

  @Override
  public void remover() {
    if (!permissao.podeEscrever()) {
      throw new IllegalStateException("Acesso negado!");
    }
    inicializarSeNecessario();
    arquivo.remover();
  }
}