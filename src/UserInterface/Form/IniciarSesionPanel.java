package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;
import UserInterface.CustomerControl.SebButton;
import UserInterface.CustomerControl.SebLabel;

public class IniciarSesionPanel extends JPanel {

    private JTextField usuarioField;
    private JPasswordField passwordField;
    private Image backgroundImage;
    private SebButton btnRegresar;

    public IniciarSesionPanel() {
        initializeComponents();
        setupLayout();
        setupActions();
        loadImage();
    }

    private void initializeComponents() {
        usuarioField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new SebButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 50));
        btnRegresar = new SebButton("Regresar");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel panerIncicioSesion = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 100));
        panerIncicioSesion.setOpaque(false);
        panerIncicioSesion.add(new SebLabel("Usuario:"));
        panerIncicioSesion.add(usuarioField);
        panerIncicioSesion.add(new SebLabel("Contraseña:"));
        panerIncicioSesion.add(passwordField);
        panerIncicioSesion.add(loginButton);

        add(panerIncicioSesion, BorderLayout.CENTER);

        JPanel panelRegresar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRegresar.setOpaque(false);

        panelRegresar.add(btnRegresar);

        add(panelRegresar, BorderLayout.SOUTH);
    }

    private void setupActions() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = usuarioField.getText();
                char[] passwordChars = passwordField.getPassword();
                String contraseña = new String(passwordChars);

                if (validarUsuario(nombre, contraseña)) {
                    JOptionPane.showMessageDialog(IniciarSesionPanel.this, "Login Exitoso", "Bienvenido",
                            JOptionPane.INFORMATION_MESSAGE);
                    PokeStoragePanel(nombre);
                } else {
                    JOptionPane.showMessageDialog(IniciarSesionPanel.this, "Usuario o Contraseña incorrectos", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                usuarioField.setText("");
                passwordField.setText("");
            }
        });

        btnRegresar.addActionListener(e -> MainForm());
    }

    private boolean validarUsuario(String nombre, String contraseña) {
        try {
            ArrayList<UsuarioDTO> listaUsuarios = UsuarioBL.getAll();

            for (UsuarioDTO usuarioGuardado : listaUsuarios) {
                if (nombre.equals(usuarioGuardado.getNombre()) &&
                        contraseña.equals(usuarioGuardado.getContraseña())) {

                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void PokeStoragePanel(String nombre) {
        try {
            removeAll();
            add(new PokeStoragePanel(nombre));
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar PokeStoragePanel",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void MainForm() {
        try {
            removeAll();
            add(new MainForm());
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar MainForm", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadImage() {
        ImageIcon imagenFondo = new ImageIcon("src\\Resource\\Img\\AdminFondo.png");
        backgroundImage = imagenFondo.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
// GM-12 subtarea: GM-13 Leer Usuario y verificar contraseña



