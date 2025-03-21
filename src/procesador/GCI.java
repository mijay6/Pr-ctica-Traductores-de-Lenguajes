package procesador;

import java.util.ArrayList;
import java.util.List;


public class GCI {
	
	// Lista para almacenar los cuartetos generados
	private List<String> cuartetos;
	
	// Contadores para desplazamiento de  temporales, no locales y etiquetas
	private static int despTemp;
	private static int despNoLoc;
	private static int etiqCounter;
	
	// Constructor
	public GCI() {
		cuartetos = new ArrayList<>();
		despTemp = 0;
		despNoLoc = 0;
		etiqCounter = 0;
	}
	
	
	// TODO: hay que tener en cuente que puede tener espacios que se desee entre parámetros
	
	// genera cuarteto con el formato especifico
	public void addCuarteto(String op, String arg1Clase, String arg1Valor, String arg2Clase, String arg2Valor, String resClase, String resValor){
		String cuarteto = String.format("(%s, {%s, %s}, {%s, %s}, {%s, %s})", op, arg1Clase, arg1Valor, arg2Clase, arg2Valor, resClase, resValor);
		cuartetos.add(cuarteto);
	}
	

	
	// Genera un nuevo nombre para una variable temporal.
	public static String nuevaTemp() {
		return "t" + (despTemp++);
	}
	
	// Genera un nuevo nombre para una variable no local.
	public static String nuevaNoLoc() {
		return "nl" + (despNoLoc++);
	}
	
	
	// Genera una nueva etiqueta.
	public static String nuevaEtiqueta() {
        return "L" + (etiqCounter++);
    }
	
	
	// Obtiene la lista de cuartetos generados
    public List<String> getCuartetos() {
        return cuartetos;
    }
	

}
