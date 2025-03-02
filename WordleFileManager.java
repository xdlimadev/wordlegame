package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WordleFileManager {

    // Método en el cual cargamos las palabras de un archivo.
    public static String[] loadWords(String filePath) {

        // Instanciamos un array con 10 posiciones.
        String[] words = new String[10];

        // try catch para manejo de excepciones en caso de cualquier error con el archivo.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Bucle que recorre el array leyendo línea por línea del archivo y añadiendo a 
            // su correspondiente posicion en el array en caso de que no sea nulo.
            for (int i = 0; i < 10; i++) {
                if ((line = br.readLine()) != null) {
                    words[i] = line.toUpperCase();
                }
            }
        } catch (IOException e) {
            System.out.println("This is not good ...  no found words or file... or something... Error: " + e.getMessage());
        }
        // devolvemos el array
        return words;
    }

    // Método para guardar el historial de palabras introducidas por el jugador en un archivo txt
    public static void saveHistory(String[] history, int size) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/history.txt", true))) {
            // bucle en el cual se guarda en el archivo las palabras con un salto de línea.
            for (int i = 0; i < size; i++) {
                bw.write(history[i] + "\n");
            }
            // y para diferenciar las partidas añadimos --- con un salto de línea.
            bw.write("---\n");
        } catch (IOException e) {
            System.out.println("Game history has not been saved.");
        }
    }
}
