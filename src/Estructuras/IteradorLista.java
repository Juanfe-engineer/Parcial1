package Estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorLista<T> implements Iterator<T> {

    private Nodo<T> actual;

    IteradorLista(Nodo<T> cabeza) {
        this.actual = cabeza;
    }

    @Override
    public boolean hasNext(){
        return actual != null;
    }

    @Override
    public T next(){
        if(!hasNext()){
            throw new NoSuchElementException(
                    "No hay mas elementos en la lista");
        }
        T dato = actual.getDato();
        actual = actual.getSiguiente();

        return dato;
    }
}
