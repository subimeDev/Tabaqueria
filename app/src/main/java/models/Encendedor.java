package models;

public class Encendedor implements Producto{
    private int id;
    private double precio;
    private String Marca;
    private int cantidad;

    public Encendedor() {
    }

    public Encendedor(int id, double precio, String marca, int tipo) {
        this.id = id;
        this.precio = precio;
        Marca = marca;
        this.cantidad = tipo;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String getMarca() {
        return Marca;
    }

    @Override
    public void setMarca(String marca) {
        Marca = marca;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
