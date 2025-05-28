import java.util.List;

public class Administrador extends Usuario {
    public Administrador(String id, String nome, String email) {
        super(id, nome, email);
    }

    public void adicionarLivro(List<Livro> catalogo, Livro livro) {
        catalogo.add(livro);
    }

    public void excluirLivro(List<Livro> catalogo, Livro livro) {
        catalogo.remove(livro);
    }

    public Relatorio gerarRelatorioMensal(List<Emprestimo> emprestimos) {
        return new Relatorio(emprestimos);
    }
}