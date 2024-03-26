package progavanzada;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Bus extends Thread {
    
    // Recurso compartido --> variable atómica:
    private static final AtomicInteger varAtomBus = new AtomicInteger(0);
    
    private String id;
    private String estado;
    private int pasajeros;
    private Aeropuerto aeropuerto;
    
    // Objeto random:
    Random r = new Random();
    
    // Incrementa var. atómica, y la devuelve con ceros a la izquierda:
    public int generarNumBus(){
        int numCompartido = varAtomBus.incrementAndGet();
        String numString = String.format("%04d", numCompartido);
        return Integer.parseInt(numString);
    }
    
    public Bus(String estado){
        this.id = "B-" + generarNumBus();
        this.estado = estado;
        this.pasajeros = r.nextInt(51);
        /*
        if (varAtomAvion.get() % 2 == 0){
            this.aeropuerto = Barajas;
        else {
            this.aeropuerto = Prat;        
        }*/
    }
    
}
