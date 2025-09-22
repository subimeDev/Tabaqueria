package models;

public class Enroladora implements Identificar{
    private int id;
    private Double precio;
    private String marca;
    private String tipo;
    private String materia;

    public Enroladora() {
    }

    public Enroladora(int id, Double precio, String marca, String tipo, String materia) {
        this.id = id;
        this.precio = precio;
        this.marca = marca;
        this.tipo = tipo;
        this.materia = materia;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
