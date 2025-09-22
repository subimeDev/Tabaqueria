package models;

public class Encendedor implements Identificar{
    private int id;
    private double precio;
    private String Marca;
    private int tipo;

    public Encendedor() {
    }

    public Encendedor(int id, double precio, String marca, int tipo) {
        this.id = id;
        this.precio = precio;
        Marca = marca;
        this.tipo = tipo;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
