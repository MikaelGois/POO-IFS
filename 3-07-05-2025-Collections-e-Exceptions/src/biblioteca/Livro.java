package biblioteca;

import java.time.LocalDate;
import java.util.Comparator;

public class Livro implements Comparable<Livro> {
    private String titulo;
    private String autor;
    private String isbn;
    private LocalDate dataPublicacao;

    public Livro(String isbn, String titulo, LocalDate dataPublicacao) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN não pode ser vazio");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        this.isbn = isbn;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) [%s]", titulo, dataPublicacao.getYear(), isbn);
    }

    @Override
    public int compareTo(Livro outro) {
        return this.isbn.compareTo(outro.isbn);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Livro)) return false;
        Livro outro = (Livro) obj;

        return isbn.equals(outro.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    public static final Comparator<Livro> compDataPublicacao = Comparator
        .comparing(Livro::getDataPublicacao);
}
