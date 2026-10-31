import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {


    private static String nombreArchivo(int n) {
        return n == 1 ? "entrada.lab" : "entrada" + n + ".lab";
    }

    private static void mostrarMenu() {
        System.out.println("=== LABERINTOS ===");
        for (int n = 1; n <= 20; n++) {
            if (new File(nombreArchivo(n)).exists()) {
                System.out.println("  " + n + " -> " + nombreArchivo(n));
            }
        }
        System.out.println("  0 -> Salir");
    }

    private static void abrirVentana(String archivo) {
        Laberinto lab;
        try {
            lab = LectorLaberinto.leer(archivo);
        } catch (IOException | RuntimeException e) {
            System.out.println("No se pudo leer el archivo " + archivo + ": " + e.getMessage());
            return;
        }
        System.out.println("Abriendo " + archivo + "...");
        SwingUtilities.invokeLater(() -> new VentanaLaberinto(lab, archivo));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println();
            mostrarMenu();
            System.out.print("Elige un número: ");

            String texto = sc.hasNextLine() ? sc.nextLine().trim() : "0";
            try {
                opcion = Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Escribe solo un número.");
                continue;
            }

            if (opcion == 0) {
                System.out.println("Adiós.");
            } else if (opcion < 0) {
                System.out.println("Número no válido.");
            } else {
                abrirVentana(nombreArchivo(opcion));
            }
        }
        sc.close();
    }
}