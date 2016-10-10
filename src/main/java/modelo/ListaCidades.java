package modelo;


import java.util.ArrayList;
import java.util.List;

import modelo.Cidade;

public class ListaCidades {
    private final ArrayList<Cidade> vertices;
    public ListaCidades() {
        vertices = new ArrayList<>();
    }
    
    public Cidade get(int i){
        return vertices.get(i);
    }

    public void adicionar(Cidade vertice) {
        vertices.add(vertice);
    }

    public Cidade getPorId(int idVertice1) {
        for(Cidade v : vertices)
            if(v.getId() == idVertice1)
                return v;
        return Cidade.NULL;
    }

    public Cidade getPorRotulo(String rotulo) {
        for(Cidade v : vertices)
            if(v.getRotulo().equals(rotulo))
                return v;
        return Cidade.NULL;
    }

    public int getSize() {
        return vertices.size();
    }

    public List<Cidade> getAll() {
        return vertices;
    }
}
