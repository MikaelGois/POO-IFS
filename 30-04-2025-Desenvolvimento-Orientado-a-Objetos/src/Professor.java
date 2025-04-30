public class Professor extends Usuario implements Requisitante {
    private int emprestimosAtivos;
    private static final int LIMITE_EMPRESTIMOS = 5;

    public Professor(String id, String nome, String email) {
        super(id, nome, email);
        this.emprestimosAtivos = 0;
    }

    @Override
    public boolean podeEmprestar() {
        return !bloqueado && emprestimosAtivos < LIMITE_EMPRESTIMOS;
    }

    @Override
    public void registrarEmprestimo() {
        emprestimosAtivos++;
    }

    @Override
    public void devolverEmprestimo() {
        if (emprestimosAtivos > 0) emprestimosAtivos--;
    }
}