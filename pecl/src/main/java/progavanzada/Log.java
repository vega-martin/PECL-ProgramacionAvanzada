
package progavanzada;

import java.io.FileWriter;
import java.io.IOException;

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
            escritor.write(evento + "\n");
            escritor.flush();
        } 
        catch (IOException e) {}
    }

    public synchronized void cerrarArchivo() {
        try {
            escritor.close();
        } 
        catch (IOException e) {}
    }
}

