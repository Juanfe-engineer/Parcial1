
import Comparadores.ComparadoresPaquete;
import Modelo.Paquete;
import Modelo.Repartidor;
import Servicio.CentroDistribucion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CentroDistribucion centro = new CentroDistribucion();

        // PAQUETES DE PRUEBA
        Paquete[] paquetes = {
                new Paquete("PK-101", "Armenia", 4.5, 5, 2),
                new Paquete("PK-105", "Calarca", 8.2, 3, 4),
                new Paquete("PK-112", "Montenegro", 12.0, 4, 5),
                new Paquete("PK-120", "Quimbaya", 2.3, 1, 2),
                new Paquete("PK-127", "Circasia", 6.7, 2, 3),
                new Paquete("PK-134", "La Tebaida", 15.4, 5, 6),
                new Paquete("PK-142", "Armenia", 3.1, 2, 2),
                new Paquete("PK-151", "Calarca", 9.8, 4, 5),
                new Paquete("PK-163", "Montenegro", 5.6, 3, 3),
                new Paquete("PK-170", "Quimbaya", 11.2, 5, 7),
                new Paquete("PK-184", "Circasia", 7.4, 1, 4),
                new Paquete("PK-196", "La Tebaida", 13.6, 3, 6),
                new Paquete("PK-203", "Armenia", 2.8, 4, 2),
                new Paquete("PK-217", "Calarca", 16.1, 5, 8),
                new Paquete("PK-225", "Montenegro", 4.9, 2, 3),
                new Paquete("PK-239", "Quimbaya", 10.5, 3, 5),
                new Paquete("PK-246", "Circasia", 6.2, 4, 4),
                new Paquete("PK-258", "La Tebaida", 18.3, 5, 9),
                new Paquete("PK-269", "Armenia", 7.9, 3, 4),
                new Paquete("PK-275", "Calarca", 3.7, 1, 2),
                new Paquete("PK-281", "Montenegro", 14.8, 5, 7),
                new Paquete("PK-294", "Quimbaya", 8.6, 2, 4),
                new Paquete("PK-307", "Circasia", 5.3, 3, 3),
                new Paquete("PK-319", "La Tebaida", 9.1, 4, 5),
                new Paquete("PK-326", "Armenia", 12.7, 5, 6),
                new Paquete("PK-338", "Calarca", 6.4, 2, 3),
                new Paquete("PK-349", "Montenegro", 17.5, 4, 8),
                new Paquete("PK-357", "Quimbaya", 3.9, 1, 2),
                new Paquete("PK-368", "Circasia", 11.8, 5, 6),
                new Paquete("PK-382", "La Tebaida", 4.2, 2, 3)
        };

        for (Paquete paquete : paquetes) {
            centro.registrarPaquete(paquete);
        }

        // REPARTIDORES
        Repartidor[] repartidores = {
                new Repartidor("R-101", "Carlos", "Armenia", true),
                new Repartidor("R-205", "Laura", "Calarca", true),
                new Repartidor("R-318", "Andres", "Montenegro", true),
                new Repartidor("R-427", "Sofia", "Quimbaya", true),
                new Repartidor("R-536", "Mateo", "Circasia", true),
                new Repartidor("R-649", "Valentina", "La Tebaida", true)
        };

        for (Repartidor repartidor : repartidores) {
            centro.registrarRepartidor(repartidor);
        }

        System.out.println("Paquetes registrados: "
                + centro.obtenerPaquetes().length);

        System.out.println("Repartidores registrados: "
                + centro.obtenerRepartidores().length);

        // 1. ORDEN NATURAL: CÓDIGO ASCENDENTE
        Paquete[] paquetesNaturales = centro.obtenerPaquetes();
        Arrays.sort(paquetesNaturales);

        imprimirPaquetes(
                "Orden natural por código ascendente",
                paquetesNaturales
        );

        // 2. REPORTE: PRIORIDAD DESCENDENTE
        Paquete[] porPrioridad = centro.obtenerPaquetes();
        Arrays.sort(porPrioridad, ComparadoresPaquete.POR_PRIORIDAD);

        imprimirPaquetes(
                "Reporte por prioridad (mayor a menor)",
                porPrioridad
        );

        // 3. REPORTE: PESO DESCENDENTE
        Paquete[] porPeso = centro.obtenerPaquetes();
        Arrays.sort(porPeso, ComparadoresPaquete.POR_PESO);

        imprimirPaquetes(
                "Reporte por peso (mayor a menor)",
                porPeso
        );

        // 4. REPORTE: TIEMPO ASCENDENTE
        Paquete[] porTiempo = centro.obtenerPaquetes();
        Arrays.sort(porTiempo, ComparadoresPaquete.POR_TIEMPO);

        imprimirPaquetes(
                "Reporte por tiempo (menor a mayor)",
                porTiempo
        );

        // 5. ORDEN COMPLETO DE DESPACHO
        Paquete[] paraDespacho = centro.obtenerPaquetesPendientes();
        Arrays.sort(paraDespacho, ComparadoresPaquete.PARA_DESPACHO);

        imprimirPaquetes(
                "Despacho por prioridad, tiempo y código",
                paraDespacho
        );

        // MUNICIPIOS
        System.out.println("\nMunicipios únicos:");
        System.out.println(Arrays.toString(
                centro.obtenerMunicipiosUnicos()
        ));

        System.out.println("\nMunicipios ordenados alfabéticamente:");
        System.out.println(Arrays.toString(
                centro.obtenerMunicipiosOrdenados()
        ));

        // BÚSQUEDAS
        System.out.println("\nBúsqueda del paquete PK-217:");
        System.out.println(centro.buscarPaquete("PK-217"));

        System.out.println("\nBúsqueda del repartidor R-318:");
        System.out.println(centro.buscarRepartidor("R-318"));

        // PAQUETES POR MUNICIPIO
        System.out.println("\nPaquetes destinados a Armenia:");

        for (Paquete paquete :
                centro.obtenerPaquetesPorMunicipio("Armenia")) {
            System.out.println(paquete);
        }

        // PESO TOTAL POR MUNICIPIO
        System.out.println("\nPeso total destinado a Armenia:");
        System.out.println(
                centro.calcularPesoTotalPorMunicipio("Armenia") + " kg"
        );

        // CONTAR POR PRIORIDAD
        System.out.println("\nPaquetes con prioridad igual o superior a 4:");
        System.out.println(centro.contarPaquetesPorPrioridad(4));

        // PAQUETE DE MAYOR PESO
        System.out.println("\nPaquete de mayor peso:");
        System.out.println(centro.encontrarPaqueteMayor());

        // CONSULTAR EL SIGUIENTE DESPACHO
        System.out.println(
                "\nSiguiente paquete para despacho:"
        );
        System.out.println(
                centro.consultarSiguientePaquetePrioridad()
        );

        // ASIGNACIÓN
        Paquete paqueteAsignar = centro.buscarPaquete("PK-101");
        Repartidor repartidor = centro.buscarRepartidor("R-101");

        System.out.println("\nAsignación del paquete PK-101:");
        System.out.println(
                centro.asignarPaquete(paqueteAsignar, repartidor)
        );

        // ATENCIÓN POR ORDEN DE LLEGADA
        System.out.println("\nAtención por orden de llegada:");
        System.out.println(centro.atenderPorLlegada());

        // DESPACHO POR PRIORIDAD, TIEMPO Y CÓDIGO
        System.out.println("\nDespacho según las reglas de prioridad:");
        System.out.println(centro.despacharPorPrioridad());

        // HISTORIAL
        System.out.println("\nHistorial de entregas:");

        for (Paquete paquete : centro.obtenerHistorialEntregas()) {
            System.out.println(paquete);
        }

        // BÚSQUEDA BINARIA POR CÓDIGO
        Paquete[] paquetesOrdenados = centro.obtenerPaquetes();
        Arrays.sort(paquetesOrdenados);

        List<Paquete> listaOrdenada =
                new ArrayList<>(Arrays.asList(paquetesOrdenados));

        int posicion = centro.buscarPaquetePorCodigo(
                listaOrdenada,
                "PK-294"
        );

        System.out.println("\nBúsqueda binaria de PK-294:");

        if (posicion != -1) {
            System.out.println(listaOrdenada.get(posicion));
        } else {
            System.out.println("El paquete no existe.");
        }

        // PAQUETES QUE AÚN ESTÁN PENDIENTES
        System.out.println("\nPaquetes pendientes al finalizar:");

        for (Paquete paquete : centro.obtenerPaquetesPendientes()) {
            System.out.println(paquete);
        }
    }

    private static void imprimirPaquetes(
            String titulo,
            Paquete[] paquetes) {

        System.out.println("\n" + titulo + ":");

        for (Paquete paquete : paquetes) {
            System.out.println(paquete);
        }
    }
}
