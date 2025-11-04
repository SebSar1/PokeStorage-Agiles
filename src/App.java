import java.awt.SplashScreen;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialOceanicContrastIJTheme;

import UserInterface.Form.MainForm;
import UserInterface.Form.SplashScreenForm;

public class App {
    public static void main(String[] args) throws Exception {

        iniciarSistema();
    }

    public static void iniciarSistema() {
        FlatLightLaf.setup();

        FlatLightLaf.supportsNativeWindowDecorations();
        try {
            UIManager.setLookAndFeel(new FlatMaterialOceanicContrastIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SplashScreenForm.show();
        MainForm mainForm = new MainForm();
        JFrame frame = new JFrame("Poke-Storage");
        frame.add(mainForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        ImageIcon icon = new ImageIcon("src\\Resource\\Img\\icono_Ventana.png");

        frame.setIconImage(icon.getImage());
        frame.setSize(688, 700); // Por ejemplo, tamaño de 400x300 píxeles
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}

// GM-52 Navegacion fluida entre paneles de la aplicacion

// GM-12 subtarea: GM-14 Implementación Manejo de sesión actual

// GM-50 Implementar splash screen