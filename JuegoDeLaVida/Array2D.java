
public class Array2D<T> {

    private final Object[][] datos;
    private final int renglones;
    private final int columnas;

    public Array2D(int renglones, int columnas) {
        if (renglones <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("Renglones y columnas deben ser mayores a 0");
        }
        this.renglones = renglones;
        this.columnas = columnas;
        this.datos = new Object[renglones][columnas];
    }

    @SuppressWarnings("unchecked")
    public T obtener(int renglon, int columna) {
        validarPosicion(renglon, columna);
        return (T) datos[renglon][columna];
    }

    public void asignar(int renglon, int columna, T valor) {
        validarPosicion(renglon, columna);
        datos[renglon][columna] = valor;
    }


    public int getRenglones() {
        return renglones;
    }


    public int getColumnas() {
        return columnas;
    }


    public boolean posicionValida(int renglon, int columna) {
        return renglon >= 0 && renglon < renglones && columna >= 0 && columna < columnas;
    }

    private void validarPosicion(int renglon, int columna) {
        if (!posicionValida(renglon, columna)) {
            throw new IndexOutOfBoundsException(
                    "Posición (" + renglon + ", " + columna + ") fuera de rango");
        }
    }
}