public interface Requisitante {
    boolean podeEmprestar();
    void registrarEmprestimo();
    void devolverEmprestimo();
}