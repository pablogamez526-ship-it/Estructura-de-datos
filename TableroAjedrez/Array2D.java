public class Array2D<T> {

    private Object[][] datos;
    private int filas;
    private int columnas;

    public Array2D(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new Object[filas][columnas];
    }

    public void set(int fila, int columna, T valor) {
        datos[fila][columna] = valor;
    }

    @SuppressWarnings("unchecked")
    public T get(int fila, int columna) {
        return (T) datos[fila][columna];
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}