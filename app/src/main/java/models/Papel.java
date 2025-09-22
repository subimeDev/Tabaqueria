package models;

public class Papel implements Identificar{
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
