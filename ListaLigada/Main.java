public class Main {
    public static void main(String[] args) {
        ListaLigada<polloasados> lista = new ListaLigada<>();

        polloasados ranchero = new polloasados("Pollo ranchero", 150.0f, 8, true);
        polloasados enchilado = new polloasados("Pollo enchilado", 160.0f, 8, true);
        polloasados diabla = new polloasados("A la diabla", 170.0f, 8, false);
        polloasados adobado = new polloasados("Pollo adobado", 155.0f, 4, true);
        polloasados BBQ = new polloasados("Pollo BBQ", 180.0f, 8, true);

        System.out.println("Lista nueva");
        System.out.println("¿Vacía? " + lista.estaVacia());
        lista.transversal();

        System.out.println("\nagregar (al final)");
        lista.agregar(ranchero);
        lista.agregar(enchilado);
        lista.agregar(diabla);
        lista.transversal();
        System.out.println("Tamaño: " + lista.getTamanio());

        System.out.println("\nagregarAlFinal");
        lista.agregarAlFinal(new polloasados("Pollo criollo", 145.0f, 6, true));
        lista.transversal();

        System.out.println("\nagregarAlInicio");
        lista.agregarAlInicio(adobado);
        lista.transversal();

        System.out.println("\nagregarDespuesDe(enchilado, BBQ)");
        System.out.println("Resultado: " + lista.agregarDespuesDe(enchilado, BBQ));
        lista.transversal();

        System.out.println("\nagregarAntesDe(diabla, new PolloAsado Teriyaki)");
        System.out.println("Resultado: " + lista.agregarAntesDe(diabla,
                new polloasados("Pollo teriyaki", 165.0f, 6, true)));
        lista.transversal();

        System.out.println("\ninsertarEn(2, Pollo ahumado)");
        lista.insertarEn(2, new polloasados("Pollo ahumado", 175.0f, 6, false));
        lista.transversal();
        System.out.println("Tamaño: " + lista.getTamanio());

        System.out.println("\ncontiene / buscar / obtener");
        System.out.println("¿Contiene diabla? " + lista.contiene(diabla));
        System.out.println("Posición de diabla (buscar): " + lista.buscar(diabla));
        System.out.println("Elemento en índice 0: " + lista.obtener(0));
        System.out.println("Primero: " + lista.obtenerPrimero());
        System.out.println("Último: " + lista.obtenerUltimo());
        System.out.println("Índice inválido (99): " + lista.obtener(99));

        System.out.println("\nactualizar(ranchero -> Pollo ranchero XL)");
        System.out.println("Resultado: " + lista.actualizar(ranchero,
                new polloasados("Pollo ranchero XL", 200.0f, 12, true)));
        lista.transversal();

        System.out.println("\nactualizar de algo que no existe");
        System.out.println("Resultado: " + lista.actualizar(
                new polloasados("No existe", 0f, 0, false), ranchero));

        System.out.println("\neliminar(diabla)");
        System.out.println("Resultado: " + lista.eliminar(diabla));
        lista.transversal();

        System.out.println("\neliminarElPrimero");
        System.out.println("Eliminado: " + lista.eliminarElPrimero());
        lista.transversal();

        System.out.println("\neliminarElFinal");
        System.out.println("Eliminado: " + lista.eliminarElFinal());
        lista.transversal();

        System.out.println("\neliminarEn(1)");
        System.out.println("Eliminado: " + lista.eliminarEn(1));
        lista.transversal();
        System.out.println("Tamaño: " + lista.getTamanio());

        System.out.println("\nvaciar");
        lista.vaciar();
        lista.transversal();
        System.out.println("¿Vacía? " + lista.estaVacia());
        System.out.println("eliminarElPrimero en lista vacía: " + lista.eliminarElPrimero());
    }
}