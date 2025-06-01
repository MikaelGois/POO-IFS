package br.edu.ifs.designpatterns.abstractfactory.implB;

import br.edu.ifs.designpatterns.abstractfactory.Janela;

public class JanelaEscura implements Janela {
	String cor;
	
	public JanelaEscura(String cor) {
		this.cor = cor;
	}

	@Override
	public String obterCor() {
		return this.cor;
	}
	
}