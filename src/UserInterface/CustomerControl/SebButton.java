/**
/*-------------------------------------------------------------\
|  Copyright (©) 2K24 EPN-FIS. All rights reserved.            |
|  sebastian.sarasti01@epn.edu.ec PROPRIETARY/CONFIDENTIAL.    |
|  Use is subject to license terms.       Sebastian Sarasti    |
\--------------------------------------------------------------\
 */
package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SebButton extends JButton implements MouseListener {

    // Constructor para botones con solo texto
    public SebButton(String label) {
        super(label); // Llama al constructor de la superclase con la etiqueta del botón
        setupButton(); // Configura el estilo del botón
    }

    // Constructor para botones con texto e icono
    public SebButton(String label, String iconPath) {
        super(label); // Llama al constructor de la superclase con la etiqueta del botón
        setupButton(); // Configura el estilo del botón
        setIcon(new ImageIcon(iconPath)); // Establece el icono del botón
    }

    // Método privado para configurar el estilo del botón
    private void setupButton() {
        setFocusPainted(false); // Desactiva la pintura del enfoque
        setBorderPainted(false); // Desactiva la pintura del borde
        setContentAreaFilled(false); // Desactiva la pintura del área de contenido
        setHorizontalAlignment(SwingConstants.CENTER); // Alinea el texto al centro
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Establece el cursor del mouse a una mano
        setFont(new Font("Comic Sans MS", Font.BOLD, 20)); // Establece la fuente del texto del botón
        setForeground(Color.WHITE); // Establece el color del texto
        setBackground(new Color(255, 153, 153)); // Establece el color de fondo del botón (rojo claro)
        setOpaque(false); // Hace que el botón sea transparente
        addMouseListener(this); // Agrega un listener de mouse para manejar eventos del mouse
    }

    // Método para dibujar el botón con bordes redondeados y un degradado de color
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create(); // Crea un contexto gráfico 2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Activa el
                                                                                                  // antialiasing
        int width = getWidth(); // Obtiene el ancho del botón
        int height = getHeight(); // Obtiene el alto del botón

        // Crea un degradado de color para el fondo del botón
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(1, 46, 70), 0, height,
                new Color(1, 46, 70));
        g2d.setPaint(gradientPaint); // Establece el degradado como color de fondo
        g2d.fillRoundRect(0, 0, width, height, 20, 20); // Dibuja un rectángulo redondeado con el degradado

        super.paintComponent(g2d); // Llama al método paintComponent de la superclase para dibujar el contenido del
                                   // botón
        g2d.dispose(); // Libera los recursos gráficos
    }

    // Método para cambiar el color del botón cuando el mouse entra
    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(new Color(255, 178, 102)); // Cambia el color de fondo a un tono más claro de naranja
    }

    // Método para restaurar el color del botón cuando el mouse sale
    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(new Color(255, 153, 153)); // Establece el color de fondo del botón (rojo claro)
    }

    // Métodos no utilizados de la interfaz MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
