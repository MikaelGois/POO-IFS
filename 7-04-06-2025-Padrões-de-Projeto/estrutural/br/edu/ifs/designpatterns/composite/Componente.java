package br.edu.ifs.designpatterns.composite;

public abstract class Componente {
  protected String nome;

  public Componente(String nome) {
    this.nome = nome;
  }

  public abstract double calcularPreco();
}