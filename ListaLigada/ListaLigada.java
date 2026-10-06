public class ListaLigada<T> {
    private Nodo<T> head;

    public ListaLigada() {
        this.head = null;
    }

    // ------------------------------------------------------------------
    // Consultas básicas
    // ------------------------------------------------------------------

    /** @return true si la lista no tiene elementos */
    public boolean estaVacia() {
        return head == null;
    }

    /** @return número de elementos de la lista */
    public int getTamanio() {
        int contador = 0;
        Nodo<T> actual = head;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    /** Recorre la lista e imprime cada dato separado por | */
    public void transversal() {
        if (head == null) {
            System.out.println("Vacia");
        } else {
            Nodo<T> actual = head;
            while (actual != null) {
                System.out.print("|" + actual.getDato());
                actual = actual.getSiguiente();
            }
            System.out.println("|");
        }
    }

    // ------------------------------------------------------------------
    // Agregar
    // ------------------------------------------------------------------

    /** Agrega un elemento al final de la lista (igual que agregarAlFinal). */
    public void agregar(T dato) {
        agregarAlFinal(dato);
    }

    /** Agrega un nodo al final de la lista, entrando por head. */
    public void agregarAlFinal(T dato) {
        if (head == null) {
            this.head = new Nodo<>(dato);
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(new Nodo<>(dato));
        }
    }

    /** Agrega un elemento al inicio de la lista. */
    public void agregarAlInicio(T dato) {
        this.head = new Nodo<>(dato, head);
    }

    /**
     * Agrega valor justo después del nodo que contiene referencia.
     * @return true si se encontró la referencia y se agregó; false si no
     */
    public boolean agregarDespuesDe(T referencia, T valor) {
        Nodo<T> actual = buscarNodo(referencia);
        if (actual == null) {
            return false;
        }
        actual.setSiguiente(new Nodo<>(valor, actual.getSiguiente()));
        return true;
    }

    /**
     * Agrega valor justo antes del nodo que contiene referencia.
     * @return true si se encontró la referencia y se agregó; false si no
     */
    public boolean agregarAntesDe(T referencia, T valor) {
        if (head == null) {
            return false;
        }
        // La referencia está en el primer nodo: el nuevo pasa a ser head
        if (head.getDato().equals(referencia)) {
            agregarAlInicio(valor);
            return true;
        }
        Nodo<T> anterior = head;
        while (anterior.getSiguiente() != null
                && !anterior.getSiguiente().getDato().equals(referencia)) {
            anterior = anterior.getSiguiente();
        }
        if (anterior.getSiguiente() == null) {
            return false; // no se encontró
        }
        anterior.setSiguiente(new Nodo<>(valor, anterior.getSiguiente()));
        return true;
    }

    /**
     * Inserta un elemento en la posición indicada (la primera posición es 0).
     * Los elementos desde esa posición se recorren un lugar.
     * @return true si el índice es válido (0 a tamaño); false si no
     */
    public boolean insertarEn(int indice, T dato) {
        if (indice < 0 || indice > getTamanio()) {
            return false;
        }
        if (indice == 0) {
            agregarAlInicio(dato);
            return true;
        }
        Nodo<T> anterior = head;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }
        anterior.setSiguiente(new Nodo<>(dato, anterior.getSiguiente()));
        return true;
    }

    // ------------------------------------------------------------------
    // Buscar / obtener / actualizar
    // ------------------------------------------------------------------

    /** @return true si la lista contiene el dato (usa equals) */
    public boolean contiene(T dato) {
        return buscarNodo(dato) != null;
    }

    /** Busca un elemento. @return su posición (desde 0), o -1 si no está */
    public int buscar(T dato) {
        int indice = 0;
        Nodo<T> actual = head;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return indice;
            }
            indice++;
            actual = actual.getSiguiente();
        }
        return -1;
    }

    /** @return el dato en la posición indicada, o null si el índice no es válido */
    public T obtener(int indice) {
        if (indice < 0) {
            return null;
        }
        Nodo<T> actual = head;
        int i = 0;
        while (actual != null && i < indice) {
            actual = actual.getSiguiente();
            i++;
        }
        return (actual == null) ? null : actual.getDato();
    }

    /** @return el primer elemento, o null si la lista está vacía */
    public T obtenerPrimero() {
        return (head == null) ? null : head.getDato();
    }

    /** @return el último elemento, o null si la lista está vacía */
    public T obtenerUltimo() {
        if (head == null) {
            return null;
        }
        Nodo<T> actual = head;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * Reemplaza el dato aBuscar por nuevoValor.
     * @return true si se encontró y se actualizó; false si no
     */
    public boolean actualizar(T aBuscar, T nuevoValor) {
        Nodo<T> actual = buscarNodo(aBuscar);
        if (actual == null) {
            return false;
        }
        actual.setDato(nuevoValor);
        return true;
    }

    // ------------------------------------------------------------------
    // Eliminar
    // ------------------------------------------------------------------

    /**
     * Elimina la primera aparición del dato.
     * @return true si se eliminó; false si no se encontró
     */
    public boolean eliminar(T dato) {
        if (head == null) {
            return false;
        }
        if (head.getDato().equals(dato)) {
            head = head.getSiguiente();
            return true;
        }
        Nodo<T> anterior = head;
        while (anterior.getSiguiente() != null
                && !anterior.getSiguiente().getDato().equals(dato)) {
            anterior = anterior.getSiguiente();
        }
        if (anterior.getSiguiente() == null) {
            return false;
        }
        // "Saltamos" el nodo a eliminar
        anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
        return true;
    }

    /** Elimina y regresa el primer elemento; null si la lista está vacía. */
    public T eliminarElPrimero() {
        if (head == null) {
            return null;
        }
        T dato = head.getDato();
        head = head.getSiguiente();
        return dato;
    }

    /** Elimina y regresa el último elemento; null si la lista está vacía. */
    public T eliminarElFinal() {
        if (head == null) {
            return null;
        }
        if (head.getSiguiente() == null) { // un solo elemento
            T dato = head.getDato();
            head = null;
            return dato;
        }
        Nodo<T> anterior = head;
        while (anterior.getSiguiente().getSiguiente() != null) {
            anterior = anterior.getSiguiente();
        }
        T dato = anterior.getSiguiente().getDato();
        anterior.setSiguiente(null);
        return dato;
    }

    /** Elimina y regresa el elemento en la posición indicada; null si el índice no es válido. */
    public T eliminarEn(int indice) {
        if (indice < 0 || indice >= getTamanio()) {
            return null;
        }
        if (indice == 0) {
            return eliminarElPrimero();
        }
        Nodo<T> anterior = head;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }
        T dato = anterior.getSiguiente().getDato();
        anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
        return dato;
    }

    /** Elimina todos los elementos. */
    public void vaciar() {
        this.head = null;
    }

    // ------------------------------------------------------------------
    // Auxiliar privado
    // ------------------------------------------------------------------

    /** @return el nodo que contiene el dato, o null si no existe */
    private Nodo<T> buscarNodo(T dato) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}