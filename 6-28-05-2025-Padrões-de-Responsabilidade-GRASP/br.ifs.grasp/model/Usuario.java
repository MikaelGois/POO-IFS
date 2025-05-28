package br.ifs.grasp.model;

public class Usuario {
    private String nome;
    private String email;
    private String telefone;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = senha;
    }

    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
