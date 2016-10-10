package modelo;

public class Rua {
	public static final Rua NULL = new Rua(Cidade.NULL, Cidade.NULL, 0);
	private Cidade origem, destino;
	private Double peso;

	public Rua(Cidade origem, Cidade destino, double peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}

	public Cidade getOrigem() {
		return origem;
	}

	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}

	public Cidade getDestino() {
		return destino;
	}

	public void setDestino(Cidade destino) {
		this.destino = destino;
	}

	public double getDistancia() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return getOrigem().getRotulo() + " - " + getDistancia() + " - " + getDestino().getRotulo();
	}

}
