# QuindíoExpress

## 1. Descripción del proyecto

QuindíoExpress es una aplicación desarrollada en Java que simula la gestión de un centro de distribución de paquetes en diferentes municipios del departamento del Quindío.

El sistema permite registrar paquetes y repartidores, consultar información, organizar los paquetes pendientes, gestionar despachos y mantener un historial de entregas.

## 2. Objetivo

Aplicar los conceptos de estructuras de datos y algoritmos mediante el desarrollo de un sistema que permita administrar paquetes y repartidores de manera organizada.

## 3. Funcionalidades principales

- Registro y búsqueda de paquetes por código.
- Registro y consulta de repartidores.
- Gestión de paquetes pendientes y atención por orden de llegada.
- Organización de paquetes por prioridad, peso y tiempo estimado.
- Selección de paquetes para despacho según prioridad, tiempo estimado y código.
- Consulta de paquetes por municipio.
- Cálculo recursivo del peso total de los paquetes de un municipio.
- Conteo recursivo de paquetes según su prioridad.
- Búsqueda del paquete de mayor peso mediante divide y vencerás.
- Búsqueda binaria de paquetes por código.
- Registro y consulta del historial de entregas.

## 4. Estructura del proyecto

El código fuente se organiza en los siguientes paquetes:

- **Modelo:** contiene las clases que representan los paquetes y los repartidores.
- **Estructuras:** contiene la lista enlazada genérica propia, sus nodos y el iterador.
- **Comparadores:** contiene los criterios utilizados para ordenar los paquetes.
- **Servicio:** contiene la lógica de gestión del centro de distribución.
- **Main.java:** ejecuta el programa y demuestra las operaciones principales.

## 5. Estructuras de datos utilizadas

- **HashMap:** almacena y permite consultar paquetes y repartidores mediante una clave.
- **Queue / ArrayDeque:** gestiona los paquetes pendientes según su orden de llegada.
- **PriorityQueue:** permite seleccionar los paquetes según los criterios de despacho.
- **HashSet:** almacena municipios sin duplicados.
- **TreeSet / SortedSet:** almacena los nombres de los municipios sin repetirlos y los mantiene ordenados alfabéticamente.
- **Lista enlazada propia:** gestiona el historial de entregas mediante nodos enlazados.
- **Comparable y Comparator:** permiten establecer el orden natural de los paquetes y aplicar diferentes criterios de ordenamiento.

## 6. Ejecución del programa

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Configurar el JDK correspondiente.
4. Localizar la clase `Main.java`.
5. Ejecutar el método `main`.
6. Revisar en la consola los resultados de las operaciones demostrativas.

## 7. Datos de prueba

El programa utiliza paquetes y repartidores de prueba con diferentes códigos, pesos, prioridades, tiempos estimados y municipios de destino. Estos datos permiten demostrar las funcionalidades implementadas.

## 8. Documentación de la entrega

El informe académico en PDF contiene el desarrollo de las partes del parcial, las pruebas de escritorio, los diagramas de pila de llamadas, el análisis de complejidad y la documentación del uso de herramientas de inteligencia artificial, según los requisitos de la actividad.

## 9. Autores

- Juan Felipe Ibarra Londoño
- Johan Stiven Pineda Martinez

Parcial 1 - Estructura de datos - Ingenieria de sistemas y computación - Universidad del Quindio
