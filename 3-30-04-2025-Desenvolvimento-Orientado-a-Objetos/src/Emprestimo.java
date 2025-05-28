import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Emprestimo {
    private Requisitante usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private boolean renovado;

    public Emprestimo(Requisitante usuario, Livro livro, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(7);
        this.renovado = false;
        livro.emprestar();
        usuario.registrarEmprestimo();
    }

    public Requisitante getUsuario() { return usuario; }
    public Livro getLivro() { return livro; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }

    public boolean renovar(List<Reserva> reservas) {
        if (!renovado && reservas.stream().noneMatch(r -> r.getLivro().equals(livro))) {
            dataPrevistaDevolucao = dataPrevistaDevolucao.plusDays(7);
            renovado = true;
            return true;
        }
        return false;
    }

    public double devolver(LocalDate hoje) {
        long diasAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, hoje);
        livro.devolver();
        usuario.devolverEmprestimo();
        if (diasAtraso > 0) {
            return diasAtraso * 2.0;
        }
        return 0.0;
    }
}