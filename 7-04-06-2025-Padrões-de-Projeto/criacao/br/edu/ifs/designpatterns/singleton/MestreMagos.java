package br.edu.ifs.designpatterns.singleton;

public class MestreMagos {
	private static MestreMagos instancia;
	private String nome;
	private int nivelMagia;
	
    private MestreMagos() {
        this.nome = "Mestre dos Magos";
        this.nivelMagia = 1;
    }
    
    public void elevarNivel() {
        this.nivelMagia++;
        System.out.println(this.nome + " elevou o nível de magia para: " + this.nivelMagia);
    }
	
	// Getters
	public static MestreMagos obterInstancia() {
        if (instancia == null) {
            synchronized (MestreMagos.class) {
                if (instancia == null) {
                    instancia = new MestreMagos();
                }
            }
        }
        return instancia;
	}
	public String getNome() {
		return nome;
	}
	public int getNivelMagia() {
		return nivelMagia;
	}

	// Setters
	public static void setInstancia(MestreMagos instancia) {
		MestreMagos.instancia = instancia;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setNivelMagia(int nivelMagia) {
        if (nivelMagia > 0) {
            this.nivelMagia = nivelMagia;
        } else {
            System.out.println("O nível de magia deve ser positivo.");
        }
	}

}
