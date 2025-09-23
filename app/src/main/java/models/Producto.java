package models;

public interface Producto {
    int getId();
    String getMarca();
    int getCantidad();
    double getPrecio();

    void setMarca(String marca);
    void setCantidad(int cantidad);
    void setPrecio(double precio);

}
