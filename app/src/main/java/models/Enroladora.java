package models;

public class Enroladora implements Producto{
    private int id;
    private double precio;
    private String marca;
    private int cantidad;

    public Enroladora() {
    }

    public Enroladora(int id, double precio, String marca, int tipo) {
        this.id = id;
        this.precio = precio;
        this.marca = marca;
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
        return marca;
    }

    @Override
    public void setMarca(String marca) {
        this.marca = marca;
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
