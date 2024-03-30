
package progavanzada;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private final String nombreArchivo;
    private FileWriter escritor;

    public Log(String nombre) {
        this.nombreArchivo = nombre;
        try {
            escritor = new FileWriter(nombreArchivo);
        } 
        catch (IOException e) {}
    }

    public synchronized void escribirEvento(String evento) {
        try {
            
            // Tomar la fecha y hora actuales:
            LocalDateTime fechaActual = LocalDateTime.now();
            
            // Formateamos la fecha a nuestro gusto:
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
            String fechaFormateada = fechaActual.format(formato);
            
            // Escribimos en el fichero:
            escritor.write("[" + fechaFormateada + "] " + evento + "\n");
            escritor.flush();
        } 
        catch (IOException e) {}
    }
}

