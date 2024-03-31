package progavanzada;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private final String nombreArchivo;
    private final DateTimeFormatter formatter;
    private final Object monitor = new Object();

    public Log(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Crear el archivo de registro si no existe
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
        } catch (IOException e) {}
    }

    public void escribirEvento(String evento) {
        synchronized (monitor) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
                String fechaHora = LocalDateTime.now().format(formatter);
                writer.write("[" + fechaHora + "] " + evento + "\n");
            } catch (IOException e) {}
        }
    }
}



