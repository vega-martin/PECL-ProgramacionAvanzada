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
            
            while (true) {
                
                // Llegar al centro de la ciudad:
                Thread.sleep(r.nextInt(5000) + 1000);  
                System.out.println("BUS " + this.getIdBus() + " llega a la ciudad");

                // Llenar el bus:
                this.pasajeros = r.nextInt(51);
                System.out.println("BUS " + this.getIdBus() + " llena pasajeros");

                // Viajar al aeropuerto:
                Thread.sleep(r.nextInt(6000) + 5000);
                System.out.println("BUS " + this.getIdBus() + " viaja al aeropuerto");

                /* ENVIAR EL VALOR DE this.pasajeros AL SISTEMA DEL AEROPUERTO */
                this.aeropuerto.sumarViajerosBus(this.pasajeros);
                System.out.println("BUS " + this.getIdBus() + " dropea pasajeros");

                // Llegar al aeropuerto y vaciar bus:
                this.pasajeros = 0;
                System.out.println("BUS " + this.getIdBus() + " queda vacio");

                // Esperar a que se vuelva a llenar el bus:
                Thread.sleep(r.nextInt(4000) + 2000);
                System.out.println("BUS " + this.getIdBus() + " espera que se llene");

                // Llenar el bus:
                this.pasajeros = r.nextInt(51);
                System.out.println("BUS " + this.getIdBus() + " se llena");
                
                /* RESTAR EL VALOR DE this.pasajeros DEL SISTEMA DEL AEROPUERTO */
                this.aeropuerto.restarViajerosBus(this.pasajeros);

                // Viajar a la ciudad:
                Thread.sleep(r.nextInt(6000) + 5000);
                System.out.println("BUS " + this.getIdBus() + " llega a la ciudad");

                // Llegar a la ciudad:
                this.pasajeros = 0;
                System.out.println("BUS " + this.getIdBus() + " vacio en la ciudad");

                // REPETIR INFINITAMENTE EL BUCLE
            }
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }        
    }
    
}
