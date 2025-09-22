package models;

import java.security.spec.PSSParameterSpec;
import java.util.List;

public class Gestion<T extends Identificar> {
    private List<T> listaElementos;

    public Gestion(List<T> listaElementos) {
        this.listaElementos = listaElementos;
    }

    public void agregar(T elemento) {
        listaElementos.add(elemento);
    }

    public List<T> obtenerTodos() {
        return listaElementos;
    }

    public void eliminar(int id) {
        listaElementos.removeIf(e -> e.getId() == id);
    }

    public void actualizar(T elementoActualizado) {
        for (int i = 0; i < listaElementos.size(); i++) {
            T e = listaElementos.get(i);
            if (e.getId() == elementoActualizado.getId()) {
                listaElementos.set(i, elementoActualizado);
                break;
            }
        }
    }
}
