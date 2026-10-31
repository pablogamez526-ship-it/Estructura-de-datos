public class Pila {
    private static class Nodo {
        Posicion dato;
        Nodo siguiente;
        Nodo(Posicion dato, Nodo siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private Nodo tope;
    private int tamanio;

    public void push(Posicion p) {
        tope = new Nodo(p, tope);
        tamanio++;
    }

    public Posicion pop() {
        if (estaVacia()) return null;
        Posicion p = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return p;
    }

    public Posicion peek() {
        return estaVacia() ? null : tope.dato;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public Posicion[] aArreglo() {
        Posicion[] arr = new Posicion[tamanio];
        int i = tamanio - 1;
        for (Nodo n = tope; n != null; n = n.siguiente) {
            arr[i--] = n.dato;
        }
        return arr;
    }
}
