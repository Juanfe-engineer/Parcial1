package Servicio;

import Comparadores.ComparadoresPaquete;
import Estructuras.IteradorLista;
import Estructuras.ListaEnlazada;
import Modelo.Paquete;
import Modelo.Repartidor;

import java.util.*;

public class CentroDistribucion {

    // Cola que conserva el orden de llegada.
    private final Queue<Paquete> paquetesPendientes =
            new ArrayDeque<>();

    // Búsqueda rápida por código.
    private final Map<String, Paquete> paquetesPorCodigo =
            new HashMap<>();

    private final Set<String> municipiosUnicos =
            new HashSet<>();

    private final SortedSet<String> municipiosOrdenados =
            new TreeSet<>();

    // Orden completo para determinar el siguiente despacho.
    private final PriorityQueue<Paquete> paquetesPorPrioridad =
            new PriorityQueue<>(ComparadoresPaquete.PARA_DESPACHO);

    private ListaEnlazada<Paquete> historialEntregas;

    private Map<String, Repartidor> repartidoresPorIdentificacion;
    private Map<String, ListaEnlazada<Paquete>> paquetesPorMunicipio;

    public CentroDistribucion() {
        historialEntregas = new ListaEnlazada<>();
        repartidoresPorIdentificacion = new HashMap<>();
        paquetesPorMunicipio = new HashMap<>();
    }

    // PAQUETES

    // REGISTRAR PAQUETE

    public boolean registrarPaquete(Paquete paquete) {
        if (paquete == null){
            return false;
        }

        String codigo = paquete.getCodigo();
        String municipio = paquete.getDestino();

        if (codigo == null || codigo.isBlank()
                || municipio == null || municipio.isBlank()) {
            return false;
        }

        if (paquetesPorCodigo.containsKey(codigo)) {
            return false;
        }

        // Validamos restricciones del paquete
        if (paquete.getPeso() <= 0 || paquete.getPrioridad() < 1
                || paquete.getPrioridad() > 5 || paquete.getTiempoEstimado() <= 0) {
            return false;
        }

        paquetesPorCodigo.put(codigo, paquete);
        paquetesPendientes.offer(paquete);
        paquetesPorPrioridad.offer(paquete);
        municipiosUnicos.add(municipio);
        municipiosOrdenados.add(municipio);

        //agrupar el paquete por municipio de destino
        ListaEnlazada<Paquete> paquetesMunicipio = paquetesPorMunicipio.get(municipio);
        if (paquetesMunicipio == null) {
            paquetesMunicipio = new ListaEnlazada<>();
            paquetesPorMunicipio.put(municipio, paquetesMunicipio);
        }
        paquetesMunicipio.add(paquete);

        return true;
    }

    // BUSCAR PAQUETE

