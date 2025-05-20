package procesador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GCI {
    private static List<Cuarteto> cuartetos = new ArrayList<>();
    private static int tempCounter = 0; // Contador para variables temporales
    private static int labelCounter = 0; // Contador para etiquetas

    public static void addCuarteto(String op, String arg1, String arg2, String res) {
        cuartetos.add(new Cuarteto(op, arg1, arg2, res));
    }

    public static void addCuarteto(Cuarteto c) {
        cuartetos.add(c);
    }
    
    // Genera un nuevo nombre para variable temporal
    public static String nuevaTemp() {
        return "t" + tempCounter++;
    }

    // Genera un nuevo nombre para etiqueta
    public static String nuevaEtiqueta() {
        return "ET" + labelCounter++;
    }

    public static void escribirCuartetos(String nombreFichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
            for (Cuarteto c : cuartetos) {
                bw.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero de cuartetos: " + e.getMessage());
        }
    }

    public static void reset() {
        cuartetos.clear();
        tempCounter = 0;
        labelCounter = 0;
    }
}