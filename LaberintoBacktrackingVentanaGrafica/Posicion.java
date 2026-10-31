
public class Posicion {
    public final int fila;
    public final int col;
    public int direccion; // siguiente dirección por probar (0..3)

    public Posicion(int fila, int col) {
        this.fila = fila;
        this.col = col;
        this.direccion = 0;
    }

    @Override
    public String toString() {
        return "(" + fila + "," + col + ")";
    }
}
