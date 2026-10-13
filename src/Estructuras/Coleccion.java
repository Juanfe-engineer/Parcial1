package Estructuras;

public interface Coleccion<T> {

    void add(T elemento);

    boolean remove(T elemento);

    T get(int indice);

    int size();

    boolean isEmpty();

    IteradorLista<T> iterador();
}
