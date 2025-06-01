package br.edu.ifs.designpatterns.abstractfactory.implA;

import br.edu.ifs.designpatterns.abstractfactory.Menu;

public class MenuClaro implements Menu {
	String cor;
	
	public MenuClaro(String cor) {
		this.cor = cor;
	}

	@Override
	public String obterCor() {
		return this.cor;
	}
	
}