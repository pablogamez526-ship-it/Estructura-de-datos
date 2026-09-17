public class Main {

    private static Nodo<String> head;

    public static void main(String[] args) {

        Nodo<String> nodoAl = new Nodo<>("Al");
        Nodo<String> nodoB  = new Nodo<>("B");
        Nodo<String> nodoC  = new Nodo<>("C");
        Nodo<String> nodoDe = new Nodo<>("De");
        Nodo<String> nodoMc = new Nodo<>("Mc");
        Nodo<String> nodoZi = new Nodo<>("Zi");

        // Se enlazan manualmente los nodos mediante referencias
        nodoAl.setSiguiente(nodoB);
        nodoB.setSiguiente(nodoC);
        nodoC.setSiguiente(nodoDe);
        nodoDe.setSiguiente(nodoMc);
        nodoMc.setSiguiente(nodoZi);


        head = nodoAl;


        // 2. Estado inicial completo de la lista
        System.out.println("Estado inicial de la lista");
        imprimirLista();

        // 3. Dato del primer nodo

        System.out.println("\nDato del primer nodo");
        System.out.println(head.getDato());


        // 4. Estado completo del último nodo

        System.out.println("\nEstado del ultimo nodo");
        System.out.println(obtenerUltimoNodo());


        // 5. Insertar "Fe" entre "De" y "Mc"
        insertarDespuesDe("De", "Fe");
        System.out.println("\nLista despues de insertar 'Fe' entre 'De' y 'Mc'");
        imprimirLista();


        // 6. Insertar "Zz" al final

        insertarAlFinal("Zz");
        System.out.println("\nLista despues de insertar 'Zz' al final");
        imprimirLista();


        insertarAlInicio("Aa");
        System.out.println("\nLista final despues de insertar 'Aa' al inicio");
        imprimirLista();
    }


    private static void imprimirLista() {
        Nodo<String> actual = head;
        int posicion = 0;
        while (actual != null) {
            System.out.println("[" + posicion + "] " + actual);
            actual = actual.getSiguiente();
            posicion++;
        }
    }
    //Ultimo Nodo
    private static Nodo<String> obtenerUltimoNodo() {
        Nodo<String> actual = head;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    // Inserta un nuevo nodo/Dato despues del nodo que contiene datoBuscado
    private static void insertarDespuesDe(String datoBuscado, String nuevoDato) {
        Nodo<String> actual = head;
        while (actual != null && !actual.getDato().equals(datoBuscado)) {
            actual = actual.getSiguiente();
        }
        if (actual != null) {
            Nodo<String> nuevoNodo = new Nodo<>(nuevoDato);
            nuevoNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevoNodo);
        }
    }

    // Inserta un nuevo nodo/Dato  al final de la lista
    private static void insertarAlFinal(String nuevoDato) {
        Nodo<String> nuevoNodo = new Nodo<>(nuevoDato);
        Nodo<String> ultimo = obtenerUltimoNodo();
        ultimo.setSiguiente(nuevoNodo);
    }

    // Inserta un nuevo nodo/Dato" al inicio de la lista
    private static void insertarAlInicio(String nuevoDato) {
        Nodo<String> nuevoNodo = new Nodo<>(nuevoDato);
        nuevoNodo.setSiguiente(head);
        head = nuevoNodo;
    }
}