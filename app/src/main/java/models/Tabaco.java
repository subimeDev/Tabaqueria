package models;

public class Tabaco implements Producto{
    private  int id;
    private String Marca;
    private int Cantidad;
    private double Precio;

    public Tabaco() {
    }

    public Tabaco(int id, String marca, int cantidad, double precio) {
        this.id = id;
        Marca = marca;
        Cantidad = cantidad;
        Precio = precio;
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
        return Marca;
    }

    @Override
    public void setMarca(String marca) {
        Marca = marca;
    }

    @Override
    public int getCantidad() {
        return Cantidad;
    }

    @Override
    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    @Override
    public double getPrecio() {
        return Precio;
    }

    @Override
    public void setPrecio(double precio) {
        Precio = precio;
    }
}
