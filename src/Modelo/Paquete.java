package Modelo;

public class Paquete implements Comparable<Paquete> {
    private String codigo;
    private String destino;
    private Double peso;
    private int prioridad;
    private int tiempoEstimado;

    public Paquete(String codigo, String destino, Double peso,
                   int prioridad, int tiempoEstimado) {
        this.codigo = codigo;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public Double getPeso() {
        return peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    @Override
    public String toString() {
        return "Paquete: " +
                "| codigo: " + codigo + '\'' +
                "| destino: " + destino + '\'' +
                "| peso: " + peso +
                "| prioridad: " + prioridad +
                "| tiempoEstimado: " + tiempoEstimado;
    }

    @Override
    public int compareTo(Paquete otro){
        return this.codigo.compareTo(otro.codigo);
    }
}
