public class ArrayADT<T> {

    private Object[] datos;
    private int tamanio;
    private int cantidad;

    public ArrayADT(int tamanio) {
        this.tamanio = tamanio;
        this.datos = new Object[tamanio];
        this.cantidad = 0;
    }

    public void agregar(T elemento) {
        if (cantidad < tamanio) {
            datos[cantidad] = elemento;
            cantidad++;
        } else {
            System.out.println("El arreglo ya está lleno, no se puede agregar más.");
        }
    }


    public T obtener(int indice) {
        if (indice >= 0 && indice < cantidad) {
            return (T) datos[indice];
        }
        return null;
    }

    public void modificar(int indice, T elemento) {
        if (indice >= 0 && indice < cantidad) {
            datos[indice] = elemento;
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getTamanio() {
        return tamanio;
    }
}
