package br.edu.ifs.designpatterns.factorymethod;

import br.edu.ifs.designpatterns.factorymethod.impl.Anao;
import br.edu.ifs.designpatterns.factorymethod.impl.Elfo;
import br.edu.ifs.designpatterns.factorymethod.impl.Humano;
import br.edu.ifs.designpatterns.factorymethod.impl.Orc;

public class FabricaPersonagens {
	public static Personagem criarPersonagem(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "orc" -> new Orc();
            case "anão" -> new Anao();
            case "elfo" -> new Elfo();
            case "humano" -> new Humano();
            default -> throw new IllegalStateException("Personagem não encontrado!");
        };
    }
}