    public Paquete buscarPaquete(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return null;
        }
        return paquetesPorCodigo.get(codigo);
    }

    // OBTENER PAQUETES

    public Paquete[] obtenerPaquetes() {
        return paquetesPorCodigo.values().toArray(new Paquete[0]);
    }


    // REPARTIDORES

    // REGISTRAR REPARTIDOR

    public boolean registrarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            return false;
        }

        String identificacion = repartidor.getIdentificacion();
        if (identificacion == null || identificacion.isBlank()) {
            return false;
        }
        if (repartidoresPorIdentificacion.containsKey(identificacion)) {
            return false;
        }

        repartidoresPorIdentificacion.put(identificacion, repartidor);
        return true;
    }

    // BUSCAR REPARTIDOR

    public Repartidor buscarRepartidor(String identificacion) {
        if (identificacion == null || identificacion.isBlank()) {
            return null;
        }
        return repartidoresPorIdentificacion.get(identificacion);
    }

    // OBTENER REPARTIDORES

    public Repartidor[] obtenerRepartidores() {
        return repartidoresPorIdentificacion.values().toArray(new Repartidor[0]);
    }


    // ASIGNACIÓN

    // ASIGNAR PAQUETE

    public boolean asignarPaquete(Paquete paquete, Repartidor repartidor) {
        if (paquete == null || repartidor == null) {
            return false;
        }

        String codigo = paquete.getCodigo();
        String identificacion = repartidor.getIdentificacion();
        if (codigo == null || identificacion == null) {
            return false;
        }

        // Deben ser los mismos objetos que se registraron en este centro
        if (paquetesPorCodigo.get(codigo) != paquete
                || repartidoresPorIdentificacion.get(identificacion) != repartidor) {
            return false;
        }

        // repartidor debe estar disponible
        if (!repartidor.isDisponible()) {
            return false;
        }

        String destino = paquete.getDestino();
        String zona = repartidor.getZona();
        if (destino == null || zona == null || !destino.equalsIgnoreCase(zona)) {
            return false;
        }

        if (!paquetesPendientes.contains(paquete)) {
            return false;
        }

        // Devuelve el resultado real de la asignación en Repartidor.
        return repartidor.asignarPaquete(paquete);
    }

    // OBTENER PAQUETES PENDIENTES

    public Paquete[] obtenerPaquetesPendientes() {
        return paquetesPendientes.toArray(new Paquete[0]);
    }


    // ATENCIÓN Y ENTREGAS


    // ATENDER POR LLEGADA -

    public Paquete atenderPorLlegada() {
        Paquete paquete = paquetesPendientes.peek();
        if (paquete == null) {
            return null;
        }
        if (!registrarEntrega(paquete)) {
            return null;
        }
        return paquete;
    }

    // CONSULTAR SIGUIENTE PAQUETE POR PRIORIDAD

    public Paquete consultarSiguientePaquetePrioridad() {
        return paquetesPorPrioridad.peek();
    }

    // DESPACHAR POR PRIORIDAD

    public Paquete despacharPorPrioridad() {
        Paquete paquete = paquetesPorPrioridad.peek();
        if (paquete == null) {
            return null;
        }
        if (!registrarEntrega(paquete)) {
            return null;
        }
        return paquete;
    }

    // REGISTRAR ENTREGA

    public boolean registrarEntrega(Paquete paquete) {
        if (paquete == null || paquete.getCodigo() == null
                || paquete.getCodigo().isBlank()) {
            return false;
        }

        String codigo = paquete.getCodigo();
        if (paquetesPorCodigo.get(codigo) != paquete) {
            return false;
        }
        if (!paquetesPendientes.contains(paquete)) {
            return false;
        }

        // Evita guardar dos veces el mismo código en el historial
        for (Paquete entrega : historialEntregas) {
            if (entrega.getCodigo().equals(codigo)) {
                return false;
            }
        }

        historialEntregas.add(paquete);
        paquetesPendientes.remove(paquete);
        paquetesPorPrioridad.remove(paquete);
        return true;
    }

    // OBTENER HISTORIAL DE ENTREGAS

    public Paquete[] obtenerHistorialEntregas() {
        Paquete[] historial = new Paquete[historialEntregas.size()]; // creamos un arreglo del tamaño del historial

        IteradorLista<Paquete> iterador = historialEntregas.iterador();
        int indice = 0;

        while (iterador.hasNext()) {
            historial[indice] = iterador.next();
            indice++;
        }
        return historial;
    }


    // MUNICIPIOS

    // OBTENER MUNICIPIOS UNICOS

    public String[] obtenerMunicipiosUnicos() {
        return municipiosUnicos.toArray(new String[0]);
    }

    // OBTENER MUNICIPIOS ORDENADOS

    public String[] obtenerMunicipiosOrdenados() {
        return municipiosOrdenados.toArray(new String[0]);
    }

    // OBTENER PAQUETES POR MUNICIPIO

    public Paquete[] obtenerPaquetesPorMunicipio(String municipio) {
        if (municipio == null || municipio.isBlank()) {
            return new Paquete[0];
        }

        ListaEnlazada<Paquete> paquetesMunicipio = paquetesPorMunicipio.get(municipio);
        if (paquetesMunicipio == null || paquetesMunicipio.isEmpty()) {
            return new Paquete[0];
        }

        Paquete[] resultado = new Paquete[paquetesMunicipio.size()];

        IteradorLista<Paquete> iterador = paquetesMunicipio.iterador();
        int indice = 0;

        while (iterador.hasNext()) {
            resultado[indice] = iterador.next();
            indice++;
        }
        return resultado;
    }


    // ALGORITMOS RECURSIVOS

    // CALCULAR PESO TOTAL POR MUNICIPIO

    public double calcularPesoTotalPorMunicipio(String municipio) {
        if (municipio == null || municipio.isBlank()) {
            return 0.0;
        }

        ListaEnlazada<Paquete> paquetesMunicipio = paquetesPorMunicipio.get(municipio);
        if (paquetesMunicipio == null || paquetesMunicipio.isEmpty()) {
            return 0.0;
        }

        return calcularPesoRecursivo(paquetesMunicipio.iterador());
    }

    private double calcularPesoRecursivo(IteradorLista<Paquete> iterador) {
        // Caso base: no quedan paquetes.
        if (!iterador.hasNext()) {
            return 0.0;
        }

        // tomar el paquete actual
        Paquete paqueteActual = iterador.next();

        // Caso recursivo: calcula el peso del resto
        double pesoRestante = calcularPesoRecursivo(iterador);
        return paqueteActual.getPeso() + pesoRestante;
    }


    // CONTAR PAQUETES POR PRIORIDAD

    public int contarPaquetesPorPrioridad(int prioridadMinima) {
        if (prioridadMinima < 1 || prioridadMinima > 5) {
            return 0;
        }

        return contarPrioridadRecursiva(paquetesPorCodigo.values().iterator(), prioridadMinima);
    }

    private int contarPrioridadRecursiva(Iterator<Paquete> iterador, int prioridadMinima) {
        // Caso base: no quedan paquetes.
        if (!iterador.hasNext()) {
            return 0;
        }

        // obtener el paquete actual
        Paquete paquete = iterador.next();

        // contar recursivamente los restantes
        int cantidadRestante = contarPrioridadRecursiva(iterador, prioridadMinima);

        // suma uno si el paquete actual cumple la condicion
        if (paquete.getPrioridad() >= prioridadMinima) {
            return 1 + cantidadRestante;
        }
        return cantidadRestante;
    }


    // DIVIDE Y VENCERÁS

    public Paquete encontrarPaqueteMayor() {
        Paquete[] paquetes = obtenerPaquetes();
        if (paquetes.length == 0) {
            return null;
        }
        return encontrarMayorPeso(paquetes, 0, paquetes.length - 1);
    }

    private Paquete encontrarMayorPeso(Paquete[] paquetes, int izquierda, int derecha) {
        // Caso base
        if (izquierda == derecha) {
            return paquetes[izquierda];
        }

        int medio = izquierda + (derecha - izquierda) / 2;

        Paquete mayorIzquierda = encontrarMayorPeso(paquetes, izquierda, medio);
        Paquete mayorDerecha = encontrarMayorPeso(paquetes, medio + 1, derecha);

        // Combinar las soluciones de las dos mitades.
        if (mayorIzquierda.getPeso() >= mayorDerecha.getPeso()) {
            return mayorIzquierda;
        }
        return mayorDerecha;
    }


    // BÚSQUEDA BINARIA

    public int buscarPaquetePorCodigo(List<Paquete> paquetesOrdenados, String codigo) {
        if (paquetesOrdenados == null || codigo == null || codigo.isBlank()) {
            return -1;
        }

        return busquedaBinaria(paquetesOrdenados, codigo, 0, paquetesOrdenados.size() - 1);
    }

    private int busquedaBinaria(List<Paquete> paquetes, String codigo, int inicio, int fin) {
        if (inicio > fin) {
            return -1;
        }

        int medio = inicio + (fin - inicio) / 2;
        String codigoMedio = paquetes.get(medio).getCodigo();
        int comparacion = codigoMedio.compareTo(codigo);

        if (comparacion == 0) {
            return medio;
        }
        if (comparacion < 0) {
            return busquedaBinaria(paquetes, codigo, medio + 1, fin);
        }
        return busquedaBinaria(paquetes, codigo, inicio, medio - 1);
    }

}