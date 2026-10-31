
public class Laberinto {
    // Movimientos: arriba, derecha, abajo, izquierda
    private static final int[] DF = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};

    private final int filas, cols;
    private final char[][] tablero;
    private boolean[][] visitado;   // casillas ya exploradas
    private boolean[][] enPila;     // casillas que forman el camino actual
    private final Posicion entrada, salida;
    private Pila camino = new Pila();
    private int retrocesos;
    private boolean terminado, resuelto;

    public Laberinto(char[][] tablero, Posicion entrada, Posicion salida) {
        this.tablero = tablero;
        this.filas = tablero.length;
        this.cols = tablero[0].length;
        this.entrada = entrada;
        this.salida = salida;
        iniciar();
    }

    public void iniciar() {
        camino = new Pila();
        visitado = new boolean[filas][cols];
        enPila = new boolean[filas][cols];
        retrocesos = 0;
        terminado = false;
        resuelto = false;

        camino.push(new Posicion(entrada.fila, entrada.col));
        visitado[entrada.fila][entrada.col] = true;
        enPila[entrada.fila][entrada.col] = true;
    }

    private boolean esValida(int f, int c) {
        return f >= 0 && f < filas && c >= 0 && c < cols
                && tablero[f][c] != '1' && !visitado[f][c];
    }

    public boolean paso() {
        if (terminado) return false;

        Posicion actual = camino.peek();
        boolean avanzo = false;

        while (actual.direccion < 4 && !avanzo) {
            int d = actual.direccion++;
            int nf = actual.fila + DF[d];
            int nc = actual.col + DC[d];
            if (esValida(nf, nc)) {
                visitado[nf][nc] = true;
                enPila[nf][nc] = true;
                camino.push(new Posicion(nf, nc));
                avanzo = true;
                if (nf == salida.fila && nc == salida.col) {
                    terminado = true;
                    resuelto = true;
                }
            }
        }

        if (!avanzo) {              // callejón sin salida -> BACKTRACKING
            Posicion p = camino.pop();
            enPila[p.fila][p.col] = false;
            retrocesos++;
            if (camino.estaVacia()) terminado = true; // sin solución
        }
        return !terminado;
    }

    /** Resuelve todo de golpe (sin animación). */
    public boolean resolver() {
        iniciar();
        while (paso()) { }
        return resuelto;
    }

    // Datos para dibujar y mostrar resultados
    public int getFilas() { return filas; }
    public int getCols() { return cols; }
    public char getCelda(int f, int c) { return tablero[f][c]; }
    public boolean fueVisitada(int f, int c) { return visitado[f][c]; }
    public boolean estaEnPila(int f, int c) { return enPila[f][c]; }
    public Posicion getActual() { return camino.peek(); }
    public boolean isTerminado() { return terminado; }
    public boolean isResuelto() { return resuelto; }
    public int getRetrocesos() { return retrocesos; }
    public int getLongitudCamino() { return camino.tamanio(); }

    // Salida en consola (opcional)
    public void imprimir() {
        char[][] out = new char[filas][cols];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                out[i][j] = tablero[i][j] == '1' ? '#' : ' ';
            }
        }
        for (Posicion p : camino.aArreglo()) out[p.fila][p.col] = '.';
        out[entrada.fila][entrada.col] = 'E';
        out[salida.fila][salida.col] = 'S';

        for (int i = 0; i < filas; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols; j++) sb.append(out[i][j]).append(' ');
            System.out.println(sb);
        }
    }

    public void imprimirCamino() {
        Posicion[] ruta = camino.aArreglo();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ruta.length; i++) {
            sb.append(ruta[i]);
            if (i < ruta.length - 1) sb.append(" -> ");
        }
        System.out.println(sb);
    }
}