package models;

public class Papel implements Producto{
    private int id;
    private String marca;
    private int cantidad;
    private double precio;

    public Papel() {
    }

    public Papel(int id, String marca, int cantidad, double precio) {
        this.id = id;
        this.marca = marca;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
