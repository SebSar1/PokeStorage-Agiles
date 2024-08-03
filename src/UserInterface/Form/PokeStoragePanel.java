/**
/*-------------------------------------------------------------\
|  Copyright (©) 2K24 EPN-FIS. All rights reserved.            |
|  sebastian.sarasti01@epn.edu.ec PROPRIETARY/CONFIDENTIAL.    |
|  Use is subject to license terms.       Sebastian Sarasti    |
\--------------------------------------------------------------\
 */
package UserInterface.Form; // Define el paquete donde se encuentra la clase mostrarFormulario

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BusinessLogic.UsuarioBL;
import UserInterface.CustomerControl.SebButton;
import UserInterface.CustomerControl.SebLabel;

public class PokeStoragePanel extends JPanel {

    private SebButton btnRegistrar;
    private SebButton btnArbol;
    private SebButton btnLista;
    private SebButton btnHistorial;
    private Image backgroundImage;
    private SebButton btnRegresar;
    private String usuario;

    public PokeStoragePanel(String usuario) {
        this.usuario = usuario;
        initializeComponents();
        setupLayout();
        setupActions();
        loadImage();
    }

    private void initializeComponents() {
        btnRegistrar = new SebButton("Registrar Pokemon");
        btnArbol = new SebButton("Ver Árbol");
        btnLista = new SebButton("Lista Pokemon");
        btnHistorial = new SebButton("Historial Captura");
        btnRegistrar.setPreferredSize(new Dimension(300, 50));
        btnArbol.setPreferredSize(new Dimension(300, 50));
        btnLista.setPreferredSize(new Dimension(300, 50));
        btnHistorial.setPreferredSize(new Dimension(300, 50));
        btnRegresar = new SebButton("Regresar");
        MochilaPokemon mochilaPokemon = new MochilaPokemon();

    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel para los botones superiores
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        topPanel.setOpaque(false);
        topPanel.add(btnRegistrar);
        topPanel.add(btnArbol);

        // Panel para los botones inferiores
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setOpaque(false);
        bottomPanel.add(btnLista);
        bottomPanel.add(btnHistorial);

        // Panel para el botón regresar
        JPanel regresarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        regresarPanel.setOpaque(false);
        regresarPanel.add(btnRegresar);

        // Agregar los paneles a la posición correspondiente
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
        add(regresarPanel, BorderLayout.SOUTH);
    }

    private void setupActions() {
        btnRegistrar.addActionListener(e -> mostrarFormulario());

        btnArbol.addActionListener(e -> LoginAdminPanel());
        btnLista.addActionListener(e -> LoginListaPanel());
        btnRegresar.addActionListener(e -> MainForm());
    }

    private void MainForm() {
        try {
            removeAll();
            add(new MainForm());
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar PatPnlPersonaSexo",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LoginListaPanel() {
        // try {
        // removeAll();
        // add(new LoginListaPanel());
        // revalidate();
        // repaint();
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // JOptionPane.showMessageDialog(this, "Error al cargar PatPnlPersonaSexo",
        // "ERROR", JOptionPane.ERROR_MESSAGE);
        // }
    }

    private void LoginAdminPanel() {
        // try {
        // removeAll();
        // add(new LoginAdminPanel());
        // revalidate();
        // repaint();
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // JOptionPane.showMessageDialog(this, "Error al cargar PatPnlPersonaSexo",
        // "ERROR", JOptionPane.ERROR_MESSAGE);
        // }
    }

    private void mostrarFormulario() {
        // Utiliza JOptionPane para crear una ventana emergente con los componentes
        // necesarios
        JPanel panerCrear = new JPanel(new GridLayout(2, 2, 5, 5));

        SebLabel lblNombre = new SebLabel("Nombre del Pokémon:");
        JTextField txtNombre = new JTextField(20);

        SebLabel lblPoder = new SebLabel("Poder:");
        // Crear las opciones de poder del 1000 al 2500 en saltos de 100
        Integer[] poderes = new Integer[16];
        for (int i = 0; i < poderes.length; i++) {
            poderes[i] = 1000 + (i * 100);
        }
        JComboBox<Integer> cmbPoder = new JComboBox<>(poderes);

        // Agregar los componentes al panel
        panerCrear.add(lblNombre);
        panerCrear.add(txtNombre);
        panerCrear.add(lblPoder);
        panerCrear.add(cmbPoder);

        // Mostrar la ventana emergente de entrada con los componentes
        int opcion = JOptionPane.showConfirmDialog(null, panerCrear, "Registrar Pokémon", JOptionPane.OK_CANCEL_OPTION);

        // Verificar si se ha presionado "OK"
        if (opcion == JOptionPane.OK_OPTION) {
            // Verificar que el campo de nombre no esté vacío
            if (txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo Nombre del Pokémon no puede estar vacío", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // Salir del método sin procesar los datos
            }

            // Obtener los datos ingresados
            String nombre = txtNombre.getText();
            Integer poder = (Integer) cmbPoder.getSelectedItem();

            // Aquí los datos del Pokémon
            JOptionPane.showMessageDialog(null, "Pokémon registrado:\nNombre: " + nombre + "\nPoder: " + poder,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // lógica para almacenar los datos en el sistema
            MochilaPokemon.guardarPokemon(nombre, poder, usuario);
        }
    }

    private void loadImage() {
        ImageIcon imagenFondo = new ImageIcon(
                "src\\Resource\\Img\\RolFondo.png");
        backgroundImage = imagenFondo.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    // @Override
    // public Dimension getPreferredSize() {
    // return new Dimension(600, 400); // Establece el tamaño preferido del panel
    // }
}
