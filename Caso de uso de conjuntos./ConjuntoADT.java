import java.util.ArrayList;

public class ConjuntoADT<T> {
    private ArrayList<T> elementos;

    public ConjuntoADT(){
        this.elementos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ConjuntoADT{" +
                "elementos=" + elementos +
                '}';
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<T> elementos) {
        this.elementos = elementos;
    }

    public int longitud(){
        return elementos.size();
    }

    public void agregarElemento(T elemento){
        // Un conjunto no permite elementos repetidos
        if (!contieneElemento(elemento)) {
            elementos.add(elemento);
        }
    }

    public boolean contieneElemento(T elemento){
        return elementos.contains(elemento);
    }

    public void eliminarElemento(T elemento){
        elementos.remove(elemento);
    }

    public ConjuntoADT<T> union(ConjuntoADT<T> otro){
        ConjuntoADT<T> resultado = new ConjuntoADT<>();
        for (T elemento : this.elementos) {
            resultado.agregarElemento(elemento);
        }
        for (T elemento : otro.getElementos()) {
            resultado.agregarElemento(elemento);
        }
        return resultado;
    }

    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro){
        ConjuntoADT<T> resultado = new ConjuntoADT<>();
        for (T elemento : this.elementos) {
            if (otro.contieneElemento(elemento)) {
                resultado.agregarElemento(elemento);
            }
        }
        return resultado;
    }

    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro){
        ConjuntoADT<T> resultado = new ConjuntoADT<>();
        for (T elemento : this.elementos) {
            if (!otro.contieneElemento(elemento)) {
                resultado.agregarElemento(elemento);
            }
        }
        return resultado;
    }

    public boolean esSubconjuntoDe(ConjuntoADT<T> otro){
        for (T elemento : this.elementos) {
            if (!otro.contieneElemento(elemento)) {
                return false;
            }
        }
        return true;
    }

}