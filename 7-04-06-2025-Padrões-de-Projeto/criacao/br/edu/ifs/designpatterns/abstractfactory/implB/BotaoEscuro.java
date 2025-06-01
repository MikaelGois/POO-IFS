package br.edu.ifs.designpatterns.abstractfactory.implB;

import br.edu.ifs.designpatterns.abstractfactory.Botao;

public class BotaoEscuro implements Botao {
	String cor;
	
	public BotaoEscuro(String cor) {
		this.cor = cor;
	}

	@Override
	public String obterCor() {
		return this.cor;
	}
	
}
