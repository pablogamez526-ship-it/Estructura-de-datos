import java.time.LocalDate;

    public class Empleado {
        private int numTrabajador;
        private String nombres;
        private String paterno;
        private String materno;
        private float horasExtra;
        private float sueldoBase;
        private int anioIngreso;

        // Pago fijo por cada hora extra (regla de negocio)
        private static final float PAGO_HORA_EXTRA = 276.5f;
        // Porcentaje de prestación por cada año de antigüedad
        private static final float PORCENTAJE_ANTIGUEDAD = 0.03f;

        public Empleado(int numTrabajador, String nombres, String paterno, String materno,
                        float horasExtra, float sueldoBase, int anioIngreso) {
            this.numTrabajador = numTrabajador;
            this.nombres = nombres;
            this.paterno = paterno;
            this.materno = materno;
            this.horasExtra = horasExtra;
            this.sueldoBase = sueldoBase;
            this.anioIngreso = anioIngreso;
        }

        public int getNumTrabajador() { return numTrabajador; }
        public String getNombres() { return nombres; }
        public String getPaterno() { return paterno; }
        public String getMaterno() { return materno; }
        public float getHorasExtra() { return horasExtra; }
        public float getSueldoBase() { return sueldoBase; }
        public int getAnioIngreso() { return anioIngreso; }

        public int obtenerAntiguedad() {
            int anioActual = LocalDate.now().getYear();
            return anioActual - anioIngreso;
        }

        // Sueldo a pagar este mes:
        // sueldo base + horas extra pagadas a $276.5 c/u + 3% del sueldo base por cada año de antigüedad
        public float calcularSueldo() {
            float pagoHorasExtra = horasExtra * PAGO_HORA_EXTRA;
            float prestacionAntiguedad = sueldoBase * PORCENTAJE_ANTIGUEDAD * obtenerAntiguedad();
            return sueldoBase + pagoHorasExtra + prestacionAntiguedad;
        }

        public String getNombreCompleto() {
            return nombres + " " + paterno + " " + materno;
        }
    }


