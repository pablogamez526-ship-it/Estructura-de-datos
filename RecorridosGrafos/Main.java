public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo("ABCDEFGHIJ".toCharArray());

        g.agregar_arista('A', 'B');
        g.agregar_arista('A', 'C');
        g.agregar_arista('B', 'D');
        g.agregar_arista('B', 'E');
        g.agregar_arista('C', 'F');
        g.agregar_arista('C', 'G');
        g.agregar_arista('D', 'H');
        g.agregar_arista('E', 'H');
        g.agregar_arista('E', 'I');
        g.agregar_arista('F', 'I');
        g.agregar_arista('F', 'J');
        g.agregar_arista('G', 'J');
        g.agregar_arista('H', 'I');
        g.agregar_arista('I', 'J');

        String secuenciaBFS = g.bfs('A');
        System.out.println("Secuencia BFS: " + secuenciaBFS);
        System.out.println();

        String secuenciaDFS = g.dfs('A');
        System.out.println("Secuencia DFS: " + secuenciaDFS);
    }
}