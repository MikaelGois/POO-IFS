package br.edu.ifs.designpatterns.factorymethod.impl;

import br.edu.ifs.designpatterns.factorymethod.Personagem;

public class Orc extends Personagem {
    @Override
    public String atacar() { return "Ataque: garras"; }

    @Override
    public String defender() { return "Defesa: absorver"; }

    @Override
    public String usarMagia() { return "Magia: espirito maligno"; }
}