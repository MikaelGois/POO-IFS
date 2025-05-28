public class Reserva {
    private Requisitante usuario;
    private Livro livro;
    private boolean notificado;

    public Reserva(Requisitante usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.notificado = false;
    }

    public Requisitante getUsuario() { return usuario; }
    public Livro getLivro() { return livro; }

    public void notificarDisponibilidade() {
        if (livro.estaDisponivel()) {
            this.notificado = true;
            // Implementar envio de notificação real (e-mail, SMS)
        }
    }

    public boolean isNotificado() { return notificado; }
}