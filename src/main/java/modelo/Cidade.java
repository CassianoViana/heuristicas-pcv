package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Cidade implements Comparable<Cidade> {

    public static Cidade NULL = new Cidade(null, -1);
    private Integer id;
    private String rotulo;
    private final ListaRuas arestas;

    public Cidade(String rotulo, int relId) {
        this.id = relId;
        this.rotulo = rotulo;
        arestas = new ListaRuas();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String nome) {
        this.rotulo = nome;
    }

    void adicionarAresta(Rua aresta) {
        arestas.adicionar(aresta);
    }

    public Collection<Cidade> getAdjacentes() {
        List<Cidade> vertices = new ArrayList<>();
        for (Rua aresta : arestas.getAll()) {
            vertices.add(aresta.getDestino());
        }
        Collections.sort(vertices);
        return vertices;
    }

    @Override
    public String toString() {
        return this.rotulo;
    }

    @Override
    public int compareTo(Cidade o) {
        if (this.getId() > o.getId()) {
            return 1;
        } else if (this.getId() < o.getId()) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cidade) {
            Cidade v = (Cidade) obj;
            return Objects.equals(this.getId(), v.getId()) && this.getRotulo().equals(v.getRotulo());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.rotulo);
        return hash;
    }

    public ListaRuas getArestas() {
        return this.arestas;
    }

    public Iterable<Cidade> getAdjacentesExceto(Cidade vertice) {
        Iterable<Cidade> adjacentes = getAdjacentes();
        for (Iterator<Cidade> iterator = adjacentes.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            if(next == vertice)
                iterator.remove();
        }
        return adjacentes;
    }
}
