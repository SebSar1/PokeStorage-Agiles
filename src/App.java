
/**
/*-------------------------------------------------------------\
|  Copyright (©) 2K24 EPN-FIS. All rights reserved.            |
|  sebastian.sarasti01@epn.edu.ec PROPRIETARY/CONFIDENTIAL.    |
|  Use is subject to license terms.       Sebastian Sarasti    |
\--------------------------------------------------------------\
 */
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

// import BusinessLogic.PersonaBL;
// import BusinessLogic.UsuarioSistemaBL;
// import DataAcces.DTO.UsuarioSistemaDTO;
// import UserInterface.Form.MainForm;
// import UserInterface.Form.SplashScreenForm;

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
        // Cambia
        // la ruta
        // logo
        frame.setIconImage(icon.getImage());
        frame.setSize(688, 700); // Por ejemplo, tamaño de 400x300 píxeles
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
