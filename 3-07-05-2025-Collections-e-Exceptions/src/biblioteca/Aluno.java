package biblioteca;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import exception.EmprestimosExcedidoException;
import exception.LivroInvalidoException;
import exception.LivroJaEmprestadoException;
import exception.LivroNaoEmPosseException;

public class Aluno implements Comparable<Aluno> {
    private String matricula;
    private String nome;
    private Map<Livro, Integer> emprestimos = new HashMap<>(); // livro -> contador de renovações
    private static final int LIMITE = 3;

    public Aluno(String matricula, String nome) {
        if (matricula == null || matricula.isEmpty()) {
            throw new IllegalArgumentException("Matrícula não pode ser vazia");
        }
        this.matricula = matricula;
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }
    
    public void emprestar(Livro livro) throws LivroInvalidoException, EmprestimosExcedidoException, LivroJaEmprestadoException {
        if (livro == null) throw new LivroInvalidoException();
        if (emprestimos.size() >= LIMITE) throw new EmprestimosExcedidoException();
        if (emprestimos.containsKey(livro)) throw new LivroJaEmprestadoException(livro.getTitulo());
        emprestimos.put(livro, 0);
        System.out.printf("%s emprestado para %s (%s)\n", livro, nome, matricula);
    }

    public void renovar(Livro livro) throws LivroInvalidoException, LivroNaoEmPosseException {
        if (livro == null) throw new LivroInvalidoException();
        Integer ren = emprestimos.get(livro);
        if (ren == null) throw new LivroNaoEmPosseException(
            String.format("Erro: %s não pode ser renovado porque não está sob sua posse!\nTente emprestar o livro solicitado antes de renová-lo!", livro)
        );
        emprestimos.put(livro, ren + 1);
        System.out.printf("%s renovado para %s (%s)\n", livro, nome, matricula);
    }

    public void devolver(Livro livro) throws LivroInvalidoException, LivroNaoEmPosseException {
        if (livro == null) throw new LivroInvalidoException();
        if (!emprestimos.containsKey(livro)) {
            throw new LivroNaoEmPosseException(
                String.format("Erro: %s não pode ser devolvido porque não está sob sua posse!\nTente emprestar o livro solicitado antes de devolvê-lo!", livro)
            );
        }
        emprestimos.remove(livro);
        System.out.printf("%s devolvido por %s (%s)\n", livro, nome, matricula);
    }

    @Override
    public String toString() {
        return nome + " (" + matricula + ")";
    }

    @Override
    public int compareTo(Aluno outro) {
        return this.matricula.compareTo(outro.matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Aluno)) return false;
        Aluno outro = (Aluno) obj;
        
        return matricula.equals(outro.matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }

    public static final Comparator<Aluno> comparadorNome = Comparator.comparing(Aluno::getNome).thenComparing(Aluno::getMatricula);
}