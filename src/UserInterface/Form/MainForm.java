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
    private JLabel background; // Etiqueta para la imagen de fondo

    public MainForm() {
        customizeComponent();
        btonIniciarSesion.addActionListener(e -> iniciarSesionPnl());

        btonCrearCuenta.addActionListener(e -> btonCrearCuentaClick());
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
        // Utiliza JOptionPane para crear una ventana emergente con los componentes
        // necesarios
        JPanel panerCrear = new JPanel(new GridLayout(4, 2, 5, 5));

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

        panerCrear.add(lblNombre);
        panerCrear.add(txtNombre);
        panerCrear.add(lblContraseña);
        panerCrear.add(txtContraseña);
        panerCrear.add(lblSexo);
        panerCrear.add(cmbSexo);
        panerCrear.add(lblEdad);
        panerCrear.add(cmbEdad);

        // Mostrar la ventana emergente de entrada con los componentes
        int opcion = JOptionPane.showConfirmDialog(null, panerCrear, "Crear Usuario", JOptionPane.OK_CANCEL_OPTION);

        // Verificar si se ha presionado "OK"
        if (opcion == JOptionPane.OK_OPTION) {
            // Verificar que los campos no estén vacíos
            if (txtNombre.getText().isEmpty() || txtContraseña.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos Nombre y Contraseña no pueden estar vacíos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // Salir del método sin procesar los datos
            }

            // Aquí puedes procesar los datos ingresados por el usuario
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

    // Método para crear un archivo de texto con el nombre del usuario
    private void crearArchivoUsuario(String nombreUsuario) {
        // Nombre del archivo basado en el nombre del usuario
        String nombreArchivo = nombreUsuario + ".txt";

        // Crear el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al crear el archivo para el usuario: " + nombreUsuario);
        }
    }

    private void customizeComponent() {
        setLayout(new BorderLayout()); // Establece el diseño del MainForm como BorderLayout

        // Carga la imagen de fondo
        ImageIcon backgroundImage = new ImageIcon("src\\Resource\\Img\\Main.png"); // Cambia la ruta por la ubicación de
                                                                                   // tu imagen
        background = new JLabel(backgroundImage);

        // Establece el tamaño del fondo para que coincida con el tamaño del MainForm
        background.setPreferredSize(new Dimension(getWidth(), getHeight()));

        // Establece el diseño del fondo como BorderLayout para que cubra todo el
        // MainForm
        background.setLayout(new BorderLayout());

        // Agrega los botones al fondo
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 280));
        buttonsPanel.setOpaque(false); // Hace que el panel de botones sea transparente
        buttonsPanel.add(btonIniciarSesion);
        buttonsPanel.add(btonCrearCuenta);

        // Agrega el panel de botones al centro del fondo
        background.add(buttonsPanel, BorderLayout.CENTER);

        // Agrega el fondo al MainForm
        add(background);
    }
}
