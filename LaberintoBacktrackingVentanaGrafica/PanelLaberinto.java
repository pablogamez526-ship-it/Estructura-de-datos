import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelLaberinto extends JPanel {
    private static final Color PARED = Color.BLACK;
    private static final Color LIBRE = Color.WHITE;
    private static final Color DESCARTADA = new Color(255, 213, 158); // naranja claro
    private static final Color EN_CAMINO = new Color(123, 201, 111);  // verde
    private static final Color ACTUAL = new Color(255, 221, 0);       // amarillo
    private static final Color ENTRADA = new Color(52, 120, 246);     // azul
    private static final Color SALIDA = new Color(220, 53, 69);       // rojo

    private final Laberinto lab;

    public PanelLaberinto(Laberinto lab) {
        this.lab = lab;
        int tam = 40;
        setPreferredSize(new Dimension(lab.getCols() * tam, lab.getFilas() * tam));
        setBackground(Color.LIGHT_GRAY);
    }

    private Color colorDe(int i, int j, Posicion actual) {
        char c = lab.getCelda(i, j);
        if (c == '1') return PARED;
        if (c == 'E') return ENTRADA;
        if (c == 'S') return SALIDA;
        if (actual != null && actual.fila == i && actual.col == j) return ACTUAL;
        if (lab.estaEnPila(i, j)) return EN_CAMINO;
        if (lab.fueVisitada(i, j)) return DESCARTADA;
        return LIBRE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int filas = lab.getFilas(), cols = lab.getCols();
        int tam = Math.min(getWidth() / cols, getHeight() / filas);
        if (tam < 1) return;
        int offX = (getWidth() - tam * cols) / 2;
        int offY = (getHeight() - tam * filas) / 2;
        Posicion actual = lab.getActual();

        g.setFont(new Font("SansSerif", Font.BOLD, Math.max(10, tam / 2)));
        FontMetrics fm = g.getFontMetrics();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                int x = offX + j * tam;
                int y = offY + i * tam;
                g.setColor(colorDe(i, j, actual));
                g.fillRect(x, y, tam, tam);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, tam, tam);

                char c = lab.getCelda(i, j);
                if (c == 'E' || c == 'S') {
                    g.setColor(Color.WHITE);
                    String t = String.valueOf(c);
                    g.drawString(t, x + (tam - fm.stringWidth(t)) / 2,
                            y + (tam + fm.getAscent() - fm.getDescent()) / 2);
                }
            }
        }
    }
}