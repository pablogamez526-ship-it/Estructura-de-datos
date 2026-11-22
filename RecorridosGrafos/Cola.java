public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public boolean esta_vacia() { return frente == null; }

    public int get_tamanio() { return tamanio; }

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (esta_vacia()) {
            frente = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        tamanio++;
    }

    public T desencolar() {
        if (esta_vacia()) return null;
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) fin = null;
        tamanio--;
        return dato;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> aux = frente;
        while (aux != null) {
            sb.append(aux.getDato());
            if (aux.getSiguiente() != null) sb.append(", ");
            aux = aux.getSiguiente();
        }
        return sb.append("]").toString();
    }
}
