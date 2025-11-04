package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

// Clase que define una etiqueta de texto personalizada con estilo infantil
public class SebLabel extends JLabel {

    private String label = ""; // Texto de la etiqueta
    private Color color = new Color(12, 183, 242); // Color del texto (por defecto azul)
    private int alignment = SwingConstants.LEFT; // Alineación del texto (por defecto a la izquierda)
    private Font font = new Font("Comic Sans MS", Font.BOLD, 26); // Fuente del texto (tipo de letra y tamaño)

    // Constructor vacio
    public SebLabel() {
        customizeLabel(); // Configura la apariencia de la etiqueta
    }

    // Constructor con texto
    public SebLabel(String label) {
        this.label = label;
        customizeLabel(); // Configura la apariencia de la etiqueta
    }

    // Constructor con texto y color
    public SebLabel(String label, Color color) {
        this.label = label;
        this.color = color;
        customizeLabel(); // Configura la apariencia de la etiqueta
    }

    // Constructor con texto, color y fuente
    public SebLabel(String label, Color color, Font font) {
        this.label = label;
        this.color = color;
        this.font = font;
        customizeLabel(); // Configura la apariencia de la etiqueta
    }

    // Constructor con texto y alineación
    public SebLabel(String label, int alignment) {
        this.label = label;
        this.alignment = alignment;
        customizeLabel(); // Configura la apariencia de la etiqueta
    }

    // Método privado para configurar la apariencia de la etiqueta
    private void customizeLabel() {
        setFont(font); // Establece la fuente del texto
        setText(label); // Establece el texto de la etiqueta
        setForeground(color); // Establece el color del texto
        setHorizontalAlignment(alignment); // Establece la alineación del texto
        setOpaque(false); // Hace que la etiqueta sea transparente
        setBackground(null); // Establece el fondo de la etiqueta como transparente
    }
}

// GM-4 subtarea: GM-7 Diseño visual