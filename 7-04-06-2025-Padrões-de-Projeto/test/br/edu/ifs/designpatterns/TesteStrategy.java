/**
 *
 */
package br.edu.ifs.designpatterns;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.ifs.designpatterns.strategy.EstrategiaDesconto;
import br.edu.ifs.designpatterns.strategy.Ingresso;
import br.edu.ifs.designpatterns.strategy.impl.EstrategiaDiaPais;
import br.edu.ifs.designpatterns.strategy.impl.EstrategiaEstudante;
import br.edu.ifs.designpatterns.strategy.impl.EstrategiaIntegral;

/**
 * 
 */
class TesteStrategy {

	/**
	 * 
	 */
	@Test
	@DisplayName("Teste Strategy")
	void test() {

		/*
		 * Definição do valor integral do ingresso
		 */
		double valor = 30.0;

		/*
		 * Aplicação da estratégia de preço integral, sem desconto.
		 */
		EstrategiaDesconto integral = new EstrategiaIntegral();
		Ingresso ingresso = new Ingresso(valor, integral);
		assertEquals(ingresso.calcularValor(), 30.0);

		/*
		 * Aplicação da estratégia de preço estudantil com 50% de desconto.
		 */
		EstrategiaDesconto estudante = new EstrategiaEstudante();
		ingresso = new Ingresso(valor, estudante);
		assertEquals(ingresso.calcularValor(), 15.0);

		/*
		 * Aplicação da estratégia de preço especial da semana do dia dos pais com 60%
		 * de desconto.
		 */
		EstrategiaDesconto diaPais = new EstrategiaDiaPais();
		ingresso = new Ingresso(valor, diaPais);
		assertEquals(ingresso.calcularValor(), 12.0);
	}
}