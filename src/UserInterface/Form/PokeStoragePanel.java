package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import UserInterface.CustomerControl.SebButton;
import UserInterface.CustomerControl.SebLabel;
import UserInterface.Form.LoginAdminPanel.ArbolAVL;
import UserInterface.Form.LoginAdminPanel.ArbolGraficoPanel;
import UserInterface.Form.MochilaPokemon.Pokemon;

public class PokeStoragePanel extends JPanel {

    private SebButton btnRegistrar;
    private SebButton btnArbol;
    private SebButton btnLista;
    private SebButton btnHistorial;
    private Image backgroundImage;
    private SebButton btnRegresar;
    private String usuario;
    private SebButton btnInorden;
    private SebButton btnGrafico;

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
        btnInorden = new SebButton("Ver Inorden");
        btnGrafico = new SebButton("Ver Gráfico");
        btnInorden.setPreferredSize(new Dimension(300, 50));
        btnGrafico.setPreferredSize(new Dimension(300, 50));
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

        btnArbol.addActionListener(e -> mostrarOpcionesArbol());
        btnInorden.addActionListener(e -> mostrarInorden());
        btnGrafico.addActionListener(e -> mostrarGrafico());
        btnLista.addActionListener(e -> mostrarListaPokemon());
        btnRegresar.addActionListener(e -> MainForm());
        btnHistorial.addActionListener(e -> mostrarHistorial());
    }

    private void MainForm() {
        try {
            removeAll();
            add(new MainForm());
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar MainForm",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarOpcionesArbol() {
        JPanel opcionesPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        opcionesPanel.add(btnInorden);
        opcionesPanel.add(btnGrafico);

        JOptionPane.showMessageDialog(this, opcionesPanel, "Opciones del Árbol AVL", JOptionPane.PLAIN_MESSAGE);
    }

    private void mostrarInorden() {
        // Crear una nueva instancia del árbol AVL
        ArbolAVL arbol = new ArbolAVL();

        // Cargar los pokémones desde el archivo
        List<Pokemon> pokemons = LoginAdminPanel.cargarPokemons(usuario + ".txt");

        // Insertar los pokémones en el árbol AVL
        for (Pokemon pokemon : pokemons) {
            arbol.insertar(pokemon);
        }

        // Mostrar el árbol en una ventana emergente
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setText(arbol.obtenerInorden()); // Usar obtenerInorden() en lugar de toString()
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(null, scrollPane, "Árbol AVL en Inorden", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarGrafico() {
        // Crear una nueva instancia del árbol AVL
        ArbolAVL arbol = new ArbolAVL();

        // Cargar los pokémones desde el archivo
        List<MochilaPokemon.Pokemon> pokemons = LoginAdminPanel.cargarPokemons(usuario + ".txt");

        // Insertar los pokémones en el árbol AVL
        for (MochilaPokemon.Pokemon pokemon : pokemons) {
            arbol.insertar(pokemon);
        }

        // Crear un panel personalizado para dibujar el árbol
        ArbolGraficoPanel arbolPanel = new ArbolGraficoPanel(arbol);

        // Mostrar el panel en una ventana emergente
        JScrollPane scrollPane = new JScrollPane(arbolPanel);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        JOptionPane.showMessageDialog(null, scrollPane, "Árbol AVL Gráfico", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarListaPokemon() {
        try {
            removeAll();
            add(new ListaPokemon(usuario));
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar la lista de Pokémones",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarFormulario() {
        // Utiliza JOptionPane para crear una ventana emergente con los componentes
        // necesarios
        JPanel panerCrear = new JPanel(new GridLayout(2, 2, 5, 5));

        SebLabel lblNombre = new SebLabel("Nombre del Pokémon:");

        // Crear las opciones de los 10 Pokémon más famosos
        String[] nombresPokemon = {
                "Pikachu", "Charizard", "Bulbasaur", "Squirtle", "Jigglypuff", "Meowth", "Psyduck",
                "Snorlax", "Gengar", "Eevee", "Mewtwo", "Lugia", "Ho-Oh", "Celebi", "Torchic",
                "Mudkip", "Treecko", "Blaziken", "Gardevoir", "Metagross", "Rayquaza", "Lucario",
                "Garchomp", "Infernape", "Empoleon", "Torterra", "Dialga", "Palkia", "Giratina",
                "Darkrai", "Zoroark", "Chandelure", "Greninja", "Talonflame", "Sylveon", "Tyrantrum",
                "Goodra", "Noivern", "Xerneas", "Yveltal", "Zygarde", "Rowlet", "Litten", "Popplio",
                "Decidueye", "Incineroar", "Primarina", "Lycanroc", "Toxtricity", "Dragapult",
                "Cinderace", "Rillaboom", "Inteleon", "Zacian", "Zamazenta", "Eternatus", "Urshifu"
        };

        JComboBox<String> cmbNombre = new JComboBox<>(nombresPokemon);

        SebLabel lblPoder = new SebLabel("Poder:");
        JTextField txtPoder = new JTextField(10);

        // Agregar los componentes al panel
        panerCrear.add(lblNombre);
        panerCrear.add(cmbNombre);
        panerCrear.add(lblPoder);
        panerCrear.add(txtPoder);

        // Mostrar la ventana emergente de entrada con los componentes
        int opcion = JOptionPane.showConfirmDialog(null, panerCrear, "Registrar Pokémon", JOptionPane.OK_CANCEL_OPTION);

        // Verificar si se ha presionado "OK"
        if (opcion == JOptionPane.OK_OPTION) {
            // Validar el campo de poder para que no sea negativo y no contenga texto
            String poderTexto = txtPoder.getText();
            int poder;
            try {
                poder = Integer.parseInt(poderTexto);
                if (poder < 0) {
                    JOptionPane.showMessageDialog(null, "El poder no puede ser negativo", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método sin procesar los datos
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido para el poder", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // Salir del método sin procesar los datos
            }

            // Obtener el nombre seleccionado y el poder ingresado
            String nombre = (String) cmbNombre.getSelectedItem();

            // Mostrar los datos del Pokémon registrado
            JOptionPane.showMessageDialog(null, "Pokémon registrado:\nNombre: " + nombre + "\nPoder: " + poder,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Lógica para almacenar los datos en el sistema
            MochilaPokemon.guardarPokemon(nombre, poder, usuario);
        }
    }

    private void mostrarHistorial() {
        try {
            removeAll();
            add(new HistorialPanel(usuario));
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el historial",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadImage() {
        ImageIcon imagenFondo = new ImageIcon("src\\Resource\\Img\\RolFondo.png");
        backgroundImage = imagenFondo.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

// GM-30 subtarea: GM-31 campos
// GM-30 subtarea: GM-32 validacion campos
// GM-30 subtarea: GM-33 pruebas de interfaz
// GM-30 Implementación completa del formulario

// GM-34 subtarea: GM-35 Logica de guardado
// GM-34 subtarea: GM-36 Controla tope de especies
// GM-34 Logica de guardado

// GM-14 Implementación Manejo de sesión actual

// GM-46 Implementar un arbol AVL