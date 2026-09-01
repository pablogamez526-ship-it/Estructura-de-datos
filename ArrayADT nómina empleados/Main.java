import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        // Fuerza la salida de consola a UTF-8 para que tildes y ñ se vean bien
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        NominaICO nomina = new NominaICO("junio.dat");

        nomina.leerArchivo();


        nomina.imprimirNomina();


        Empleado mayor = nomina.mayorAntiguedad();
        Empleado menor = nomina.menorAntiguedad();

        System.out.println();
        System.out.println("===== ANTIGÜEDAD =====");
        System.out.println("Mayor antigüedad: " + mayor.getNombreCompleto()
                + " (" + mayor.obtenerAntiguedad() + " años, ingresó en " + mayor.getAnioIngreso() + ")");
        System.out.println("Menor antigüedad: " + menor.getNombreCompleto()
                + " (" + menor.obtenerAntiguedad() + " años, ingresó en " + menor.getAnioIngreso() + ")");
    }
}
