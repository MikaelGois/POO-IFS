import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaService {
    private List<Livro> catalogo = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    public void adicionarLivro(Administrador admin, Livro livro) {
        admin.adicionarLivro(catalogo, livro);
    }

    public List<Livro> buscar(String termo) {
        return catalogo.stream()
                .filter(l -> l.getTitulo().contains(termo)
                        || l.getAutor().contains(termo)
                        || l.getCategoria().contains(termo))
                .collect(Collectors.toList());
    }

    public boolean solicitarEmprestimo(Requisitante usuario, Livro livro, Bibliotecario biblio) {
        if (!livro.estaDisponivel()) return false;
        if (!biblio.aprovarEmprestimo(usuario)) return false;
        Emprestimo em = new Emprestimo(usuario, livro, LocalDate.now());
        emprestimos.add(em);
        return true;
    }

    public boolean reservarLivro(Requisitante usuario, Livro livro) {
        if (!livro.estaDisponivel()) {
            livro.reservar();
            Reserva res = new Reserva(usuario, livro);
            reservas.add(res);
            return true;
        }
        return false;
    }

    public boolean renovarEmprestimo(Emprestimo em) {
        List<Reserva> resLivro = reservas.stream()
                .filter(r -> r.getLivro().equals(em.getLivro()))
                .collect(Collectors.toList());
        return em.renovar(resLivro);
    }

    public double devolverLivro(Emprestimo em, Bibliotecario biblio) {
        double multa = biblio.registrarDevolucao(em);
        reservas.stream()
                .filter(r -> r.getLivro().equals(em.getLivro()))
                .forEach(Reserva::notificarDisponibilidade);
        return multa;
    }

    public Relatorio gerarRelatorioMensal(Administrador admin) {
        return admin.gerarRelatorioMensal(emprestimos);
    }

    public Emprestimo getEmprestimo(Requisitante usuario, Livro livro) {
        return emprestimos.stream()
                .filter(e -> e.getUsuario().equals(usuario) && e.getLivro().equals(livro))
                .findFirst()
                .orElse(null);
    }
}