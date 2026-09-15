public class Main {
    public static void main(String[] args) {

        RegistroDimensiones jugador = new RegistroDimensiones();

        System.out.println("Prueba 1: visitar dimensiones nuevas");
        jugador.visitarDimension("Overworld");
        jugador.visitarDimension("Nether");
        jugador.visitarDimension("End");

        System.out.println();
        System.out.println("Prueba 2: visitar una dimension repetida");
        jugador.visitarDimension("Nether"); // ya la habia visitado, no debe duplicarse

        System.out.println();
        System.out.println("Prueba 3: consultar si ya visito una dimension");
        System.out.println("¿Ya visito el Nether? " + jugador.yaVisito("Nether"));
        System.out.println("¿Ya visito el Overworld? " + jugador.yaVisito("Overworld"));

        System.out.println();
        System.out.println("Prueba 4: total de dimensiones distintas visitadas");
        System.out.println("Total: " + jugador.totalVisitadas());

        System.out.println();
        System.out.println("Prueba 5: mostrar todas las dimensiones");
        jugador.mostrarDimensiones();
    }
}