package progavanzada;

import java.util.Random;

public class Bus extends Thread {
    
    // Recurso compartido
    private Aeropuerto aeropuerto;
    
    // Resto de atributos:
    private String id;
    private int pasajeros;
    
    // Objeto random:
    Random r = new Random();
    
    Log log = new Log("evolucionAeropuerto.txt");

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
    private String generarIdBus(int numBus){
        String numString = String.format("%04d", numBus);
        return "B-" + numString;
    }
    
    public Bus(Aeropuerto aeropuerto, int numCompartido){
        this.id = generarIdBus(numCompartido);
        this.pasajeros = 0;
        this.aeropuerto = aeropuerto;
    }
    
    @Override
    public void run() {
     
        try {
            
            while (true) {
                
                // Llegar al centro de la ciudad y esperar a pasajeros:
                log.escribirEvento("BUS " + this.getIdBus() + " llega a la ciudad.");
                Thread.sleep(r.nextInt(5000) + 1000);

                // Llenar el bus:
                this.pasajeros = r.nextInt(51);
                log.escribirEvento("BUS " + this.getIdBus() + " recoge " + this.pasajeros + " pasajeros.");

                // Viajar al aeropuerto:
                log.escribirEvento("BUS " + this.getIdBus() + " viajando al aeropuerto.");
                Thread.sleep(r.nextInt(6000) + 5000);
                
                // Llegar al aeropuerto y vaciar bus:
                this.aeropuerto.sumarViajerosBus(this.pasajeros); // Incorporar pasajeros al aeropuerto
                log.escribirEvento("BUS " + this.getIdBus() + " deja " + this.pasajeros + " pasajeros en el aeropuerto y queda vacío.");
                this.pasajeros = 0;

                // Esperar y llenar el bus:
                Thread.sleep(r.nextInt(4000) + 2000);
                this.pasajeros = r.nextInt(51);
                this.aeropuerto.restarViajerosBus(this.pasajeros); // Restar pasajeros del aeropuerto
                log.escribirEvento("BUS " + this.getIdBus() + " se llena.");
                
                // Viajar a la ciudad:
                log.escribirEvento("BUS " + this.getIdBus() + " viajando a la ciudad.");
                Thread.sleep(r.nextInt(6000) + 5000);

                // Llegar a la ciudad:
                log.escribirEvento("BUS " + this.getIdBus() + " deja " + this.pasajeros + " pasajeros y queda vacío en la ciudad.");
                this.pasajeros = 0;

                // REPETIR INFINITAMENTE EL BUCLE
            }
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }        
    }
    
}
