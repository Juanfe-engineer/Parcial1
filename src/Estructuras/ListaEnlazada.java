package Estructuras;

import java.util.Iterator;

public class ListaEnlazada<T> implements Coleccion<T>, Iterable<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamano;

    public ListaEnlazada(){
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    @Override
    public void add(T elemento){
        Nodo<T> nuevo = new Nodo<>(elemento);

        if(cabeza == null){
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
        tamano++;
    }

    @Override
    public boolean remove(T elemento){
        if(elemento == null || cabeza ==null){
            return false;
        }
        if(cabeza.dato.equals(elemento)){
            cabeza = cabeza.siguiente;
            if(cabeza == null){
                cola = null;
            }
            tamano--;
            return true;
        }
        Nodo<T> actual = cabeza;

        while (actual.siguiente != null){
            if(actual.siguiente.dato.equals(elemento)){
                actual.siguiente = actual.siguiente.siguiente;
                if(actual.siguiente == null){
                    cola = actual;
                }
                tamano--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public T get(int indice){
        if(indice < 0 || indice >= tamano){
            throw new IndexOutOfBoundsException(
                    "Indice fuera de rango: " + indice);
        }
        Nodo<T> actual = cabeza;

        for(int i = 0; i < indice; i++){
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    @Override
    public int size(){
        return tamano;
    }

    @Override
    public boolean isEmpty(){
        return tamano == 0;
    }

    @Override
    public IteradorLista<T> iterador(){
        return new IteradorLista<>(cabeza);
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorLista<>(cabeza);
    }
}
