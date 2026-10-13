package Modelo;

public class Repartidor {
    private String identificacion;
    private String nombre;
    private String zona;
    private boolean disponible;

    public Repartidor(String identificacion, String nombre,
                      String zona, boolean disponible) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.zona = zona;
        this.disponible = disponible;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getZona() {
        return zona;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Repartidor: " +
                "| identificacion: " + identificacion + '\'' +
                "| nombre: " + nombre + '\'' +
                "| zona: " + zona + '\'' +
                "| disponible: " + disponible;
    }


    // Asignar paquete

    public boolean asignarPaquete(Paquete paquete){
        if(paquete == null || !disponible){
            return false;
        }
        this.disponible = false;
        return true;
    }
}
