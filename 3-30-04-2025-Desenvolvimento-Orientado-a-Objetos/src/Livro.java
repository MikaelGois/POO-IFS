public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private String categoria;
    private EstadoLivro estado;

    public Livro(String id, String titulo, String autor, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.estado = EstadoLivro.DISPONIVEL;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public EstadoLivro getEstado() { return estado; }

    public boolean estaDisponivel() { return estado == EstadoLivro.DISPONIVEL; }
    public void emprestar() { estado = EstadoLivro.EMPRESTADO; }
    public void reservar() { estado = EstadoLivro.RESERVADO; }
    public void devolver() { estado = EstadoLivro.DISPONIVEL; }
}

