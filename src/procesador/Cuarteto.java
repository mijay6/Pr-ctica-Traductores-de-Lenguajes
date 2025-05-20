package procesador;


public class Cuarteto {
	
	String operador;
    String arg1;
    String arg2;
    String resultado;
	

    public Cuarteto(String operador, String arg1, String arg2, String resultado) {
        this.operador = operador;
        this.arg1 = arg1 == null || arg1.isEmpty() ? "-" : arg1;
        this.arg2 = arg2 == null || arg2.isEmpty() ? "-" : arg2;
        this.resultado = resultado == null || resultado.isEmpty() ? "-" : resultado;
    }

    
    @Override
    public String toString() {
        // Formato según formato_fichero_cuartetos.pdf
        return String.format("(%s, %s, %s, %s)", operador, arg1, arg2, resultado);
    }
    
    // Método para formatear argumentos como {CLASE, VALOR}
    public static String formatArg(String clase, String valor) {
        return String.format("{%s, %s}", clase, valor);
    }
    
    public static String formatArg(String clase, int valor) {
        return String.format("{%s, %d}", clase, valor);
    }
    
    
}
