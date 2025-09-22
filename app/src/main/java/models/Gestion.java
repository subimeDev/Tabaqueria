package models;

import java.security.spec.PSSParameterSpec;
import java.util.List;

public class Gestion {
    private List<Producto> listaProductos;

    public Gestion(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void agregarProducto(Producto p){
listaProductos.add(p);
    }
    public List<Producto> obtenerProductos(){
        return  listaProductos;
    }


    public void eliminarProducto(int id){
        listaProductos.removeIf(p -> p.getId() == id);

    }
    public  void actualizarProduct(Producto productoActualizado){
        for (int i =0; i < listaProductos.size(); i++){ //recorrimos la lista  usando el for
            Producto p = listaProductos.get(i);   //optenemos el producto actual
            if (p.getId() ==productoActualizado.getId()){ // comparo las id ( osea si conside con lo que quiero modificar)
                listaProductos.set(i,productoActualizado); // remplazo el producto en la lista usando esto
                break;
            }
        }
    }
}