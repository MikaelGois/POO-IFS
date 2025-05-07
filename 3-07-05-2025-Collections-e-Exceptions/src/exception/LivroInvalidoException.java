package exception;

public class LivroInvalidoException extends Exception {
    public LivroInvalidoException() {
        super("Erro crítico: Livro não pode ser nulo!");
    }
}