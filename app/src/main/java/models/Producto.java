package models;

public class Producto {
    private  int id;
    private String Nombre;
    private int Cantidad;
    private double Precio;

    public Producto(int id) {
        this.id = id;
    }

    public Producto(int id, String nombre, int cantidad, double precio) {
        this.id = id;
        Nombre = nombre;
        Cantidad = cantidad;
        Precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }
}
