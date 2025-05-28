package exception;

public class LivroJaEmprestadoException extends Exception {
    public LivroJaEmprestadoException(String titulo) {
        super(String.format("Erro: %s já está emprestado!\nTente renovar o livro!", titulo));
    }
}
