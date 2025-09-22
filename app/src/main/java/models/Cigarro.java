package models;

public class Cigarro implements Identificar{
    private int id;
    private String marca;
    private int cantidad;
    private String tipo;
    private Double precio;

    public Cigarro() {
    }

    public Cigarro(int id, String marca, int cantidad, String tipo, Double precio) {
        this.id = id;
        this.marca = marca;
        this.cantidad = cantidad;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
