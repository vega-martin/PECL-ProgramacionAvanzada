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
        String letra1 = Character.toString((char) ('A' + r.nextInt(26)));
        String letra2 = Character.toString((char) ('A' + r.nextInt(26)));
        int numCompartido = varAtomAvion.getAndIncrement();
        String numString = String.format("%04d", numCompartido);
        return letra1 + letra2 + "-" + numString;
    }
    
    // Constructor del objeto Avion:
    public Avion(Aeropuerto aeropuerto){
        this.id = generarIdAvion();
        this.maxPasajeros = r.nextInt(201) + 100;
        this.aeropuerto = aeropuerto;
        
        // Inclur el avion al hangar del aeropuerto:
        this.aeropuerto.incluirAvionEnHangar(this);
    }
    
    @Override
    public void run(){
        try {
            
            // Eliminar el avion del hangar:
            this.aeropuerto.quitarAvionDeHangar(this);
            
            // Añadir el avion al area de estacionamiento:
            this.aeropuerto.incluirAvionEnAreaEst(this);
            
            // Esperar a que toque el turno al avion:
            synchronized (aeropuerto.colaEsperaPuertasEmbarque) {
                
                // Añadir el avion a la cola de espera:
                aeropuerto.colaEsperaPuertasEmbarque.offer(this);
                
                // Mientras que no sea su turno, espera:
                while (aeropuerto.colaEsperaPuertasEmbarque.peek() != this) {
                    aeropuerto.colaEsperaPuertasEmbarque.wait();
                }
            }
            
            // Encontrar la puerta de embarque libre:
            synchronized (aeropuerto.getPuertasEmbarque()) {
                for (int i = 1; i <=6; i++){
                    
                    // La puerta 2 solo vale para desembarques:
                    if (i == 2) {
                        continue;                        
                    }
                    
                    Avion[] puertas = this.aeropuerto.getPuertasEmbarque();
                    if (puertas[i] == null){
                        puertas[i] = this;
                        this.aeropuerto.setPuertasEmbarque(puertas);
                        break;
                    }
                }
            }
            
            // Llenar el avión de pasajeros:
            if (this.getMaxPasajeros() <= this.aeropuerto.getViajeros()){ // Hay suficientes
                this.aeropuerto.setViajeros(this.aeropuerto.getViajeros() - this.getMaxPasajeros());
                Thread.sleep(r.nextInt(3000) + 1000);
            }
            
            else { // No hay suficientes
                this.aeropuerto.setViajeros(this.aeropuerto.getViajeros() - this.getMaxPasajeros());
                Thread.sleep(r.nextInt(3000) + 1000);
                //Primera espera:
                Thread.sleep(r.nextInt(5000) + 1000);
                
                // ...
                
            }            
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }
    }
}
