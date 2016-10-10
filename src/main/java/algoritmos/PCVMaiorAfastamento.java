package algoritmos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import modelo.Cidade;
import modelo.Mapa;
import modelo.Rua;

public class PCVMaiorAfastamento {

	private Mapa mapa;
	private List<Cidade> subciclo;
	private List<Rua> ruas;
	private List<Cidade> naoInseridas;
	private List<Cidade> inseridas;

	public void setGrafo(Mapa mapa) {
		this.mapa = mapa;
		this.ruas = new ArrayList<>(mapa.getRuas());
		this.naoInseridas = new ArrayList<>(mapa.getCidades());
		this.inseridas = new ArrayList<>();
		this.subciclo = new ArrayList<>();
	}

	public void procurarMelhorCaminho() {

		Rua rua = encontraRuaMaisComprida();
		insereCidadesDaRua(rua);

		subciclo.add(rua.getOrigem());
		subciclo.add(rua.getDestino());
		subciclo.add(rua.getOrigem());

		boolean naoInseriuTodasAsCidades = !naoInseridas.isEmpty();
		while (naoInseriuTodasAsCidades) {

			List<Double> linhaMenoresDistancias = new ArrayList<>();
			for (Cidade naoInserida : naoInseridas) {
				double menorDistancia = encontrarMenorDistancia(naoInserida, inseridas);
				linhaMenoresDistancias.add(menorDistancia);
			}

			Cidade cidadeAInserir = getCidadeAInserir(linhaMenoresDistancias);
			int posicaoInserir = getPosicaoInserir(cidadeAInserir);

			subciclo.add(posicaoInserir, cidadeAInserir);
			inseridas.add(cidadeAInserir);
			naoInseridas.remove(cidadeAInserir);
			naoInseriuTodasAsCidades = !naoInseridas.isEmpty();
		}
	}

	private int getPosicaoInserir(Cidade cidade) {
		int indiceInserir = 0;
		double minDistancia = Double.POSITIVE_INFINITY;
		for (int i = 0; i < subciclo.size() - 1; i++) {
			Cidade cid1 = subciclo.get(i);
			Cidade cid2 = subciclo.get(i + 1);
			Double dist1 = mapa.getRua(cid1, cidade).getDistancia();
			Double dist2 = mapa.getRua(cid2, cidade).getDistancia();
			Double dist1Ate2 = mapa.getRua(cid1, cid2).getDistancia();
			double calc = dist1 + dist2 - dist1Ate2;
			if (calc < minDistancia) {
				minDistancia = calc;
				indiceInserir = i + 1;
			}
		}
		return indiceInserir;
	}

	private Cidade getCidadeAInserir(List<Double> linhaMenoresDistancias) {
		Double maiorDaLinha = linhaMenoresDistancias.stream().max(Comparator.comparing((dist) -> dist)).get();
		int indiceMaiorDaLinha = linhaMenoresDistancias.indexOf(maiorDaLinha);
		Cidade cidadeAInserir = naoInseridas.get(indiceMaiorDaLinha);
		return cidadeAInserir;
	}

	private double encontrarMenorDistancia(Cidade cidadeNaoInserida, List<Cidade> inseridas) {
		double menorDaColuna = Double.POSITIVE_INFINITY;
		for (Cidade cidadeInserida : inseridas) {
			double distancia = mapa.getRua(cidadeNaoInserida, cidadeInserida).getDistancia();
			if (distancia <= menorDaColuna)
				menorDaColuna = distancia;
		}
		return menorDaColuna;
	}

	private void insereCidadesDaRua(Rua ruaMaisComprida) {
		inseridas.add(ruaMaisComprida.getOrigem());
		inseridas.add(ruaMaisComprida.getDestino());
		naoInseridas.remove(ruaMaisComprida.getOrigem());
		naoInseridas.remove(ruaMaisComprida.getDestino());
	}

	public Rua encontraRuaMaisComprida() {
		return ruas.stream().max(Comparator.comparing((rua1) -> rua1.getDistancia())).get();
	}

	public String getCaminho() {
		return subciclo.toString();
	}

	public int getDistancia() {
		int distancia = 0;
		for (int i = 0; i < subciclo.size() - 1; i++)
			distancia += mapa.getRua(subciclo.get(i), subciclo.get(i + 1)).getDistancia();
		return distancia;
	}
}
