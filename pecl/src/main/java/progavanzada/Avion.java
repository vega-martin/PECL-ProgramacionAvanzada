package progavanzada;

import java.util.Random;

public class Avion extends Thread {
    
    // Recurso compartido
    private Aeropuerto aeropuerto; 
    
    // Resto de atributos:
    private final String id;
    private final int maxPasajeros;
    private int numPasajeros = 0;
    
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
    private String generarIdAvion(int numAvion) {
        String letra1 = Character.toString((char) ('A' + r.nextInt(26)));
        String letra2 = Character.toString((char) ('A' + r.nextInt(26)));
        String numString = String.format("%04d", numAvion);
        return letra1 + letra2 + "-" + numString;
    }
    
    // Constructor del objeto Avion:
    public Avion(Aeropuerto aeropuerto, int numCompartido){
        this.id = generarIdAvion(numCompartido);
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
            this.aeropuerto.quitarAvionDeAreaEst(this);
            
            // Añadri el avion a las puertas de embarque
            this.aeropuerto.insertarPuertasEmbarque(this, true);
            
            // Llenar el avión de pasajeros:
            if (this.maxPasajeros <= this.aeropuerto.getViajeros()){ // Hay suficientes
                this.aeropuerto.restarViajerosAvion(this.aeropuerto.getViajeros() - this.maxPasajeros);
                this.numPasajeros = this.maxPasajeros;
                Thread.sleep(r.nextInt(2000) + 1000);
            }
            else { // No hay suficientes
                int numViajerosRecogidosTotales = 0;
                for (int i = 1; i <= 3; i++) {
                    int numViajerosIteracion = this.aeropuerto.getViajeros();
                    // El numero de viajeros disponibles no permite llenar el avión
                    if (numViajerosIteracion <= (this.maxPasajeros - this.numPasajeros)) {
                        // Recoger todos los viajeros disponibles
                        numViajerosRecogidosTotales = numViajerosIteracion;
                    }
                    // El numero de viajeros disponibles hace que se llene el avión
                    else {
                        // Recoger viajeros hasta llenar avion
                        numViajerosRecogidosTotales = numViajerosIteracion - (this.maxPasajeros - this.numPasajeros);
                    }
                    // Actualizar viajeros del aeropuerto y del avión
                    this.aeropuerto.restarViajerosAvion(this.aeropuerto.getViajeros() - numViajerosRecogidosTotales);
                    this.numPasajeros += numViajerosRecogidosTotales;
                    Thread.sleep(r.nextInt(2000) + 1000);
                    if (this.numPasajeros == this.maxPasajeros) {
                        break;
                    }
                    Thread.sleep(r.nextInt(4000) + 1000);
                }
            }
            
            // Abandona la puerta de embarque
            this.aeropuerto.quitarPuertasEmbarque(this);
            
            // Area de rodaje y pista
            this.aeropuerto.areaDeRodaje(this);
            this.aeropuerto.entrarPista(this);
            this.aeropuerto.salirPista(this);
            
            // Entrar en aerovía
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }
    }
}
