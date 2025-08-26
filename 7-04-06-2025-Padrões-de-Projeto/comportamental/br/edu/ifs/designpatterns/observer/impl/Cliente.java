package br.edu.ifs.designpatterns.observer.impl;

import br.edu.ifs.designpatterns.observer.Observador;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Observador {
  private String nome;
  private List<String> pedidos = new ArrayList<>();

  public Cliente(String nome) {
    this.nome = nome;
  }

  public void comprar(Produto produto) {
    if (!produto.estaDisponivel()) {
      throw new IllegalStateException("Produto indisponível");
    }
    produto.remover(1);
    pedidos.add(produto.getNome());
  }

  @Override
  public void atualizar(Produto produto) {
    if (produto.estaDisponivel()) {
      comprar(produto);
    }
  }

  public List<String> getPedidos() {
    return pedidos;
  }
}