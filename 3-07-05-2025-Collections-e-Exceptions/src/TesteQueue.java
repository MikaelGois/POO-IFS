// -----------------------------------------------------------
// TesteQueue.java: Demonstra uso de Queue com LinkedList e PriorityQueue

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import biblioteca.Aluno;

public class TesteQueue {

    public static void main(String[] args) {

    	// =============================================================================

        // === Parte 1: Fila FIFO com LinkedList ===
    	
		System.out.println("Parte 1: Fila FIFO com LinkedList\n");

		// Definição de uma fila baseado em um LinkedList
		Queue<Aluno> queue = new LinkedList<>();

		/*
		 * Adição dos alunos na fila
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001
		 */
		queue.add(new Aluno("2023001", "Maria"));
		queue.add(new Aluno("2023009", "João"));
		queue.add(new Aluno("2023001", "Miguel"));

		// Impressão da fila de alunos
		System.out.printf("LinkedList após primeira inserção\n");
		imprimirAlunos(new LinkedList<>(queue));

		/*
		 * Adição dos alunos na fila
		 * - Joaquim	2023005
		 * - Maria S.	2023001
		 * - Gessica	2023007
		 */
		queue.add(new Aluno("2023005", "Joaquim"));
		queue.add(new Aluno("2023001", "Maria S."));
		queue.add(new Aluno("2023007", "Gessica"));

		// Impressão da fila de alunos
		System.out.printf("\nLinkedList após segunda inserção\n");
		imprimirAlunos(new LinkedList<>(queue));

		// =============================================================================

        // === Parte 2: Fila de prioridades com PriorityQueue ===

		System.out.println("\nParte 2: Fila de prioridades com PriorityQueue\n");

		// PriorityQueue padrão: ordena por matrícula
		queue = new PriorityQueue<>();

		/*
		 * Adição dos alunos na fila
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001
		 */
		queue.add(new Aluno("2023001", "Maria"));
		queue.add(new Aluno("2023009", "João"));
		queue.add(new Aluno("2023001", "Miguel"));

		// Impressão da fila de alunos
		System.out.printf("PriorityQueue (matrícula) após primeira inserção\n");
		imprimirAlunos(new PriorityQueue<>(queue));

		/*
		 * Adição dos alunos na fila
		 * - Joaquim	2023005
		 * - Maria S.	2023001
		 * - Gessica	2023007
		 */
		queue.add(new Aluno("2023005", "Joaquim"));
		queue.add(new Aluno("2023001", "Maria S."));
		queue.add(new Aluno("2023007", "Gessica"));

		// Impressão da fila de alunos
		System.out.printf("\nPriorityQueue (matrícula) após segunda inserção\n");
		imprimirAlunos(new PriorityQueue<>(queue));

		// =============================================================================

		// === Parte 3: Fila de prioridades com PriorityQueue com Comparator ===

		System.out.println("\nParte 3: Fila de prioridades com PriorityQueue com Comparator\n");

		// PriorityQueue com comparador por nome
		queue = new PriorityQueue<>(Aluno.comparadorNome());

		/*
		 * Adição dos alunos na fila
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001
		 */
		queue.add(new Aluno("2023001", "Maria"));
		queue.add(new Aluno("2023009", "João"));
		queue.add(new Aluno("2023001", "Miguel"));

		// Impressão da fila de alunos
		System.out.printf("PriorityQueue (nome) após primeira inserção\n");
		imprimirAlunos(new PriorityQueue<>(queue));

		/*
		 * Adição dos alunos na fila
		 * - Joaquim	2023005
		 * - Maria S.	2023001
		 * - Gessica	2023007
		 */
		queue.add(new Aluno("2023005", "Joaquim"));
		queue.add(new Aluno("2023001", "Maria S."));
		queue.add(new Aluno("2023007", "Gessica"));

		// Impressão da fila de alunos
		System.out.printf("\nPriorityQueue (nome) após segunda inserção\n");
		imprimirAlunos(new PriorityQueue<>(queue));
	}

    // Remove e imprime cada elemento da fila até esvaziar (poll retorna nulo se vazia)
    private static void imprimirAlunos(Queue<Aluno> queue) {
        System.out.println("Lista de alunos:");
        int i = 0;
        while (!queue.isEmpty()) {
            System.out.printf("%d - %s\n", ++i, queue.poll());
        }
    }
}

