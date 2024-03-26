package progavanzada;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Avion extends Thread {
    
    // Recurso compartido --> variable atómica:
    private static final AtomicInteger varAtomAvion = new AtomicInteger(0);
    
    // Resto de atributos:
    private String id;
    private int maxPasajeros;
    private Aeropuerto aeropuerto;
    
    // Objeto random, usado en diferentes métodos:
    Random r = new Random();
    
    // Incrementa var. atómica, y la devuelve con ceros a la izquierda:
    public int generarNumAvion(){
        int numCompartido = varAtomAvion.incrementAndGet();
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
    public Avion(Aeropuerto aeropuerto){
        this.id = generarLetrasAvion() + "-" + generarNumAvion();
        this.maxPasajeros = r.nextInt(201) + 100;
        this.aeropuerto = aeropuerto;
        
        // Inclur el avion al hangar del aeropuerto:
        ArrayList<Avion> oldHangar = this.aeropuerto.getHangar();
        oldHangar.add(this);
        this.aeropuerto.setHangar(oldHangar);
    }
    
    @Override
    public void run(){
        try {
            
            // Eliminar el avion del hangar:
            ArrayList<Avion> oldHangar = this.aeropuerto.getHangar();
            oldHangar.add(this);
            this.aeropuerto.setHangar(oldHangar);
            
            // Añadir el avion al area de estacionamiento:
            ArrayList<Avion> oldAreaEst = this.aeropuerto.getAreaEstacionamiento();
            oldAreaEst.add(this);
            this.aeropuerto.setAreaEstacionamiento(oldAreaEst);
            
            //...
            
            // FALTAN MECANISMOS DE PROTECCIÓN DE DATOS
            
            
            
            
        }
        // Da error porque todavía no hay sleep arriba
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }
    }
}
