package br.edu.ifs.designpatterns.abstractfactory.implB;

import br.edu.ifs.designpatterns.abstractfactory.Menu;

public class MenuEscuro implements Menu {
	String cor;
	
	public MenuEscuro(String cor) {
		this.cor = cor;
	}

	@Override
	public String obterCor() {
		return this.cor;
	}
	
}