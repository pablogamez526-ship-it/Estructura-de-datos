import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;


public class VentanaLaberinto extends JFrame {
    private final Laberinto lab;
    private final PanelLaberinto panel;
    private final JLabel estado = new JLabel(" ");
    private final JButton btnPausa = new JButton("Pausa");
    private final JButton btnReiniciar = new JButton("Reiniciar");
    private final JSlider velocidad = new JSlider(1, 10, 5); // 10 = más rápido
    private final Timer timer;

    public VentanaLaberinto(Laberinto lab, String archivo) {
        super("Laberinto - " + archivo);
        this.lab = lab;
        this.panel = new PanelLaberinto(lab);

        timer = new Timer(retardo(), e -> siguientePaso());

        JPanel controles = new JPanel(new FlowLayout());
        controles.add(btnPausa);
        controles.add(btnReiniciar);
        controles.add(new JLabel("Velocidad:"));
        controles.add(velocidad);

        JPanel abajo = new JPanel(new BorderLayout());
        abajo.add(estado, BorderLayout.NORTH);
        abajo.add(controles, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);

        btnPausa.addActionListener(e -> alternarPausa());
        btnReiniciar.addActionListener(e -> reiniciar());
        velocidad.addChangeListener(e -> timer.setDelay(retardo()));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        actualizarEstado();
        timer.start();
    }

    private int retardo() {
        return 550 - velocidad.getValue() * 50; // de 500 ms a 50 ms
    }

    private void siguientePaso() {
        boolean sigue = lab.paso();
        actualizarEstado();
        panel.repaint();
        if (!sigue) {
            timer.stop();
            btnPausa.setEnabled(false);
        }
    }

    private void alternarPausa() {
        if (timer.isRunning()) {
            timer.stop();
            btnPausa.setText("Reanudar");
        } else {
            timer.start();
            btnPausa.setText("Pausa");
        }
    }

    private void reiniciar() {
        timer.stop();
        lab.iniciar();
        btnPausa.setEnabled(true);
        btnPausa.setText("Pausa");
        actualizarEstado();
        panel.repaint();
        timer.start();
    }

    private void actualizarEstado() {
        String texto = "Casillas en el camino: " + lab.getLongitudCamino()
                + "   |   Retrocesos: " + lab.getRetrocesos();
        if (lab.isTerminado()) {
            texto += lab.isResuelto() ? "   |   ¡Salida encontrada!" : "   |   Sin solución";
        }
        estado.setText(" " + texto);
    }
}