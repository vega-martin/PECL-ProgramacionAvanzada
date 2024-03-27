package progavanzada;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Avion extends Thread {
    
    // Recurso compartido --> variable atómica:
    private static final AtomicInteger varAtomAvion = new AtomicInteger(0);
    
    // Resto de atributos:
    private final String id;
    private final int maxPasajeros;
    private Aeropuerto aeropuerto;   
    
    // Objeto random, usado en diferentes métodos:
    Random r = new Random();

    public String getIdAvion() {
        return id;
    }

    public int getMaxPasajeros() {
        return maxPasajeros;
    }    

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }    
    
    // Genera el ID completo del avión:
    private String generarIdAvion() {
        char letra1 = (char) ('A' + r.nextInt(26));
        char letra2 = (char) ('A' + r.nextInt(26));
        int numCompartido = varAtomAvion.incrementAndGet();
        String numString = String.format("%04d", numCompartido);
        return letra1 + letra2 + "-" + numString;
    }
    
    // Constructor del objeto Avion:
    public Avion(Aeropuerto aeropuerto){
        this.id = generarIdAvion();
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
            
            // Esperar a que toque el turno al avion:
            while (this.aeropuerto.getColaPuertasEmbarque().peek() != this)
                Thread.sleep(1000); // ESTO ES UN MIERDÓN PERO POR SEGUIR HACIENDO COSAS DEBAJO
            
            // Encontrar la puerta de embarque libre:
            for (int i = 0; i <=6 && i != 2; i++){
                Avion[] puertas = this.aeropuerto.getPuertasEmbarque();
                if (puertas[i] == null){
                    puertas[i] = this;
                    this.aeropuerto.setPuertasEmbarque(puertas);
                    break;
                }
            }
            // TAMBIÉN ES UN POCO MOJÓN
            
            // Llenar el avión de pasajeros:
            if (this.getMaxPasajeros() <= this.aeropuerto.getViajeros()){ // Hay suficientes
                this.aeropuerto.setViajeros(this.aeropuerto.getViajeros() - this.getMaxPasajeros());
                Thread.sleep(r.nextInt(3) + 1);
            }
            
            else { // No hay suficientes
                this.aeropuerto.setViajeros(this.aeropuerto.getViajeros() - this.getMaxPasajeros());
                Thread.sleep(r.nextInt(3) + 1);
                //Primera espera:
                Thread.sleep(r.nextInt(5) + 1);
                
                // ...
                
            }
            
            
            
            
        }
        // Da error porque todavía no hay sleep arriba
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }
    }
}
