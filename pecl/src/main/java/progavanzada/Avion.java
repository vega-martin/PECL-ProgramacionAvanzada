package progavanzada;

import java.util.Random;

public class Avion extends Thread {
    
    // Recurso compartido
    private Aeropuerto aeropuerto; 
    
    // Resto de atributos:
    private final String id;
    private final int maxPasajeros;
    private int numPasajeros;
    
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
            
            // Esperar a que toque el turno al avion:
            //synchronized (aeropuerto.colaEsperaPuertasEmbarque) {
                
                // Añadir el avion a la cola de espera:
            //    aeropuerto.colaEsperaPuertasEmbarque.offer(this);
                
                // Mientras que no sea su turno, espera:
            //    while (aeropuerto.colaEsperaPuertasEmbarque.peek() != this) {
            //        aeropuerto.colaEsperaPuertasEmbarque.wait();
            //    }
            //}
            
            // Encontrar la puerta de embarque libre:
            int puertaElegida = -1;
            synchronized (aeropuerto.getPuertasEmbarque()) {
                Avion[] puertas = this.aeropuerto.getPuertasEmbarque();
                
                for (int i = 1; i <=6; i++){

                    // La puerta 2 solo vale para desembarques:
                    if (i == 2) {
                        continue;                        
                    }

                    if (puertas[i] == null){
                        puertaElegida = i;
                        this.aeropuerto.setPuertasEmbarque(puertaElegida, this);
                        break;
                    }
                }
            }
            
            // Llenar el avión de pasajeros:
            if (this.maxPasajeros <= this.aeropuerto.getViajeros()){ // Hay suficientes
                this.aeropuerto.setViajeros(this.aeropuerto.getViajeros() - this.maxPasajeros);
                this.numPasajeros = this.maxPasajeros;
                Thread.sleep(r.nextInt(3000) + 1000);
            }
            else { // No hay suficientes
                int numViajerosRecogidos = 0;
                for (int i = 1; i <= 3; i++) {
                    int numViajeros = this.aeropuerto.getViajeros();
                    if (numViajeros <= (this.maxPasajeros - this.numPasajeros)) {
                        numViajerosRecogidos = numViajeros;
                    } 
                    else {
                        numViajerosRecogidos = numViajeros - (this.maxPasajeros - this.numPasajeros);
                    }
                    this.aeropuerto.setViajeros(numViajeros - numViajerosRecogidos);
                    this.numPasajeros += numViajerosRecogidos;
                    Thread.sleep(r.nextInt(3000) + 1000);
                    if (this.numPasajeros == this.maxPasajeros) {
                        break;
                    }
                    Thread.sleep(r.nextInt(5000) + 1000);
                }
            } 
            // Abandona la puerta de embarque
            this.aeropuerto.setPuertasEmbarque(puertaElegida, null);
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }
    }
}
