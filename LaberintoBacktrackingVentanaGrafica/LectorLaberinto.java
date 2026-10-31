import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorLaberinto {

    public static Laberinto leer(String ruta) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            int filas = Integer.parseInt(br.readLine().trim());
            int cols = Integer.parseInt(br.readLine().trim());
            char[][] tablero = new char[filas][cols];
            Posicion entrada = null;
            Posicion salida = null;

            for (int i = 0; i < filas; i++) {
                String[] partes = br.readLine().trim().split(",");
                for (int j = 0; j < cols; j++) {
                    char c = partes[j].trim().toUpperCase().charAt(0);
                    tablero[i][j] = c;
                    if (c == 'E') entrada = new Posicion(i, j);
                    if (c == 'S') salida = new Posicion(i, j);
                }
            }

            if (entrada == null || salida == null) {
                throw new IOException("El laberinto debe tener una E (entrada) y una S (salida).");
            }
            return new Laberinto(tablero, entrada, salida);
        }
    }
}