import java.time.LocalDate;

public class Bibliotecario extends Usuario {
    public Bibliotecario(String id, String nome, String email) {
        super(id, nome, email);
    }

    public boolean aprovarEmprestimo(Requisitante usuario) {
        return usuario.podeEmprestar();
    }

    public double registrarDevolucao(Emprestimo emprestimo) {
        LocalDate hoje = LocalDate.now();
        double multa = emprestimo.devolver(hoje);
        emprestimo.getUsuario().devolverEmprestimo();
        return multa;
    }
}