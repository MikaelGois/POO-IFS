package br.edu.ifs.designpatterns.abstractfactory.implB;

import br.edu.ifs.designpatterns.abstractfactory.Botao;
import br.edu.ifs.designpatterns.abstractfactory.FabricaInterfaceGrafica;
import br.edu.ifs.designpatterns.abstractfactory.Janela;
import br.edu.ifs.designpatterns.abstractfactory.Menu;

public class TemaEscuro implements FabricaInterfaceGrafica {

	@Override
	public Botao criarBotao() {
		Botao botao = new BotaoEscuro("#4F4F4F");
		return botao;
	}

	@Override
	public Janela criarJanela() {
		Janela janela = new JanelaEscura("#4F4F4F");
		return janela;
	}

	@Override
	public Menu criarMenu() {
		Menu menu = new MenuEscuro("#4F4F4F");
		return menu;
	}

}
