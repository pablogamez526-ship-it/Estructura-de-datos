public class RegistroDimensiones {

    private ConjuntoADT<String> dimensionesVisitadas;

    public RegistroDimensiones() {
        this.dimensionesVisitadas = new ConjuntoADT<>();
    }

    public void visitarDimension(String nombre) {
        if (!dimensionesVisitadas.contieneElemento(nombre)) {
            dimensionesVisitadas.agregarElemento(nombre);
            System.out.println("-> Nueva dimension visitada: " + nombre);
        } else {
            System.out.println("-> Ya habias visitado \"" + nombre + "\", no se agrega de nuevo.");
        }
    }

    public boolean yaVisito(String nombre) {
        return dimensionesVisitadas.contieneElemento(nombre);
    }

    public int totalVisitadas() {
        return dimensionesVisitadas.longitud();
    }

    public void mostrarDimensiones() {
        System.out.println("Dimensiones visitadas hasta ahora: " + dimensionesVisitadas.getElementos());
    }
}