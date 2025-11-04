package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;

import UserInterface.CustomerControl.SebButton;


public class HistorialPanel extends JPanel {
    private JTextPane textPane;
    private SebButton btnRegresar;
    private String usuario;
    private Image backgroundImage;

    // Clase interna para la pila
    private class Pila {
        private Nodo tope;
        private int maximo; // Este atributo es opcional y se puede usar para otros propósitos

        // Clase interna Nodo
        private class Nodo {
            String dato;
            Nodo siguiente;

            Nodo(String dato, Nodo siguiente) {
                this.dato = dato;
                this.siguiente = siguiente;
            }
        }

        // Constructor de Pila
        Pila() {
            tope = null;
            maximo = 0; // Inicialmente el máximo es 0
        }

        // Añade un dato a la pila
        void apilar(String dato) {
            tope = new Nodo(dato, tope);
            maximo++; // Incrementa el tamaño máximo de la pila
        }

        // Elimina y devuelve el dato en la cima de la pila
        String desapilar() {
            if (tope == null) {
                throw new RuntimeException("Pila vacía");
            }
            String dato = tope.dato;
            tope = tope.siguiente;
            maximo--; // Decrementa el tamaño máximo de la pila
            return dato;
        }

        // Verifica si la pila está vacía
        boolean estaVacia() {
            return tope == null;
        }

        // Devuelve el tamaño actual de la pila
        int tamaño() {
            return maximo;
        }
    }

    public HistorialPanel(String usuario) {
        this.usuario = usuario;
        setLayout(new BorderLayout());

        // Cargar la imagen de fondo
        ImageIcon imagenFondo = new ImageIcon("src\\Resource\\Img\\Historial2.png");
        backgroundImage = imagenFondo.getImage();

        // Crear el JTextPane
        textPane = new JTextPane();
        textPane.setEditable(false);

        // Configurar la fuente y el centrado
        Font font = new Font("Arial", Font.BOLD, 20);
        textPane.setFont(font);
        StyledDocument doc = textPane.getStyledDocument();

        // Alineación centrada
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // Color de texto negro
        SimpleAttributeSet black = new SimpleAttributeSet();
        StyleConstants.setForeground(black, Color.BLACK);
        textPane.setCharacterAttributes(black, false);

        // Crear el panel central con fondo gris
        JPanel centerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false); // Hacer que el panel central sea transparente
            }
        };
        centerPanel.setBackground(new Color(211, 211, 211, 150)); // Fondo gris claro con transparencia
        centerPanel.setPreferredSize(new Dimension(400, 300)); // Ajustar tamaño según sea necesario

        JScrollPane scrollPane = new JScrollPane(textPane);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Crear un panel contenedor con fondo transparente
        JPanel containerPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                // Dibujar el texto sobre la imagen de fondo
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.setColor(Color.BLACK);
                String title = "Historial de pokemones capturados";
                int titleWidth = g.getFontMetrics().stringWidth(title);
                g.drawString(title, (getWidth() - titleWidth) / 2, 40); // Ajustar la posición vertical según sea necesario
            }
        };
        containerPanel.setOpaque(false);

        // Centrando el panel gris en el contenedor
        containerPanel.add(centerPanel);
        add(containerPanel, BorderLayout.CENTER);

        btnRegresar = new SebButton("Regresar");
        btnRegresar.addActionListener(e -> regresar());
        add(btnRegresar, BorderLayout.SOUTH);

        cargarHistorial(usuario);

        // Centrar el panel después de añadirlo al contenedor
        SwingUtilities.invokeLater(() -> centrarPanel(centerPanel, containerPanel));
    }

    private void cargarHistorial(String usuario) {
        String nombreArchivo = usuario + ".txt";
        Pila pila = new Pila();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                pila.apilar(linea); // Agregar cada línea a la pila
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de historial",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        // Mostrar los datos en el JTextPane, comenzando por el último
        try {
            StyledDocument doc = textPane.getStyledDocument();
            while (!pila.estaVacia()) {
                doc.insertString(doc.getLength(), pila.desapilar() + "\n", null);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar texto en el documento",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void centrarPanel(JPanel panel, JPanel contenedor) {
        Dimension size = panel.getPreferredSize();
        int x = (contenedor.getWidth() - size.width) / 2;
        int y = (contenedor.getHeight() - size.height) / 2;
        panel.setBounds(x, y, size.width, size.height);
        contenedor.revalidate();
        contenedor.repaint();
    }

    private void regresar() {
        try {
            removeAll();
            add(new PokeStoragePanel(usuario));
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al regresar",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// GM-44 Cargar historial