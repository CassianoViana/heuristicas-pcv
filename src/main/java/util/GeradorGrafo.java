package util;

import modelo.Cidade;
import modelo.Mapa;
import modelo.Rua;

public class GeradorGrafo {

	private int minNrCidades;
	private int maxNrCidades;
	private int minDistancia;
	private int maxDistancia;

	public Mapa gerar() {

		Mapa mapa = new Mapa();

		int nrCidades = random(minNrCidades, maxNrCidades);

		for (int i = 0; i < nrCidades; i++)
			mapa.getCidades().add(new Cidade(String.valueOf(i), i));
		
		for(Cidade a : mapa.getCidades())
			for(Cidade b : mapa.getCidades())
				if(a != b && mapa.getRua(a, b) == Rua.NULL)
					mapa.getRuas().add(new Rua(a, b, random(minDistancia, maxDistancia)));

		return mapa;
	}

	private int random(int minDistancia2, int maxDistancia2) {
		return minDistancia2 + (int) (Math.random() * (maxDistancia2 - minDistancia2));
	}

	public void setNrCidades(int minNrCidades, int maxNrCidades) {
		this.minNrCidades = minNrCidades;
		this.maxNrCidades = maxNrCidades;
	}

	public void setDistancias(int minDistancia, int maxDistancia) {
		this.minDistancia = minDistancia;
		this.maxDistancia = maxDistancia;
	}

}
