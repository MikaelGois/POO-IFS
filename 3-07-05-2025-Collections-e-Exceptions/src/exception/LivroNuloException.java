package exception;

public class LivroNuloException extends ExcecaoEmprestimo {
    public LivroNuloException() {
        super("Erro crítico: Livro não pode ser nulo!");
    }
}
