import java.io.IOException;

public class Main {

    private static final int NUMERO_GENERACIONES = 10;
    private static final String ARCHIVO_POR_DEFECTO = "poblacion_inicial.csv";

    public static void main(String[] args) {
        String rutaArchivo = (args.length > 0) ? args[0] : ARCHIVO_POR_DEFECTO;

        try {
            JuegoDeLaVida juego = JuegoDeLaVida.cargarDesdeCSV(rutaArchivo);

            System.out.println("Generacion 0 (poblacion inicial):");
            juego.mostrar();
            System.out.println();

            for (int generacion = 1; generacion <= NUMERO_GENERACIONES; generacion++) {
                juego.calcularSiguienteGeneracion();
                System.out.println("Generacion " + generacion + ":");
                juego.mostrar();
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la configuración inicial: " + e.getMessage());
        }
    }
}