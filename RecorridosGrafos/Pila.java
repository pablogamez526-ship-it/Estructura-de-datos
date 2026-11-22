public class Pila<T> {
    private Nodo<T> tope;
    private int tamanio;

    public Pila() {
        tope = null;
        tamanio = 0;
    }

    public boolean esta_vacia() { return tope == null; }

    public int get_tamanio() { return tamanio; }

    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(tope);
        tope = nuevo;
        tamanio++;
    }

    public T desapilar() {
        if (esta_vacia()) return null;
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        tamanio--;
        return dato;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> aux = tope;
        while (aux != null) {
            sb.insert(0, aux.getDato() + (aux == tope ? "" : ", "));
            aux = aux.getSiguiente();
        }
        return "[" + sb + "]";
    }
}
