import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BibliotecaService service = new BibliotecaService();

        // TESTES ESTÁTICOS

        // Cria os usuários
        Aluno aluno = new Aluno("A1", "Maria", "maria@ifs.com");
        Professor prof = new Professor("P1", "João", "joao@ifs.com");
        Bibliotecario bib = new Bibliotecario("B1", "Ana", "ana@ifs.com");
        Administrador admin = new Administrador("AD1", "Carlos", "carlos@ifs.com");

        // Adiciona os livros
        Livro livro1 = new Livro("L1", "Java Básico", "Autor X", "Programação");
        Livro livro2 = new Livro("L2", "UML Básico", "Autor Y", "Modelagem");
        service.adicionarLivro(admin, livro1);
        service.adicionarLivro(admin, livro2);

        // Aluno solicita o empréstimo
        boolean emprestou = service.solicitarEmprestimo(aluno, livro1, bib);
        System.out.println("Empréstimo de '" + livro1.getTitulo() + "' pelo aluno: " + emprestou);

        // Professor reserva o segundo livro
        boolean reservou = service.reservarLivro(prof, livro2);
        System.out.println("Reserva de '" + livro2.getTitulo() + "' pelo professor: " + reservou);

        // Tentativa de renovação sem devolução
        Emprestimo em = service.getEmprestimo(aluno, livro1);
        boolean renovou = service.renovarEmprestimo(em);
        System.out.println("Renovação feita: " + renovou);

        // Devolução após atraso
        double multa = service.devolverLivro(em, bib);
        System.out.println("Multa cobrada: R$ " + multa);

        // Gera relatório
        Relatorio rel = service.gerarRelatorioMensal(admin);
        System.out.println("Relatório gerado: " + rel);
    }
}