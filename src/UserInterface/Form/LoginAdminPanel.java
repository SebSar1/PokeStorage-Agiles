package UserInterface.Form;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import UserInterface.Form.MochilaPokemon.Pokemon;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class LoginAdminPanel extends JPanel {

    // Carga los pokémones desde un archivo de texto
    public static List<Pokemon> cargarPokemons(String filename) {
        List<Pokemon> pokemons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Eliminar la coma al final de la línea si está presente
                line = line.trim();
                if (line.endsWith(",")) {
                    line = line.substring(0, line.length() - 1);
                }

                // Dividir la línea en nombre y poder
                String[] parts = line.split(" ");
                if (parts.length < 2) {
                    // Manejo del error si no hay suficientes partes
                    System.err.println("Línea malformada: " + line);
                    continue;
                }

                String nombre = parts[0];
                int poder;
                try {
                    poder = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    // Manejo del error si el poder no es un número válido
                    System.err.println("Poder inválido: " + parts[1]);
                    continue;
                }

                pokemons.add(new Pokemon(nombre, poder));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    // Clase para el nodo del árbol AVL
    public static class NodoAVL {
        private int info;
        private int fe;
        private List<String> nombres;
        private int poder;
        private int repeticiones = 1;
        private NodoAVL izq, der;

        public NodoAVL(int info, String nombre, int poder) {
            this.info = info;
            this.nombres = new ArrayList<>();
            this.nombres.add(nombre);
            this.poder = poder;
            this.fe = 0;
            this.izq = null;
            this.der = null;
        }

        public void agregarNombre(String nombre) {
            this.nombres.add(nombre);
        }

        public int getInfo() {
            return info;
        }

        public void setInfo(int info) {
            this.info = info;
        }

        public int getFe() {
            return fe;
        }

        public void setFe(int fe) {
            this.fe = fe;
        }

        public NodoAVL getIzq() {
            return izq;
        }

        public void setIzq(NodoAVL izq) {
            this.izq = izq;
        }

        public NodoAVL getDer() {
            return der;
        }

        public void setDer(NodoAVL der) {
            this.der = der;
        }

        public String getNombre() {
            return nombres.get(0);
        }

        public int getPoder() {
            return poder;
        }

        public String toString(int nivel) {
            return "Nivel: " + nivel + ", Poder: " + info + ", Nombres: " + String.join(", ", nombres);
        }

        @Override
        public String toString() {
            return "Poder: " + info + ", Nombres: " + String.join(", ", nombres);
        }
    }

    // Clase para el árbol AVL
    public static class ArbolAVL {
        private NodoAVL raiz;

        public ArbolAVL() {
            this.raiz = null;
        }

        public ArbolAVL(NodoAVL raiz) {
            this.raiz = raiz;
        }

        public NodoAVL getRaiz() {
            return raiz;
        }

        public void setRaiz(NodoAVL raiz) {
            this.raiz = raiz;
        }

        public void insertar(Pokemon pokemon) {
            Logical h = new Logical(false);
            this.raiz = insertarAvl(this.raiz, pokemon, h);
        }

        private NodoAVL insertarAvl(NodoAVL raizAux, Pokemon pokemon, Logical h) {
            NodoAVL nodo1;
            if (raizAux == null) {
                raizAux = new NodoAVL(pokemon.getPoder(), pokemon.getNombre(), pokemon.getPoder());
                h.setLogical(true);
            } else if (pokemon.getPoder() < raizAux.getInfo()) {
                NodoAVL ramaIz;
                ramaIz = insertarAvl(raizAux.getIzq(), pokemon, h);
                raizAux.setIzq(ramaIz);
                if (h.valorBoolean()) {
                    switch (raizAux.getFe()) {
                        case 1:
                            raizAux.setFe(0);
                            h.setLogical(false);
                            break;
                        case 0:
                            raizAux.setFe(-1);
                            break;
                        case -1:
                            nodo1 = raizAux.getIzq();
                            if (nodo1.getFe() == -1) {
                                raizAux = rotacionII(raizAux, nodo1);
                            } else {
                                raizAux = rotacionID(raizAux, nodo1);
                            }
                            h.setLogical(false);
                            break;
                    }
                }
            } else if (pokemon.getPoder() > raizAux.getInfo()) {
                NodoAVL ramaDe;
                ramaDe = insertarAvl(raizAux.getDer(), pokemon, h);
                raizAux.setDer(ramaDe);
                if (h.valorBoolean()) {
                    switch (raizAux.getFe()) {
                        case 1:
                            nodo1 = raizAux.getDer();
                            if (nodo1.getFe() == +1) {
                                raizAux = rotacionDD(raizAux, nodo1);
                            } else {
                                raizAux = rotacionDI(raizAux, nodo1);
                            }
                            h.setLogical(false);
                            break;
                        case 0:
                            raizAux.setFe(+1);
                            break;
                        case -1:
                            raizAux.setFe(0);
                            h.setLogical(false);
                            break;
                    }
                }
            } else {
                raizAux.agregarNombre(pokemon.getNombre());
            }
            return raizAux;
        }

        private NodoAVL rotacionII(NodoAVL nodo1, NodoAVL nodo2) {
            nodo1.setIzq(nodo2.getDer());
            nodo2.setDer(nodo1);
            if (nodo2.getFe() == -1) {
                nodo1.setFe(0);
                nodo2.setFe(0);
            } else {
                nodo1.setFe(-1);
                nodo2.setFe(1);
            }
            return nodo2;
        }

        private NodoAVL rotacionDD(NodoAVL nodo1, NodoAVL nodo2) {
            nodo1.setDer(nodo2.getIzq());
            nodo2.setIzq(nodo1);
            if (nodo2.getFe() == +1) {
                nodo1.setFe(0);
                nodo2.setFe(0);
            } else {
                nodo1.setFe(+1);
                nodo2.setFe(-1);
            }
            return nodo2;
        }

        private NodoAVL rotacionID(NodoAVL nodo1, NodoAVL nodo2) {
            NodoAVL n3 = nodo2.getDer();
            nodo1.setIzq(n3.getDer());
            n3.setDer(nodo1);
            nodo2.setDer(n3.getIzq());
            n3.setIzq(nodo2);
            if (n3.getFe() == +1) {
                nodo2.setFe(-1);
            } else {
                nodo2.setFe(0);
            }
            if (n3.getFe() == -1) {
                nodo1.setFe(1);
            } else {
                nodo1.setFe(0);
            }
            n3.setFe(0);
            return n3;
        }

        private NodoAVL rotacionDI(NodoAVL nodo1, NodoAVL nodo2) {
            NodoAVL n3 = nodo2.getIzq();
            nodo1.setDer(n3.getIzq());
            n3.setIzq(nodo1);
            nodo2.setIzq(n3.getDer());
            n3.setDer(nodo2);
            if (n3.getFe() == -1) {
                nodo2.setFe(1);
            } else {
                nodo2.setFe(0);
            }
            if (n3.getFe() == +1) {
                nodo1.setFe(-1);
            } else {
                nodo1.setFe(0);
            }
            n3.setFe(0);
            return n3;
        }

        public String obtenerInorden() {
            StringBuilder resultado = new StringBuilder();
            ayudanteInorden(this.raiz, resultado, 0); // Nivel inicial es 0
            return resultado.toString();
        }

        private void ayudanteInorden(NodoAVL nod, StringBuilder resultado, int nivel) {
            if (nod != null) {
                ayudanteInorden(nod.getIzq(), resultado, nivel + 1);
                resultado.append(nod.toString(nivel)).append("\n");
                ayudanteInorden(nod.getDer(), resultado, nivel + 1);
            }
        }
    }

    // Clase para manejar el valor booleano en las rotaciones
    public static class Logical {
        private boolean v;

        public Logical(boolean f) {
            v = f;
        }

        public boolean valorBoolean() {
            return v;
        }

        public void setLogical(boolean f) {
            v = f;
        }
    }

    public static class ArbolGraficoPanel extends JPanel {

        private ArbolAVL arbol;

        public ArbolGraficoPanel(ArbolAVL arbol) {
            this.arbol = arbol;
            setPreferredSize(new Dimension(800, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            dibujarNodo(g, arbol.raiz, getWidth() / 2, 30, getWidth() / 4);
        }

        private void dibujarNodo(Graphics g, NodoAVL nodo, int x, int y, int xOffset) {
            if (nodo != null) {
                g.setColor(Color.BLACK);
                g.drawString(nodo.getNombre() + "(" + nodo.getPoder() + ")", x - 15, y);
                if (nodo.getIzq() != null) {
                    g.drawLine(x - 5, y + 5, x - xOffset + 5, y + 50 - 5);
                    dibujarNodo(g, nodo.getIzq(), x - xOffset, y + 50, xOffset / 2);
                }
                if (nodo.getDer() != null) {
                    g.drawLine(x + 5, y + 5, x + xOffset + 5, y + 50 - 5);
                    dibujarNodo(g, nodo.getDer(), x + xOffset, y + 50, xOffset / 2);
                }
            }
        }
    }

}