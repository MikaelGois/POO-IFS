package br.edu.ifs.designpatterns.factorymethod.impl;

import br.edu.ifs.designpatterns.factorymethod.Personagem;

public class Anao extends Personagem {
    @Override
    public String atacar() { return "Ataque: machado"; }

    @Override
    public String defender() { return "Defesa: escudo"; }

    @Override
    public String usarMagia() { return "Magia: resistência"; }
}