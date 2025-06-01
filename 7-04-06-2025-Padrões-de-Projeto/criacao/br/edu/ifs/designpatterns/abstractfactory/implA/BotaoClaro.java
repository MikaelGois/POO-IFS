package br.edu.ifs.designpatterns.abstractfactory.implA;

import br.edu.ifs.designpatterns.abstractfactory.Botao;

public class BotaoClaro implements Botao {
	String cor;
	
	public BotaoClaro(String cor) {
		this.cor = cor;
	}

	@Override
	public String obterCor() {
		return this.cor;
	}
	
}