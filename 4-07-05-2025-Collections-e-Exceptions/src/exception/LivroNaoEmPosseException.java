package exception;

public class LivroNaoEmPosseException extends Exception {
    public LivroNaoEmPosseException(String mensagem) {
        super(mensagem);
    }
}
