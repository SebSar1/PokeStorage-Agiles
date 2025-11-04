package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import BusinessLogic.UsuarioBL;
import UserInterface.CustomerControl.SebButton;
import UserInterface.CustomerControl.SebLabel;

public class MainForm extends JPanel {
    SebButton btonIniciarSesion = new SebButton("Iniciar Sesión"),
            btonCrearCuenta = new SebButton("Crear Cuenta");
    private JLabel background; 

    SebButton btonObjetivo = new SebButton("Objetivo");

    public MainForm() {
        customizeComponent();
        btonIniciarSesion.addActionListener(e -> iniciarSesionPnl());

        btonCrearCuenta.addActionListener(e -> btonCrearCuentaClick());

        btonObjetivo.addActionListener(e -> mostrarImagen());
    }

    private void iniciarSesionPnl() {
        try {
            removeAll();
            add(new IniciarSesionPanel());
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar PatPnlPersonaSexo",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btonCrearCuentaClick() {

        JPanel panelCrear = new JPanel(new GridLayout(4, 2, 5, 5));

        SebLabel lblNombre = new SebLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);

        SebLabel lblContraseña = new SebLabel("Contraseña:");
        JTextField txtContraseña = new JTextField(20);

        SebLabel lblSexo = new SebLabel("Sexo:");
        String[] opcionesSexo = { "Masculino", "Femenino", "Otros" };
        JComboBox<String> cmbSexo = new JComboBox<>(opcionesSexo);

        SebLabel lblEdad = new SebLabel("Edad:");
        String[] opcionesEdad = {
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
                "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42",
                "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57",
                "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72",
                "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87",
                "88", "89", "90"
        };
        JComboBox<String> cmbEdad = new JComboBox<>(opcionesEdad);

        panelCrear.add(lblNombre);
        panelCrear.add(txtNombre);
        panelCrear.add(lblContraseña);
        panelCrear.add(txtContraseña);
        panelCrear.add(lblSexo);
        panelCrear.add(cmbSexo);
        panelCrear.add(lblEdad);
        panelCrear.add(cmbEdad);

        int opcion = JOptionPane.showConfirmDialog(null, panelCrear, "Crear Usuario", JOptionPane.OK_CANCEL_OPTION);

        if (opcion == JOptionPane.OK_OPTION) {
            if (txtNombre.getText().isEmpty() || txtContraseña.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos Nombre y Contraseña no pueden estar vacíos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; 
            }

            String nombre = txtNombre.getText();
            String contraseña = txtContraseña.getText();
            String sexo = cmbSexo.getSelectedItem().toString();
            String edad = cmbEdad.getSelectedItem().toString();

            Integer numeroSexo;
            if (sexo.equals("Masculino")) {
                numeroSexo = 1;
            } else if (sexo.equals("Femenino")) {
                numeroSexo = 2;
            } else {
                numeroSexo = 3;
            }

            Integer edadInteger = Integer.parseInt(edad);
            UsuarioBL usuarioNuevo = new UsuarioBL();

            try {
                crearArchivoUsuario(nombre);
                usuarioNuevo.add(nombre, contraseña, numeroSexo, edadInteger);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void crearArchivoUsuario(String nombreUsuario) {
        String nombreArchivo = nombreUsuario + ".txt";

        // Crear el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al crear el archivo para el usuario: " + nombreUsuario);
        }
    }

    private void mostrarImagen() {
        String rutaImagen = "src\\Resource\\Img\\objetivo.png";
        
        ImageIcon imagen = new ImageIcon(rutaImagen);
        
        JOptionPane.showMessageDialog(this, new JLabel(imagen), "Objetivo POKE-STORAGE", JOptionPane.PLAIN_MESSAGE);
    }

    private void customizeComponent() {
        setLayout(new BorderLayout()); 

        ImageIcon backgroundImage = new ImageIcon("src\\Resource\\Img\\main2.png"); 
                                                                                  
        background = new JLabel(backgroundImage);

        background.setPreferredSize(new Dimension(getWidth(), getHeight()));

        background.setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 280));
        buttonsPanel.setOpaque(false); 
        buttonsPanel.add(btonIniciarSesion);
        buttonsPanel.add(btonCrearCuenta);
        buttonsPanel.add(btonObjetivo); 

        background.add(buttonsPanel, BorderLayout.CENTER);

        add(background);
    }
}

// GM-51 Implementa pantalla principal

// GM-15 Implementación Pruebas de inicio de sesión

// GM-9 subtarea: GM-11 Manejo de error si un usuario ya existe

// GM-4 subtarea: GM-7 Diseño visual

// GM-4 subtarea: GM-8 Validación de campos del formulario

// GM-4 Formulario registro