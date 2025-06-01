package br.edu.ifs.designpatterns.factorymethod.impl;

import br.edu.ifs.designpatterns.factorymethod.Personagem;

public class Humano extends Personagem {
    @Override
    public String atacar() { return "Ataque: espada"; }

    @Override
    public String defender() { return "Defesa: rolar"; }

    @Override
    public String usarMagia() { return "Magia: raio de luz"; }
}
