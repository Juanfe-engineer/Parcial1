
package Comparadores;

import Modelo.Paquete;
import java.util.Comparator;

public class ComparadoresPaquete {

    // Reportes: prioridad de mayor a menor.
    public static final Comparator<Paquete> POR_PRIORIDAD =
            (a, b) -> Integer.compare(
                    b.getPrioridad(),
                    a.getPrioridad()
            );

    // Reportes: peso de mayor a menor.
    public static final Comparator<Paquete> POR_PESO =
            (a, b) -> Double.compare(
                    b.getPeso(),
                    a.getPeso()
            );

    // Reportes: tiempo estimado de menor a mayor.
    public static final Comparator<Paquete> POR_TIEMPO =
            (a, b) -> Integer.compare(
                    a.getTiempoEstimado(),
                    b.getTiempoEstimado()
            );

    // Despacho: prioridad, luego tiempo y finalmente código.
    public static final Comparator<Paquete> PARA_DESPACHO =
            (a, b) -> {
                int resultado = Integer.compare(
                        b.getPrioridad(),
                        a.getPrioridad()
                );

                if (resultado != 0) {
                    return resultado;
                }

                resultado = Integer.compare(
                        a.getTiempoEstimado(),
                        b.getTiempoEstimado()
                );

                if (resultado != 0) {
                    return resultado;
                }

                return a.getCodigo().compareTo(b.getCodigo());
            };
}
