package br.edu.ifs.designpatterns.abstractfactory.implA;

import br.edu.ifs.designpatterns.abstractfactory.Botao;
import br.edu.ifs.designpatterns.abstractfactory.FabricaInterfaceGrafica;
import br.edu.ifs.designpatterns.abstractfactory.Janela;
import br.edu.ifs.designpatterns.abstractfactory.Menu;

public class TemaClaro implements FabricaInterfaceGrafica {

	@Override
	public Botao criarBotao() {
		Botao botao = new BotaoClaro("#FFFAFA");
		return botao;
	}

	@Override
	public Janela criarJanela() {
		Janela janela = new JanelaClara("#FFFAFA");
		return janela;
	}

	@Override
	public Menu criarMenu() {
		Menu menu = new MenuClaro("#FFFAFA");
		return menu;
	}

}
