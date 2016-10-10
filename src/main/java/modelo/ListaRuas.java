package modelo;

import java.util.ArrayList;
import java.util.List;

import exceptions.ArestaInexistenteException;

public class ListaRuas {

    private final ArrayList<Rua> arestas;

    public ListaRuas() {
        arestas = new ArrayList<>();
    }

    public Rua get(int i) {
        try {
            return arestas.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new ArestaInexistenteException();
        }
    }

    public void adicionar(Rua aresta) {
        arestas.add(aresta);
    }

    public List<Rua> getAll() {
        return arestas;
    }

    public int getSize() {
        return arestas.size();
    }

	public Rua getRua(Cidade origem, Cidade destino) {
		for (Rua aresta : arestas) {
			if(aresta.getOrigem().equals(origem) && aresta.getDestino().equals(destino)
					|| aresta.getOrigem().equals(destino) && aresta.getDestino().equals(origem)){
				return aresta;
			}
		}
		return Rua.NULL;
	}
}
