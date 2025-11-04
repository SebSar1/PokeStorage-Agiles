package UserInterface.Form;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MochilaPokemon {

    // Guarda los datos del Pokémon en un archivo de texto
    public static void guardarPokemon(String nombre, int poder, String ARCHIVO_POKEMONES) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_POKEMONES + ".txt", true))) {
            writer.write(nombre + " " + poder + ",");
            writer.newLine(); // Agregar una nueva línea para el próximo Pokémon
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar el Pokémon en el archivo.");
        }
    }

    // Carga los datos de los Pokémon desde el archivo y los devuelve en un arreglo
    public static List<Pokemon> cargarPokemons(String ARCHIVO_POKEMONES) {
        List<Pokemon> pokemons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_POKEMONES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    int poder = Integer.parseInt(partes[1]);
                    pokemons.add(new Pokemon(nombre, poder));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar los Pokémon desde el archivo.");
        }
        return pokemons;
    }

    // Clase interna para representar un Pokémon
    public static class Pokemon {
        private String nombre;
        private int poder;

        public Pokemon(String nombre, int poder) {
            this.nombre = nombre;
            this.poder = poder;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPoder() {
            return poder;
        }

        @Override
        public String toString() {
            return "Nombre: " + nombre + ", Poder: " + poder;
        }
    }
}

// GM-34 Logica de guardado
