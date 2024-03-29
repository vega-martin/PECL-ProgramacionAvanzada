package progavanzada;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Bus extends Thread {
    
    // Recurso compartido --> variable atómica:
    private static final AtomicInteger varAtomBus = new AtomicInteger(0);
    
    private String id;
    private int pasajeros;
    private Aeropuerto aeropuerto;
    
    // Objeto random:
    Random r = new Random();

    public String getIdBus() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    // Incrementa var. atómica, y la devuelve con ceros a la izquierda:
    private String generarIdBus(){
        int numCompartido = varAtomBus.getAndIncrement();
        String numString = String.format("%04d", numCompartido);
        return "B-" + numString;
    }
    
    public Bus(Aeropuerto aeropuerto){
        this.id = generarIdBus();
        this.pasajeros = 0;
        this.aeropuerto = aeropuerto;
    }
    
    @Override
    public void run() {
     
        try {
            // Llegar al centro de la ciudad:
            Thread.sleep(r.nextInt(5) + 1);  
            
            // Llenar el bus:
            this.pasajeros = r.nextInt(51);
            
            // Viajar al aeropuerto:
            Thread.sleep(r.nextInt(6) + 5);
            
            // Llegar al aeropuerto y vaciar bus:
            /* ENVIAR EL VALOR DE this.pasajeros AL SISTEMA DEL AEROPUERTO */
            this.pasajeros = 0;
            
            // Esperar a que se vuelva a llenar el bus:
            Thread.sleep(r.nextInt(4) + 2);
            
            // Llenar el bus:
            this.pasajeros = r.nextInt(51);
            /* RESTAR EL VALOR DE this.pasajeros DEL SISTEMA DEL AEROPUERTO */
            
            // Viajar a la ciudad:
            Thread.sleep(r.nextInt(6) + 5);
            
            // Llegar a la ciudad:
            this.pasajeros = 0;
            
            // Esta última línea ya no hace falta porque el hilo debería morir
            /* EN CASO DE NECESITARSE, LOS PASAJEROS DE ESTE ÚLTIMO VIAJE DESAPARECEN DEL SISTEMA */  
            
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }        
    }
    
}
