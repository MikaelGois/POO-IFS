package exception;

public class EmprestimosExcedidoException extends Exception {
    public EmprestimosExcedidoException() {
        super("Erro: Limite de 3 empréstimos atingido!\nTente devolver um dos livros emprestados!");
    }
}
