package br.edu.ifs.designpatterns.abstractfactory;

public interface FabricaInterfaceGrafica {
	public Botao criarBotao();
	public Janela criarJanela();
	public Menu criarMenu();
}
