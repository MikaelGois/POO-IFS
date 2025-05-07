/*
 * Classe de teste que demonstra o fluxo das operações de empréstimo, renovação e devolução de livros,
 * bem como o tratamento das diferentes exceções que podem ocorrer.
 */
package src;

import java.time.LocalDate;

import biblioteca.Aluno;
import biblioteca.Livro;
import exception.EmprestimosExcedidoException;
import exception.LivroInvalidoException;
import exception.LivroJaEmprestadoException;
import exception.LivroNaoEmPosseException;

public class TesteException {

	/*
	 * Ponto de entrada da aplicação. Cria instâncias de aluno e livros e realiza
	 * várias operações para ilustrar o tratamento de exceções.
	 */
	public static void main(String[] args) {

		// Criação de um aluno com matrícula e nome
		Aluno maria = new Aluno("2023001", "Maria");

		// Criação de livros com ISBN, título e data de publicação
		Livro dom = new Livro("978-85-359-0272-1", "Dom Casmurro", LocalDate.of(1899, 1, 1));
		Livro sertao = new Livro("978-85-01-08890-7", "Grande Sertão: Veredas", LocalDate.of(1956, 1, 1));
		Livro tempo = new Livro("978-85-358-0504-0", "O Tempo e o Vento", LocalDate.of(1949, 1, 1));
		Livro vidas = new Livro("978-85-209-1886-5", "Vidas Secas", LocalDate.of(1938, 1, 1));

		// Exemplo de operações inválidas: livro nulo gera LivroInvalidoException
		processarEmprestimo(maria, null);
		processarRenovacao(maria, null);
		processarDevolucao(maria, null);

		// Empréstimos válidos: Maria toma emprestado 'Vidas Secas', 'Grande Sertão' e
		// 'Dom Casmurro'
		processarEmprestimo(maria, vidas);
		processarEmprestimo(maria, sertao);
		processarEmprestimo(maria, dom);

		// Excesso de empréstimos: quarto livro gera EmprestimosExcedidoException
		processarEmprestimo(maria, tempo);

		// Devolução válida: devolve 'Vidas Secas'
		processarDevolucao(maria, vidas);

		// Tentativa de reemprestar livro já emprestado: gera LivroJaEmprestadoException
		processarEmprestimo(maria, dom);

		// Renovação válida do livro 'Dom Casmurro'
		processarRenovacao(maria, dom);

		// Renovação de livro que não está em sua posse: gera LivroNaoEmPosseException
		processarRenovacao(maria, vidas);

		// Devolução de livro que não está em sua posse: gera LivroNaoEmPosseException
		processarDevolucao(maria, vidas);
	}

	/**
	 * Tenta realizar o empréstimo de um livro para o aluno e trata as possíveis
	 * exceções.
	 * 
	 * @param aluno instância do aluno que solicita o empréstimo
	 * @param livro instância do livro a ser emprestado (pode ser null)
	 */
	public static void processarEmprestimo(Aluno aluno, Livro livro) {

		System.out.printf("\n==================================================\n");
		System.out.printf("Processando empréstimo...\n- %s\n- %s\n", aluno, livro);

		try {
			// Chama o método de empréstimo da classe Aluno
			aluno.emprestar(livro);

		} catch (EmprestimosExcedidoException e) {
			// O aluno já atingiu o limite de livros emprestados
			System.err.printf("Erro: %s\n", e.getMessage());
			System.err.printf("Tente devolver um dos livros emprestados!\n");

		} catch (LivroJaEmprestadoException e) {
			// O livro já está com aluno
			System.err.printf("Erro: %s\n", e.getMessage());
			System.err.printf("Tente renovar o livro!\n");

		} catch (LivroInvalidoException e) {
			// Parâmetro inválido (ex: livro nulo)
			System.err.printf("Erro crítico: %s\n", e.getMessage());

		} finally {
			// Bloco que sempre é executado, independente de exceção
			System.out.printf("Operação finalizada.\n");
		}
	}

	/**
	 * Tenta renovar o empréstimo de um livro e trata exceções.
	 * 
	 * @param aluno instância do aluno que solicita a renovação
	 * @param livro instância do livro a ser renovado (pode ser null)
	 */
	public static void processarRenovacao(Aluno aluno, Livro livro) {

		System.out.printf("\n==================================================\n");
		System.out.printf("Processando renovacao...\n- %s\n- %s\n", aluno, livro);

		try {
			// Chama o método de renovação da classe Aluno
			aluno.renovar(livro);

		} catch (LivroNaoEmPosseException e) {
			// O aluno tenta renovar um livro que não está com ele
			System.err.printf("Erro: %s\n", e.getMessage());
			System.err.printf("Tente emprestar o livro solicitado antes de renová-lo!\n");

		} catch (LivroInvalidoException e) {
			// Parâmetro inválido (ex: livro nulo)
			System.err.printf("Erro crítico: %s\n", e.getMessage());

		} finally {
			// Bloco que sempre é executado, independente de exceção
			System.out.printf("Operação finalizada.\n");
		}
	}

	/**
	 * Tenta devolver um livro emprestado e trata exceções.
	 * 
	 * @param aluno instância do aluno que faz a devolução
	 * @param livro instância do livro a ser devolvido (pode ser null)
	 */
	public static void processarDevolucao(Aluno aluno, Livro livro) {

		System.out.printf("\n==================================================\n");
		System.out.printf("Processando devolucao...\n- %s\n- %s\n", aluno, livro);

		try {
			// Chama o método de devolução da classe Aluno
			aluno.devolver(livro);

		} catch (LivroNaoEmPosseException e) {
			// O aluno tenta devolver um livro que não está com ele
			System.err.printf("Erro: %s\n", e.getMessage());
			System.err.printf("Tente emprestar o livro solicitado antes de devolvê-lo!\n");

		} catch (LivroInvalidoException e) {
			// Parâmetro inválido (ex: livro nulo)
			System.err.printf("Erro crítico: %s\n", e.getMessage());

		} finally {
			// Bloco que sempre é executado, independente de exceção
			System.out.printf("Operação finalizada.\n");
		}
	}
}