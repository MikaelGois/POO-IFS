// -----------------------------------------------------------
// TesteSet.java: Demonstra uso de Set (HashSet, LinkedHashSet, TreeSet) e tratamento de duplicatas
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import biblioteca.Aluno;

public class TesteSet {

    public static void main(String[] args) {

    	// =============================================================================

        // === Parte 1: HashSet: não permite duplicatas, sem ordem garantida ===
    	System.out.println("Parte 1: HashSet: não permite duplicatas, sem ordem garantida\n");
    	
		// Definição de um conjunto baseado em um HashSet
		Set<Aluno> set = new HashSet<>();

		/*
		 * Adição dos alunos no conjunto
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001 (duplicado)
		 */
		set.add(new Aluno("2023001", "Maria"));
		set.add(new Aluno("2023009", "João"));
		set.add(new Aluno("2023001", "Miguel"));

		// Impressão do conjunto de alunos
		System.out.printf("HashSet após primeira inserção\n");
		imprimirAlunos(set);

		/*
		 * Adição dos alunos no conjunto
		 * - Joaquim	2023005
		 * - Maria S.	2023001 (duplicado)
		 * - Gessica	2023007
		 */
		set.add(new Aluno("2023005", "Joaquim"));
		set.add(new Aluno("2023001", "Maria S."));
		set.add(new Aluno("2023007", "Gessica"));

		// Impressão do conjunto de alunos
		System.out.printf("\nHashSet após segunda inserção\n");
		imprimirAlunos(set);

		// =============================================================================

        // === Parte 2: LinkedHashSet: mantém ordem de inserção, sem duplicatas ===
    	System.out.println("\nParte 2: LinkedHashSet: mantém ordem de inserção, sem duplicatas\n");

		// Definição de um conjunto baseado em um LinkedHashSet
		set = new LinkedHashSet<>();

		/*
		 * Adição dos alunos no conjunto
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001 (duplicado)
		 */
		set.add(new Aluno("2023001", "Maria"));
		set.add(new Aluno("2023009", "João"));
		set.add(new Aluno("2023001", "Miguel"));

		// Impressão do conjunto de alunos
		System.out.printf("LinkedHashSet após primeira inserção\n");
		imprimirAlunos(set);

		/*
		 * Adição dos alunos no conjunto
		 * - Joaquim	2023005
		 * - Maria S.	2023001 (duplicado)
		 * - Gessica	2023007
		 */
		set.add(new Aluno("2023005", "Joaquim"));
		set.add(new Aluno("2023001", "Maria S."));
		set.add(new Aluno("2023007", "Gessica"));

		// Impressão do conjunto de alunos
		System.out.printf("\nLinkedHashSet após segunda inserção\n");
		imprimirAlunos(set);

		// =============================================================================

        // === Parte 3: TreeSet padrão: ordena por matrícula (compareTo em Aluno) ===
    	System.out.println("\nParte 3: TreeSet padrão: ordena por matrícula (compareTo em Aluno)\n");

		// Definição de um conjunto baseado em um TreeSet
		set = new TreeSet<>();

		/*
		 * Adição dos alunos no conjunto
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001 (duplicado)
		 */
		set.add(new Aluno("2023001", "Maria"));
		set.add(new Aluno("2023009", "João"));
		set.add(new Aluno("2023001", "Miguel"));

		// Impressão do conjunto de alunos
		System.out.printf("TreeSet (matrícula) após primeira inserção\n");
		imprimirAlunos(set);

		/*
		 * Adição dos alunos no conjunto
		 * - Joaquim	2023005
		 * - Maria S.	2023001 (duplicado)
		 * - Gessica	2023007
		 */
		set.add(new Aluno("2023005", "Joaquim"));
		set.add(new Aluno("2023001", "Maria S."));
		set.add(new Aluno("2023007", "Gessica"));

		// Impressão do conjunto de alunos
		System.out.printf("\nTreeSet (matrícula) após segunda inserção\n");
		imprimirAlunos(set);

		// =============================================================================

        // === Parte 4: reeSet com comparador por nome: ordenação alfabética de nomes ===
    	System.out.println("\nParte 4: TreeSet com comparador por nome: ordenação alfabética de nomes\n");

		// Definição de um conjunto baseado em um TreeSet
		set = new TreeSet<>(Aluno.comparadorNome());

		/*
		 * Adição dos alunos no conjunto
		 * - Maria		2023001
		 * - João		2023009
		 * - Miguel		2023001 (duplicado)
		 */
		set.add(new Aluno("2023001", "Maria"));
		set.add(new Aluno("2023009", "João"));
		set.add(new Aluno("2023001", "Miguel"));

		// Impressão do conjunto de alunos
		System.out.printf("TreeSet (nome) após primeira inserção\n");
		imprimirAlunos(set);

		/*
		 * Adição dos alunos no conjunto
		 * - Joaquim	2023005
		 * - Maria S.	2023001 (duplicado)
		 * - Gessica	2023007
		 */
		set.add(new Aluno("2023005", "Joaquim"));
		set.add(new Aluno("2023001", "Maria S."));
		set.add(new Aluno("2023007", "Gessica"));

		// Impressão do conjunto de alunos
		System.out.printf("\nTreeSet (nome) após segunda inserção\n");
		imprimirAlunos(set);
	}

    // Imprime todos os alunos do conjunto
	private static void imprimirAlunos(Set<Aluno> set) {
		System.out.printf("Lista de alunos\n");
		int indice = 0;
		for (Aluno aluno : set) {
			System.out.printf("%d - %s\n", ++indice, aluno);
		}
	}
}