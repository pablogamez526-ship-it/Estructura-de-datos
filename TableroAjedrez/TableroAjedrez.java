
public class TableroAjedrez {

    // Piezas blancas (símbolos "huecos")
    private static final char REY_BLANCO    = '\u2654';
    private static final char REINA_BLANCA  = '\u2655';
    private static final char TORRE_BLANCA  = '\u2656';
    private static final char ALFIL_BLANCO  = '\u2657';
    private static final char CABALLO_BLANCO = '\u2658';
    private static final char PEON_BLANCO   = '\u2659';

    // Piezas negras (símbolos "rellenos")
    private static final char REY_NEGRO     = '\u265A';
    private static final char REINA_NEGRA   = '\u265B';
    private static final char TORRE_NEGRA   = '\u265C';
    private static final char ALFIL_NEGRO   = '\u265D';
    private static final char CABALLO_NEGRO = '\u265E';
    private static final char PEON_NEGRO    = '\u265F';

    private static final char VACIO = ' ';

    private Array2D<Character> tablero;

    public TableroAjedrez() {
        tablero = new Array2D<>(8, 8);
        inicializar();
    }


    private void inicializar() {
        char[] filaNegras = {TORRE_NEGRA, CABALLO_NEGRO, ALFIL_NEGRO, REINA_NEGRA,
                REY_NEGRO, ALFIL_NEGRO, CABALLO_NEGRO, TORRE_NEGRA};
        char[] filaBlancas = {TORRE_BLANCA, CABALLO_BLANCO, ALFIL_BLANCO, REINA_BLANCA,
                REY_BLANCO, ALFIL_BLANCO, CABALLO_BLANCO, TORRE_BLANCA};

        for (int col = 0; col < 8; col++) {
            tablero.set(0, col, filaNegras[col]);   // fila 8: piezas negras
            tablero.set(1, col, PEON_NEGRO);        // fila 7: peones negros

            for (int fila = 2; fila <= 5; fila++) {  // filas 6,5,4,3: vacías
                tablero.set(fila, col, VACIO);
            }

            tablero.set(6, col, PEON_BLANCO);       // fila 2: peones blancos
            tablero.set(7, col, filaBlancas[col]);  // fila 1: piezas blancas
        }
    }


    public void mostrar() {
        for (int fila = 0; fila < 8; fila++) {
            int numeroFila = 8 - fila; // fila 0 -> "8", fila 7 -> "1"
            System.out.print(numeroFila + "  ");
            for (int col = 0; col < 8; col++) {
                System.out.print(tablero.get(fila, col) + "  ");
            }
            System.out.println();
        }
        System.out.println("   a   b   c  d   e   f   g   h");
    }
}
