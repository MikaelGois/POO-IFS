// TesteList.java: Demonstra operações básicas e comparações de desempenho entre ArrayList e LinkedList
package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import biblioteca.Aluno;

public class TesteList {

    public static void main(String[] args) {

		// =============================================================================

        // === Parte 1: Operações em ArrayList ===
    	System.out.println("Parte 1: Operações em ArrayList\n");


        // Cria uma lista dinâmica baseada em array (cresce dinamicamente)
        List<src.Aluno> list = new ArrayList<>();

		/*
		 * Adiciona 3 alunos ao fim da lista (operação O(1) amortizada)
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001
		 */
        list.add(new Aluno("2023001", "Maria"));
        list.add(new Aluno("2023009", "João"));
        list.add(new Aluno("2023001", "Miguel"));

		// Impressão da lista de alunos
		System.out.printf("ArrayList após inserção no fim (default)\n");
		imprimirAlunos(list);

		/*
		 * Adiciona 3 alunos no início da lista (O(n) pois desloca elementos)
		 * - Joaquim	2023005
		 * - Maria S.	2023001
		 * - Gessica	2023007
		 */
		list.add(0, new Aluno("2023005", "Joaquim"));
		list.add(0, new Aluno("2023001", "Maria S."));
		list.add(0, new Aluno("2023007", "Gessica"));

		// Impressão da lista de alunos
		System.out.printf("\nArrayList após inserção no início\n");
		imprimirAlunos(list);

		/*
		 * Adiciona 3 alunos em posição intermediária (índice 4)
		 * - Rafael		2023011
		 * - Denis		2023012
		 * - Viviane	2023013
		 */
		list.add(4, new Aluno("2023011", "Rafael"));
		list.add(4, new Aluno("2023012", "Denis"));
		list.add(4, new Aluno("2023013", "Viviane"));

		// Impressão da lista de alunos
		System.out.printf("\nArrayList após inserção no meio\n");
		imprimirAlunos(list);

		// =============================================================================

		// === Parte 2: Operações em LinkedList ===
    	
		System.out.println("\nParte 2: Operações em LinkedList\n");

        // Substitui implementação por LinkedList (lista duplamente encadeada)
		list = new LinkedList<>();

		/*
		 * Adiciona no fim (O(1)), sem necessidade de deslocamento
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001
		 */
		list.add(new Aluno("2023001", "Maria"));
		list.add(new Aluno("2023009", "João"));
		list.add(new Aluno("2023001", "Miguel"));

		// Impressão da lista de alunos
		System.out.printf("LinkedList após inserção no fim (default)\n");
		imprimirAlunos(list);

		/*
		 * Adiciona no início (O(1)), sem necessidade de deslocamento
		 * - Joaquim	2023005
		 * - Maria S.	2023001
		 * - Gessica	2023007
		 */
		list.add(0, new Aluno("2023005", "Joaquim"));
		list.add(0, new Aluno("2023001", "Maria S."));
		list.add(0, new Aluno("2023007", "Gessica"));

		// Impressão da lista de alunos
		System.out.printf("\nLinkedList após inserção no início\n");
		imprimirAlunos(list);

		/*
		 * Adiciona 3 alunos em posição intermediária (O(n) busca + O(1) inserção)
		 * - Rafael		2023011
		 * - Denis		2023012
		 * - Viviane	2023013
		 */
		list.add(4, new Aluno("2023011", "Rafael"));
		list.add(4, new Aluno("2023012", "Denis"));
		list.add(4, new Aluno("2023013", "Viviane"));

		// Impressão da lista de alunos
		System.out.printf("\nLinkedList após inserção no meio\n");
		imprimirAlunos(list);

		// =============================================================================

		// === Parte 3: Benchmark simples ===
		
		System.out.println("\nParte 3: Benchmark simples\n");

        final int QUANTIDADE = 50_000;

        // Mede tempo de inserção no início e acesso aleatório em ArrayList
        list = new ArrayList<>();
        long tempoArrayListInsercao = medirTempoInsercaoNoInicio(list, QUANTIDADE);
        long tempoArrayListAcesso = medirTempoAcessoAleatorio(list, QUANTIDADE);

        // Mede tempo de inserção no início e acesso aleatório em LinkedList
        list = new LinkedList<>();
        long tempoLinkedListInsercao = medirTempoInsercaoNoInicio(list, QUANTIDADE);
        long tempoLinkedListAcesso = medirTempoAcessoAleatorio(list, QUANTIDADE);

        System.out.printf("=== Comparação %d operações ===\n", QUANTIDADE);
        System.out.printf("ArrayList - Inserção no início: %d ms\n", tempoArrayListInsercao);
        System.out.printf("ArrayList - Acesso aleatório: %d ms\n", tempoArrayListAcesso);
        System.out.printf("LinkedList - Inserção no início: %d ms\n", tempoLinkedListInsercao);
        System.out.printf("LinkedList - Acesso aleatório: %d ms\n", tempoLinkedListAcesso);
    }

    // Imprime todos os alunos na lista, mantendo ordem de iteração
    private static void imprimirAlunos(List<Aluno> list) {
        System.out.println("Lista de alunos:");
        int indice = 0;
        for (Aluno aluno : list) {
            System.out.printf("%d - %s\n", ++indice, aluno);
        }
    }

    // Insere QUANTIDADE de alunos no início da lista para medir performance
    private static long medirTempoInsercaoNoInicio(List<Aluno> list, int quantidade) {
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < quantidade; i++) {
            list.addFirst(new Aluno("mat" + i, "aluno" + i)); // ArrayList O(n), LinkedList O(1)
        }
        return System.currentTimeMillis() - inicio;
    }

    // Acessa posições aleatórias na lista para medir tempo de get()
    private static long medirTempoAcessoAleatorio(List<Aluno> list, int quantidade) {
        Random r = new Random(123456);
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < quantidade; i++) {
            list.get(r.nextInt(quantidade)); // ArrayList O(1), LinkedList O(n)
        }
        return System.currentTimeMillis() - inicio;
    }
}