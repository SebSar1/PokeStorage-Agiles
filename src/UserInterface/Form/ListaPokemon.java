package UserInterface.Form;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import UserInterface.CustomerControl.SebButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListaPokemon extends JPanel {
    private ListaCircular lista;
    private Nodo current;
    private JTextPane textPane;
    private SebButton btnSiguiente;
    private SebButton btnAnterior;
    private SebButton btnRegresar;
    private String usuario;
    private Image backgroundImage;

    public ListaPokemon(String usuario) {
        this.usuario = usuario;
        lista = cargarListaPokemon();
        current = lista.getHead();
        initializeComponents();
        setupLayout();
        setupActions();
        displayCurrentPokemon();
    }

    private void initializeComponents() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font("Arial", Font.BOLD, 20));
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        SimpleAttributeSet black = new SimpleAttributeSet();
        StyleConstants.setForeground(black, Color.BLACK);
        textPane.setCharacterAttributes(black, false);

        btnSiguiente = new SebButton("Siguiente");
        btnAnterior = new SebButton("Anterior");
        btnRegresar = new SebButton("Regresar");

        // Cargar imagen de fondo
        ImageIcon imagenFondo = new ImageIcon("src\\Resource\\Img\\ListaFondo.jpg");
        backgroundImage = imagenFondo.getImage();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel para los botones de navegación
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnAnterior);
        buttonPanel.add(btnSiguiente);

        // Panel central para el área de texto
        JPanel centerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
            }
        };
        centerPanel.setBackground(new Color(211, 211, 211, 150));
        centerPanel.setPreferredSize(new Dimension(300, 300));
        centerPanel.add(new JScrollPane(textPane), BorderLayout.CENTER);

        // Panel contenedor principal con la imagen de fondo
        JPanel containerPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.setColor(Color.BLACK);
                String title = "Pokemon capturados";
                int titleWidth = g.getFontMetrics().stringWidth(title);
                g.drawString(title, (getWidth() - titleWidth) / 2, 40);
            }
        };
        containerPanel.setOpaque(false);
        containerPanel.add(centerPanel);

        // Centrando el panel gris en el contenedor
        centerPanel.setBounds(100, 100, 300, 200); // Ajustar posición y tamaño según sea necesario
        containerPanel.add(centerPanel);

        // Panel para el botón de regresar en la esquina inferior izquierda
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(btnRegresar, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        add(containerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> centrarPanel(centerPanel, containerPanel));
    }

    private void setupActions() {
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (current != null && current.getLiga() != null) {
                    current = current.getLiga();
                    displayCurrentPokemon();
                }
            }
        });

        btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Nodo temp = lista.getHead();
                while (temp != null && temp.getLiga() != current) {
                    temp = temp.getLiga();
                }
                if (temp != null) {
                    current = temp;
                    displayCurrentPokemon();
                }
            }
        });

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresar();
            }
        });
    }

    private void displayCurrentPokemon() {
        if (current != null) {
            // Dividimos la cadena por espacio
            String[] datos = current.getInfo().trim().split(" ");
            String nombre = datos[0].trim(); // Primer elemento es el nombre
            String poder = datos[1].replace(",", "").trim(); // Segundo elemento es el poder
            
            // Cargar la imagen del Pokémon
            ImageIcon imagenPokemon = cargarImagenPokemon(nombre);
            
            if (imagenPokemon.getIconWidth() > 0) {
                // Mostrar el nombre y poder del Pokémon junto con la imagen
                textPane.setText(nombre + " (Poder: " + poder + ")" + "\n");
                textPane.setCaretPosition(textPane.getDocument().getLength());
                textPane.insertIcon(imagenPokemon);
            } else {
                // Mostrar un mensaje si no se encuentra la imagen
                textPane.setText("No se encontró la imagen de " + nombre + "\nPoder: " + poder);
            }
        } else {
            // Mensaje si no hay más Pokémones en la lista
            textPane.setText("No hay más Pokémones en la lista.");
        }
    }


    private void regresar() {
        try {
            removeAll();
            add(new PokeStoragePanel(usuario));
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al regresar", "ERROR", JOptionPane.ERROR_MESSAGE);
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

    private ListaCircular cargarListaPokemon() {
        ListaCircular lista = new ListaCircular();
        try (BufferedReader reader = new BufferedReader(new FileReader(usuario + ".txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lista.insertarNodoFinal(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de Pokémones",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    private class Nodo {
        private String info;
        private Nodo liga;

        public Nodo(String info, Nodo liga) {
            this.info = info;
            this.liga = liga;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Nodo getLiga() {
            return liga;
        }

        public void setLiga(Nodo liga) {
            this.liga = liga;
        }
    }

    private class ListaCircular {
        private Nodo head;
        private Nodo tail;

        public Nodo getHead() {
            return head;
        }

        public void insertarNodoFinal(String info) {
            Nodo nuevoNodo = new Nodo(info, null);
            if (head == null) {
                head = nuevoNodo;
                tail = nuevoNodo;
                tail.setLiga(head); // Hacer circular
            } else {
                tail.setLiga(nuevoNodo);
                tail = nuevoNodo;
                tail.setLiga(head); // Mantener circularidad
            }
        }
    }

    private ImageIcon cargarImagenPokemon(String nombre) {
        // Usa el ClassLoader para cargar el recurso
        String rutaImagen = "/Resource/Img/" + nombre.toLowerCase() + ".png";
        java.net.URL url = getClass().getResource(rutaImagen);
    
        if (url != null) {
            return new ImageIcon(url);
        } else {
            System.err.println("Imagen no encontrada: " + rutaImagen);
            return null; // o una imagen por defecto
        }
    }

}

// GM-41 Manejo de imagenes

// GM-40 Integración con UI

// GM-39 Metodo insertar y recorrer

// GM-55 Implementar fallback para carga de imágenes

// GM-38 Clase lista y metodos