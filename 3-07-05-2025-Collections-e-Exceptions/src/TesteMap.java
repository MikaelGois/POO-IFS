// TesteMap.java: Demonstra diferentes implementações de Map e suas características de ordenação
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import biblioteca.Aluno;
import biblioteca.Livro;

public class TesteMap {

    public static void main(String[] args) {

		// =============================================================================

        // Cria objetos Aluno e Livro para simular empréstimos
        Aluno maria = new Aluno("2023001", "Maria");
        Aluno joao  = new Aluno("2023009", "João");
        Aluno joaquim = new Aluno("2023005", "Joaquim");

        Livro dom    = new Livro("978-85-359-0272-1", "Dom Casmurro", LocalDate.of(1899,1,1));
        Livro sertao = new Livro("978-85-01-08890-7", "Grande Sertão: Veredas", LocalDate.of(1956,1,1));
        Livro tempo  = new Livro("978-85-358-0504-0", "O Tempo e o Vento", LocalDate.of(1949,1,1));
        Livro vidas  = new Livro("978-85-209-1886-5", "Vidas Secas", LocalDate.of(1938,1,1));

		// =============================================================================

        // === Parte 1: Operações em HashMap ===

		System.out.println("Parte 1: Operações em HashMap\n");

		/*
		 * HashMap: não garante ordem de chaves
		 * - Chave: livro emprestadp
		 * - Valor: aluno que emprestou o livro
		 */
		Map<Livro, Aluno>map = new HashMap<>();

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro		->	Maria
		 * - O Tempo e o Vento 	->	Maria
		 * - Vidas Secas		->	João
		 */
		map.put(dom, maria);
		map.put(tempo, maria);
		map.put(vidas, joao);

		// Impressão do mapa de empréstimos
		System.out.printf("HashMap após primeira inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro			->	Joaquim (livro emprestado)
		 * - Grande Sertão: Veredas ->	Joaquim
		 */
		map.put(dom, joaquim);
		map.put(sertao, joaquim);

		// Impressão do mapa de empréstimos
		System.out.printf("\nHashMap após segunda inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Devolução dos seguintes empréstimo
		 * - Dom Casmurro
		 * - O Tempo e o Vento
		 */
		map.remove(dom);
		map.remove(tempo);

		// Impressão do mapa de empréstimos
		System.out.printf("\nHashMap após remoção\n");
		imprimirEmprestimo(map);

		// =============================================================================

		// === Parte 2: Operações em LinkedHashMap ===
		System.out.println("\nParte 2: Operações em LinkedHashMap\n");

		/*
		 * LinkedHashMap: mantém ordem de inserção
		 * - Chave: livro emprestadp
		 * - Valor: aluno que emprestou o livro
		 */
		map = new LinkedHashMap<>();

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro		->	Maria
		 * - O Tempo e o Vento 	->	Maria
		 * - Vidas Secas		->	João
		 */
		map.put(dom, maria);
		map.put(tempo, maria);
		map.put(vidas, joao);

		// Impressão do mapa de empréstimos
		System.out.printf("LinkedHashMap após primeira inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro			->	Joaquim (livro emprestado)
		 * - Grande Sertão: Veredas ->	Joaquim
		 */
		map.put(dom, joaquim);
		map.put(sertao, joaquim);

		// Impressão do mapa de empréstimos
		System.out.printf("\nLinkedHashMap após segunda inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Devolução dos seguintes empréstimo
		 * - Dom Casmurro
		 * - O Tempo e o Vento
		 */
		map.remove(dom);
		map.remove(tempo);

		// Impressão do mapa de empréstimos
		System.out.printf("\nLinkedHashMap após remoção\n");
		imprimirEmprestimo(map);

		// =============================================================================

		// === Parte 3: Operações em TreeMap padrão ===
		
		System.out.println("\nParte 3: Operações em TreeMap padrão\n");

		/*
		 * TreeMap padrão: ordena por chave (título do livro)
		 * - Chave: livro emprestadp
		 * - Valor: aluno que emprestou o livro
		 */
		map = new TreeMap<>();

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro		->	Maria
		 * - O Tempo e o Vento 	->	Maria
		 * - Vidas Secas		->	João
		 */
		map.put(dom, maria);
		map.put(tempo, maria);
		map.put(vidas, joao);

		// Impressão do mapa de empréstimos
		System.out.printf("TreeMap (isbn) após primeira inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro			->	Joaquim (livro emprestado)
		 * - Grande Sertão: Veredas ->	Joaquim
		 */
		map.put(dom, joaquim);
		map.put(sertao, joaquim);

		// Impressão do mapa de empréstimos
		System.out.printf("\nTreeMap (isbn) após segunda inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Devolução dos seguintes empréstimo
		 * - Dom Casmurro
		 * - O Tempo e o Vento
		 */
		map.remove(dom);
		map.remove(tempo);

		// Impressão do mapa de empréstimos
		System.out.printf("\nLinkedHashMap (isbn) após remoção\n");
		imprimirEmprestimo(map);

		// =============================================================================

		// === Parte 4: Operações em TreeMap com Comparator ===

		System.out.println("\nParte 4: Operações em TreeMap com Comparator\n");

		/*
		 * TreeMap com comparador por data de publicação
		 * - Chave: livro emprestadp
		 * - Valor: aluno que emprestou o livro
		 */
		map = new TreeMap<>(Livro.compDataPublicacao);

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro		->	Maria
		 * - O Tempo e o Vento 	->	Maria
		 * - Vidas Secas		->	João
		 */
		map.put(dom, maria);
		map.put(tempo, maria);
		map.put(vidas, joao);

		// Impressão do mapa de empréstimos
		System.out.printf("TreeMap (data publicação) após primeira inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Adição dos seguintes empréstimo
		 * - Dom Casmurro			->	Joaquim (livro emprestado)
		 * - Grande Sertão: Veredas ->	Joaquim
		 */
		map.put(dom, joaquim);
		map.put(sertao, joaquim);

		// Impressão do mapa de empréstimos
		System.out.printf("\nTreeMap (data publicação) após segunda inserção\n");
		imprimirEmprestimo(map);

		/*
		 * Devolução dos seguintes empréstimo
		 * - Dom Casmurro
		 * - O Tempo e o Vento
		 */
		map.remove(dom);
		map.remove(tempo);

		// Impressão do mapa de empréstimos
		System.out.printf("\nLinkedHashMap (data publicação) após remoção\n");
		imprimirEmprestimo(map);
	}

    // Percorre todas as chaves do map e imprime empréstimos
    private static void imprimirEmprestimo(Map<Livro, Aluno> map) {
        System.out.println("Lista de empréstimos:");
        int i = 0;
        for (Livro l : map.keySet()) {
            System.out.printf("%d - %s -> %s\n", ++i, l, map.get(l));
        }
    }
}
