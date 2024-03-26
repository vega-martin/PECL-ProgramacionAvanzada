package progavanzada;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Avion extends Thread {
    
    // Recurso compartido --> variable atómica:
    private static final AtomicInteger ultimoNum = new AtomicInteger(0);
    
    // Resto de atributos:
    private String id;
    private int maxPasajeros;
    private String estado;
    private Aeropuerto aeropuerto;
    
    // Objeto random, usado en diferentes métodos:
    Random r = new Random();
    
    // Incrementa var. atómica, y la devuelve con ceros a la izquierda:
    public int generarNumAvion(){
        int numCompartido = ultimoNum.incrementAndGet();
        String numString = String.format("%04d", numCompartido);
        return Integer.parseInt(numString);
    }

    // Genera las letras aleatorias del ID del avion:
    public String generarLetrasAvion(){
        char letra1 = (char) ('A' + r.nextInt(26));
        char letra2 = (char) ('A' + r.nextInt(26));
        return String.valueOf(letra1) + String.valueOf(letra2);
    }
    
    // Constructor del objeto Avion:
    public Avion(String estado, Aeropuerto aeropuerto){
        this.id = generarLetrasAvion() + "-" + generarNumAvion();
        this.maxPasajeros = r.nextInt(201) + 100;
        this.estado = estado;
        this.aeropuerto = aeropuerto;
    }
}
