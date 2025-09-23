package models;

import models.Gestion;

public class DataHolder {
    // Aquí se crea SOLO UNA VEZ la gestión con una lista vacía
    private static Gestion gestion = new Gestion(new java.util.ArrayList<>());
    // Método para obtener la misma instancia desde cualquier parte
    public static Gestion getGestion() {
        return gestion;
    }
}