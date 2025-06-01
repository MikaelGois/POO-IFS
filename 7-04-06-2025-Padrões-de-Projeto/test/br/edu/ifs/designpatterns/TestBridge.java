/**
 * 
 */
package br.edu.ifs.designpatterns;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.ifs.designpatterns.bridge.ObjetoGrafico;
import br.edu.ifs.designpatterns.bridge.Plataforma;
import br.edu.ifs.designpatterns.bridge.obj.HollowKnight;
import br.edu.ifs.designpatterns.bridge.obj.Ponto;
import br.edu.ifs.designpatterns.bridge.plat.NintendoSwitch;
import br.edu.ifs.designpatterns.bridge.plat.PC;
import br.edu.ifs.designpatterns.bridge.plat.Playstation5;
import br.edu.ifs.designpatterns.bridge.plat.XBoxSeriesX;

/**
 * 
 */
class TestBridge {

	/**
	 */
	@Test
	@DisplayName("Teste Bridge")
	void test() {

		/*
		 * Instanciação das plataformas.
		 * 
		 * Cada plataforma possui uma taxa de frames por segundo (FPS). Para simplificar
		 * os testes foram definidos os seguintes valores:
		 * 
		 * PC: 5 FPS Xbox: 4 FPS Playstation: 3 FPS Nintendo Switch: 2 FPS
		 */
		PC pc = new PC();
		XBoxSeriesX xbox = new XBoxSeriesX();
		Playstation5 playstation = new Playstation5();
		NintendoSwitch nintendo = new NintendoSwitch();

		/*
		 * Todas as plataformas devem implementar a interface Plataforma
		 */
		assertInstanceOf(Plataforma.class, pc);
		assertInstanceOf(Plataforma.class, xbox);
		assertInstanceOf(Plataforma.class, nintendo);
		assertInstanceOf(Plataforma.class, playstation);

		/*
		 * Testando a configuração do FPS de cada plataforma
		 */
		assertEquals(pc.getFPS(), 5);
		assertEquals(xbox.getFPS(), 4);
		assertEquals(nintendo.getFPS(), 2);
		assertEquals(playstation.getFPS(), 3);

		/*
		 * Instanciando um personagem do jogo Hollow Knight
		 * 
		 * Esse personagem possui os seguintes atributos:
		 * 
		 * Velocidade: 15 posições por segundo;
		 * Impulso do pulo: 15 posições por segundo.
		 * 
		 */
		HollowKnight vesselPC = new HollowKnight(pc);

		/*
		 * Todos os personagens devem estender a classe abstrata ObjetoGrafico
		 */
		assertInstanceOf(ObjetoGrafico.class, vesselPC);

		assertEquals(vesselPC.getVelocidade(), 15);

		/*
		 * Verificando a posição inicial do personagem
		 */
		assertEquals(vesselPC.getPosicao().getX(), 0);
		assertEquals(vesselPC.getPosicao().getY(), 0);

		/*
		 * Verificando a posição após andar para frente
		 */
		vesselPC.andar(true);

		assertEquals(vesselPC.getPosicao().getX(), 15);
		assertEquals(vesselPC.getPosicao().getY(), 0);

		/*
		 * Verificando a posição após andar para trás
		 */
		vesselPC.andar(false);

		assertEquals(vesselPC.getPosicao().getX(), 0);
		assertEquals(vesselPC.getPosicao().getY(), 0);

		/*
		 * Verificando a posição após correr
		 */
		vesselPC.correr(true);

		assertEquals(vesselPC.getPosicao().getX(), 30);
		assertEquals(vesselPC.getPosicao().getY(), 0);

		/*
		 * Verificando a posição após correr para trás
		 */
		vesselPC.correr(false);

		assertEquals(vesselPC.getPosicao().getX(), 0);
		assertEquals(vesselPC.getPosicao().getY(), 0);

		/*
		 * Verificando a posição após pular
		 */
		vesselPC.pular();

		assertEquals(vesselPC.getPosicao().getX(), 0);
		assertEquals(vesselPC.getPosicao().getY(), 0);

		/*
		 * Explicitando as trajetórias que o personagem irá percorrer ao andar a partir
		 * da origem.
		 * 
		 * Obs. Lembrar que cada plataforma possui um FPS diferente, logo as plataformas
		 * com maior taxa de frames por segundo irão apresentar uma quantidade maior de
		 * pontos, tornando a animação mais fluída.
		 */
		String trajetoPC = "[{0.0, 0.0}, {3.0, 0.0}, {6.0, 0.0}, {9.0, 0.0}, {12.0, 0.0}, {15.0, 0.0}]";
		String trajetoXbox = "[{0.0, 0.0}, {3.75, 0.0}, {7.5, 0.0}, {11.25, 0.0}, {15.0, 0.0}]";
		String trajetoPlay5 = "[{0.0, 0.0}, {5.0, 0.0}, {10.0, 0.0}, {15.0, 0.0}]";
		String trajetoNintendo = "[{0.0, 0.0}, {7.5, 0.0}, {15.0, 0.0}]";

		List<Ponto> trajetoria = vesselPC.andar(true);
		assertEquals(trajetoria.toString(), trajetoPC);

		trajetoria = new HollowKnight(xbox).andar(true);
		assertEquals(trajetoria.toString(), trajetoXbox);

		trajetoria = new HollowKnight(playstation).andar(true);
		assertEquals(trajetoria.toString(), trajetoPlay5);

		trajetoria = new HollowKnight(nintendo).andar(true);
		assertEquals(trajetoria.toString(), trajetoNintendo);

		/*
		 * Explicitando as trajetórias que o personagem irá percorrer ao pular a partir
		 * da origem.
		 * 
		 * Obs. Lembrar que cada plataforma possui um FPS diferente, logo as plataformas
		 * com maior taxa de frames por segundo irá apresentar uma quantidade maior de
		 * pontos, tornando a animação mais fluída.
		 */
		trajetoPC = "[{0.0, 0.0}, {0.0, 3.0}, {0.0, 6.0}, {0.0, 9.0}, {0.0, 12.0}, {0.0, 15.0}, {0.0, 15.0}, {0.0, 12.0}, {0.0, 9.0}, {0.0, 6.0}, {0.0, 3.0}, {0.0, 0.0}]";
		trajetoXbox = "[{0.0, 0.0}, {0.0, 3.75}, {0.0, 7.5}, {0.0, 11.25}, {0.0, 15.0}, {0.0, 15.0}, {0.0, 11.25}, {0.0, 7.5}, {0.0, 3.75}, {0.0, 0.0}]";
		trajetoPlay5 = "[{0.0, 0.0}, {0.0, 5.0}, {0.0, 10.0}, {0.0, 15.0}, {0.0, 15.0}, {0.0, 10.0}, {0.0, 5.0}, {0.0, 0.0}]";
		trajetoNintendo = "[{0.0, 0.0}, {0.0, 7.5}, {0.0, 15.0}, {0.0, 15.0}, {0.0, 7.5}, {0.0, 0.0}]";

		trajetoria = new HollowKnight(pc).pular();
		assertEquals(trajetoria.toString(), trajetoPC);

		trajetoria = new HollowKnight(xbox).pular();
		assertEquals(trajetoria.toString(), trajetoXbox);

		trajetoria = new HollowKnight(playstation).pular();
		assertEquals(trajetoria.toString(), trajetoPlay5);

		trajetoria = new HollowKnight(nintendo).pular();
		assertEquals(trajetoria.toString(), trajetoNintendo);
	}
}