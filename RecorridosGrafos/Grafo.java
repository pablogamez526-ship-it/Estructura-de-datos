public class Grafo {
    private final char[] vertices;
    private final boolean[][] ady;

    public Grafo(char[] vertices) {
        this.vertices = vertices;
        this.ady = new boolean[vertices.length][vertices.length];
    }

    private int indice(char v) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == v) return i;
        }
        return -1;
    }

    public void agregar_arista(char a, char b) {
        int i = indice(a);
        int j = indice(b);
        ady[i][j] = true;
        ady[j][i] = true;
    }


    public String bfs(char inicio) {
        boolean[] descubierto = new boolean[vertices.length];
        Cola<Character> cola = new Cola<>();
        StringBuilder visitados = new StringBuilder();

        System.out.println("=== BFS desde " + inicio + " ===");
        descubierto[indice(inicio)] = true;
        cola.encolar(inicio);
        System.out.println("Inicio  | visitados = [] | cola = " + cola);

        while (!cola.esta_vacia()) {
            char actual = cola.desencolar();
            if (visitados.length() > 0) visitados.append(", ");
            visitados.append(actual);

            int i = indice(actual);
            for (int j = 0; j < vertices.length; j++) {   // orden alfabético
                if (ady[i][j] && !descubierto[j]) {
                    descubierto[j] = true;
                    cola.encolar(vertices[j]);
                }
            }
            System.out.println("Actual " + actual + " | visitados = [" + visitados + "] | cola = " + cola);
        }
        return visitados.toString().replace(", ", " -> ");
    }

    public String dfs(char inicio) {
        boolean[] marcado = new boolean[vertices.length];
        Pila<Character> pila = new Pila<>();
        StringBuilder lista = new StringBuilder();

        System.out.println("=== DFS desde " + inicio + " ===");
        marcado[indice(inicio)] = true;
        pila.apilar(inicio);
        System.out.println("Inicio  | lista = [] | pila = " + pila);

        while (!pila.esta_vacia()) {
            char actual = pila.desapilar();
            if (lista.length() > 0) lista.append(", ");
            lista.append(actual);

            int i = indice(actual);
            for (int j = 0; j < vertices.length; j++) {   // orden alfabético
                if (ady[i][j] && !marcado[j]) {
                    marcado[j] = true;
                    pila.apilar(vertices[j]);
                }
            }
            System.out.println("Actual " + actual + " | lista = [" + lista + "] | pila = " + pila);
        }
        return lista.toString().replace(", ", " -> ");
    }
}