package progavanzada;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bus extends Thread {
    
    // Recurso compartido:
    private Aeropuerto aeropuerto;
    
    // Resto de atributos:
    private String id;
    private int pasajeros;
    
    // Objeto random:
    Random r = new Random();
    
    // Instancia de Log para registrar eventos:
    Log log = new Log("evolucionAeropuerto.txt");
    
    // Instancia de Paso para pausar el programa:
    private final Paso paso;

    // Getters y setters:
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

    // Genera el ID completo del bus:
    private String generarIdBus(int numBus){
        String numString = String.format("%04d", numBus);
        return "B-" + numString;
    }
    
    // Constructor del objeto Bus:
    public Bus(Aeropuerto aeropuerto, int numCompartido, Paso p){
        this.id = generarIdBus(numCompartido);
        this.pasajeros = 0;
        this.aeropuerto = aeropuerto;
        this.paso = p;
    }
    
    @Override
    public void run() {
     
        try {
            
            while (true) {
                
                // Llegar al centro de la ciudad y esperar a pasajeros:
                log.escribirEvento("BUS " + this.getIdBus() + " llega a la ciudad.");
                Thread.sleep(r.nextInt(5000) + 1000);
                paso.mirar();

                // Llenar el bus:
                this.pasajeros = r.nextInt(51);
                log.escribirEvento("BUS " + this.getIdBus() + " recoge " + this.pasajeros + " pasajeros.");
                paso.mirar();

                // Viajar al aeropuerto:
                log.escribirEvento("BUS " + this.getIdBus() + " viajando al aeropuerto.");
                Thread.sleep(r.nextInt(6000) + 5000);
                paso.mirar();
                
                // Llegar al aeropuerto y vaciar bus:
                this.aeropuerto.sumarViajeros(this.pasajeros); // Incorporar pasajeros al aeropuerto
                log.escribirEvento("BUS " + this.getIdBus() + " deja " + this.pasajeros + " pasajeros en el aeropuerto y queda vacío.");
                this.pasajeros = 0;
                paso.mirar();

                // Esperar y llenar el bus:
                Thread.sleep(r.nextInt(4000) + 2000);
                this.pasajeros = r.nextInt(51);
                while(this.pasajeros > this.aeropuerto.getViajeros()){
                    this.pasajeros--;
                }
                this.aeropuerto.restarViajeros(this.pasajeros); // Restar pasajeros del aeropuerto
                log.escribirEvento("BUS " + this.getIdBus() + " se llena.");
                paso.mirar();
                
                // Viajar a la ciudad:
                log.escribirEvento("BUS " + this.getIdBus() + " viajando a la ciudad.");
                Thread.sleep(r.nextInt(6000) + 5000);
                paso.mirar();

                // Llegar a la ciudad:
                log.escribirEvento("BUS " + this.getIdBus() + " deja " + this.pasajeros + " pasajeros y queda vacío en la ciudad.");
                this.pasajeros = 0;
                paso.mirar();

                // REPETIR INFINITAMENTE EL BUCLE
            }
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        } catch (RemoteException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
} // Fin clase Bus
