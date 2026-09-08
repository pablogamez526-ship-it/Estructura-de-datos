import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JuegoDeLaVida {

    private Array2D<Integer> tablero;

    public JuegoDeLaVida(Array2D<Integer> tablero) {
        this.tablero = tablero;
    }

    public static JuegoDeLaVida cargarDesdeCSV(String rutaArchivo) throws IOException {
        List<int[]> filas = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] valores = linea.split(",");
                int[] fila = new int[valores.length];
                for (int j = 0; j < valores.length; j++) {
                    fila[j] = Integer.parseInt(valores[j].trim());
                }
                filas.add(fila);
            }
        }

        int renglones = filas.size();
        if (renglones == 0) {
            throw new IOException("El archivo CSV está vacío");
        }
        int columnas = filas.get(0).length;

        if (renglones < 10 || columnas < 10) {
            throw new IllegalArgumentException(
                    "La configuración inicial debe tener al menos 10 renglones y 10 columnas. "
                            + "Se leyeron " + renglones + " renglones y " + columnas + " columnas.");
        }

        Array2D<Integer> matriz = new Array2D<>(renglones, columnas);
        for (int i = 0; i < renglones; i++) {
            int[] fila = filas.get(i);
            if (fila.length != columnas) {
                throw new IllegalArgumentException(
                        "Todos los renglones del CSV deben tener el mismo número de columnas");
            }
            for (int j = 0; j < columnas; j++) {
                matriz.asignar(i, j, fila[j]);
            }
        }

        return new JuegoDeLaVida(matriz);
    }

    private int contarVecinosVivos(int renglon, int columna) {
        int vivos = 0;
        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) {
                    continue; // no contar la propia celda
                }
                int vecinoRenglon = renglon + di;
                int vecinoColumna = columna + dj;
                if (tablero.posicionValida(vecinoRenglon, vecinoColumna)) {
                    vivos += tablero.obtener(vecinoRenglon, vecinoColumna);
                }
            }
        }
        return vivos;
    }

    public void calcularSiguienteGeneracion() {
        int renglones = tablero.getRenglones();
        int columnas = tablero.getColumnas();
        Array2D<Integer> nuevoTablero = new Array2D<>(renglones, columnas);

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);
                int estadoActual = tablero.obtener(i, j);
                int nuevoEstado;

                if (estadoActual == 1) {
                    if (vecinosVivos == 2 || vecinosVivos == 3) {
                        nuevoEstado = 1; // sobrevive
                    } else {
                        nuevoEstado = 0; // muere por soledad o sobrepoblación
                    }
                } else {
                    nuevoEstado = (vecinosVivos == 3) ? 1 : 0; // nace o sigue muerta
                }

                nuevoTablero.asignar(i, j, nuevoEstado);
            }
        }

        this.tablero = nuevoTablero;
    }

    public void mostrar() {
        for (int i = 0; i < tablero.getRenglones(); i++) {
            StringBuilder renglon = new StringBuilder();
            for (int j = 0; j < tablero.getColumnas(); j++) {
                renglon.append(tablero.obtener(i, j) == 1 ? "O " : ". ");
            }
            System.out.println(renglon.toString());
        }
    }

    public Array2D<Integer> getTablero() {
        return tablero;
    }
}