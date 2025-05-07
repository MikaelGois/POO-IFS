public abstract class Usuario {
    protected String id;
    protected String nome;
    protected String email;
    protected boolean bloqueado;

    public Usuario(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.bloqueado = false;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public boolean isBloqueado() { return bloqueado; }
    public void bloquear() { this.bloqueado = true; }
}