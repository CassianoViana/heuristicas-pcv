package algoritmos;

import java.util.ArrayList;
import java.util.List;

import modelo.Rua;
import modelo.Cidade;
import modelo.Mapa;

public class PCVMenorEncargo {

	String caminho;
	private double custo;
	private Mapa mapa;
	public int count = 0;

	/**
	 * 1. Escolher o par de vértices que formam o subciclo de menor distância
	 * total.<br>
	 * 2. Inserir o vértice escolhido de modo a minimizar o aumento da distância
	 * total.<br>
	 * 3. Repetir este procedimento, no subciclo corrente, até completar o
	 * ciclo.
	 */

	public PCVMenorEncargo(Mapa mapa) {
		this.mapa = mapa;
	}

	class PosicaoCidade {
		Integer posicao;
		Cidade cidade;

		@Override
		public String toString() {
			return cidade.getRotulo() + " - " + posicao;
		}
	}

	public void executar() {
		Rua caminhoMaisCurto = getCaminhoMaisCurto(); // n

		List<Cidade> cidadesVisitadas = new ArrayList<Cidade>();
		cidadesVisitadas.add(caminhoMaisCurto.getOrigem());
		cidadesVisitadas.add(caminhoMaisCurto.getDestino());
		cidadesVisitadas.add(caminhoMaisCurto.getOrigem());

		// Enquanto existir cidade não inserida
		while (!getCidadesNaoContidasEm(cidadesVisitadas).isEmpty()) {// n
			PosicaoCidade posicaoInserir = obterMenosAfastado(cidadesVisitadas,
					getCidadesNaoContidasEm(cidadesVisitadas));
			cidadesVisitadas.add(posicaoInserir.posicao, posicaoInserir.cidade);
		}

		this.caminho = cidadesVisitadas.toString();
		this.custo = calcularCusto(cidadesVisitadas);
	}

	private double calcularCusto(List<Cidade> vertices) {
		double custo = 0;
		for (int i = 0; i < vertices.size() - 1; i++) {
			custo += mapa.getRua(vertices.get(i), vertices.get(i + 1)).getDistancia();
		}
		return custo;
	}

	private PosicaoCidade obterMenosAfastado(List<Cidade> cidadesVisitadas, List<Cidade> cidadesNaoVisitadas) {

		Cidade cidadeMaisPerto = cidadesNaoVisitadas.get(0);
		Cidade vizinho1 = null, vizinho2 = null;
		double distancia = Double.MAX_VALUE;
		Integer posicaoInserir = 0;

		for (int pos = 0; pos < cidadesVisitadas.size() - 1; pos++) {
			for (Cidade cidade : cidadesNaoVisitadas) {

				vizinho1 = cidadesVisitadas.get(pos);
				vizinho2 = cidadesVisitadas.get(pos + 1);

				double distanciaCalculada = calcularDistancia(vizinho1, cidade, vizinho2);
				count++;
				if (distanciaCalculada < distancia) {// OPERAÇÃO FUNDAMENTAL
					distancia = distanciaCalculada;
					cidadeMaisPerto = cidade;
					posicaoInserir = pos + 1;
				}
			}
		}

		PosicaoCidade posInserir = new PosicaoCidade();
		posInserir.posicao = posicaoInserir;
		posInserir.cidade = cidadeMaisPerto;
		return posInserir;
	}

	private double calcularDistancia(Cidade vertice, Cidade verticeCandidato, Cidade vertice2) {
		Rua aresta1 = mapa.getRua(vertice, verticeCandidato);
		Rua aresta2 = mapa.getRua(verticeCandidato, vertice2);
		Rua original = mapa.getRua(vertice, vertice2);
		return aresta1.getDistancia() + aresta2.getDistancia() - original.getDistancia();
	}

	private Rua getCaminhoMaisCurto() {
		Rua arestaMinPeso = new Rua(null, null, Double.POSITIVE_INFINITY);
		for (Rua aresta : mapa.getRuas())
			if (aresta.getDistancia() < arestaMinPeso.getDistancia())
				arestaMinPeso = aresta;
		return arestaMinPeso;
	}

	private List<Cidade> getCidadesNaoContidasEm(List<Cidade> vertices) {
		List<Cidade> not = new ArrayList<>();
		for (Cidade vertice : mapa.getCidades()) {
			if (!vertices.contains(vertice)) {
				not.add(vertice);
			}
		}
		return not;
	}

	public String getCaminho() {
		return caminho;
	}

	public double getCusto() {
		return custo;
	}
}
