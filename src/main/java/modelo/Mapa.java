package modelo;

import java.util.List;

public class Mapa {

    private final ListaCidades cidades;
    private final ListaRuas ruas;
    boolean ponderado;
    boolean dirigido;

    public Mapa() {
        this.cidades = new ListaCidades();
        this.ruas = new ListaRuas();
    }

    public boolean isPonderado() {
        return ponderado;
    }

    public void setPonderado(boolean ponderado) {
        this.ponderado = ponderado;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    public void adicionarVertice(Cidade vertice) {
        cidades.adicionar(vertice);
    }

    public void adicionarAresta(int idVertice1, int idVertice2, double peso) {
        Cidade v1 = cidades.getPorId(idVertice1);
        Cidade v2 = cidades.getPorId(idVertice2);
        Rua aresta = new Rua(v1, v2, peso);
        v1.adicionarAresta(aresta);
        v2.adicionarAresta(aresta);
        this.ruas.adicionar(aresta);
    }

    public Cidade getVerticePorRotulo(String rotulo) {
        return cidades.getPorRotulo(rotulo);
    }

    public int getNrVertices() {
        return cidades.getSize();
    }

    public int getNrArestas() {
        return ruas.getSize();
    }

    public List<Cidade> getCidades() {
        return cidades.getAll();
    }
    
    public List<Rua> getRuas() {
        return ruas.getAll();
    }
	public Rua getRua(Cidade origem, Cidade destino) {
		return ruas.getRua(origem, destino);
	}
}
