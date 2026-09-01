import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

    public class NominaICO {

        private ArrayADT<Empleado> datos;
        private String rutaArchivo;

        public NominaICO(String rutaArchivo) {
            this.rutaArchivo = rutaArchivo;
            int totalLineas = contarLineas(rutaArchivo);
            this.datos = new ArrayADT<>(totalLineas);
        }

        private int contarLineas(String ruta) {
            int contador = 0;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(ruta), StandardCharsets.UTF_8))) {
                br.readLine(); // se salta la línea de encabezado
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        contador++;
                    }
                }
            } catch (IOException e) {
                System.out.println("No se pudo contar las líneas del archivo: " + e.getMessage());
            }
            return contador;
        }

        public void leerArchivo() {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(rutaArchivo), StandardCharsets.UTF_8))) {
                br.readLine(); // se salta la línea de encabezado
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;

                    String[] campos = linea.split(",");

                    int numTrabajador = Integer.parseInt(campos[0].trim());
                    String nombres = campos[1].trim();
                    String paterno = campos[2].trim();
                    String materno = campos[3].trim();
                    float horasExtra = Float.parseFloat(campos[4].trim());
                    float sueldoBase = Float.parseFloat(campos[5].trim());
                    int anioIngreso = Integer.parseInt(campos[6].trim());

                    Empleado empleado = new Empleado(numTrabajador, nombres, paterno, materno,
                            horasExtra, sueldoBase, anioIngreso);
                    datos.agregar(empleado);
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }

        // Devuelve al empleado con MAYOR antigüedad (el que ingresó hace más años)
        public Empleado mayorAntiguedad() {
            Empleado mayor = datos.obtener(0);
            for (int i = 1; i < datos.getCantidad(); i++) {
                Empleado actual = datos.obtener(i);
                if (actual.obtenerAntiguedad() > mayor.obtenerAntiguedad()) {
                    mayor = actual;
                }
            }
            return mayor;
        }

        // Devuelve al empleado con MENOR antigüedad (el más reciente)
        public Empleado menorAntiguedad() {
            Empleado menor = datos.obtener(0);
            for (int i = 1; i < datos.getCantidad(); i++) {
                Empleado actual = datos.obtener(i);
                if (actual.obtenerAntiguedad() < menor.obtenerAntiguedad()) {
                    menor = actual;
                }
            }
            return menor;
        }

        // Imprime a todos los empleados con su sueldo a pagar este mes
        public void imprimirNomina() {
            System.out.println("===== NÓMINA DEL MES =====");
            for (int i = 0; i < datos.getCantidad(); i++) {
                Empleado e = datos.obtener(i);
                System.out.println("No. trabajador : " + e.getNumTrabajador());
                System.out.println("Nombre         : " + e.getNombreCompleto());
                System.out.println("Horas extra    : " + e.getHorasExtra());
                System.out.println("Sueldo base    : $" + e.getSueldoBase());
                System.out.println("Año de ingreso : " + e.getAnioIngreso());
                System.out.println("Antigüedad     : " + e.obtenerAntiguedad() + " años");
                System.out.printf("Sueldo a pagar : $%.2f%n", e.calcularSueldo());
                System.out.println("--------------------------");
            }
        }
    }

