package progavanzada;

import java.util.Random;

public class Avion extends Thread {
    
    // Recurso compartido
    private Aeropuerto aeropuerto;
    private Aeropuerto destino;
    
    // Resto de atributos:
    private final String id;
    private final int maxPasajeros;
    private int numPasajeros = 0;
    private boolean despegando = true;
    private int numViajes = 0;
    
    // Objeto random, usado en diferentes métodos:
    Random r = new Random();
    
    Log log = new Log("evolucionAeropuerto.txt");

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
    public Avion(Aeropuerto aeropuerto, int numCompartido, Aeropuerto aeropuertoDestino){
        this.id = generarIdAvion(numCompartido);
        this.maxPasajeros = r.nextInt(201) + 100;
        this.aeropuerto = aeropuerto;
        this.destino = aeropuertoDestino;
        
        // Inclur el avion al hangar del aeropuerto:
        this.aeropuerto.incluirAvionEnHangar(this);
    }
    
    @Override
    public void run(){
        try {
            while (true) {
                
                numViajes ++;

                // Eliminar el avion del hangar:
                this.aeropuerto.quitarAvionDeHangar(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " sale del hangar de " + this.aeropuerto.getNombre() + " y se dirige al área de estacionamiento.");

                // Añadir el avion al area de estacionamiento:
                this.aeropuerto.incluirAvionEnAreaEst(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " entra al área de estacionamiento y espera a que haya una puerta de embarque libre.");
                this.aeropuerto.quitarAvionDeAreaEst(this, despegando);

                // Añadir el avion a las puertas de embarque
                this.aeropuerto.insertarPuertasEmbarque(this, despegando);
                
                // Llenar el avión de pasajeros:
                //numPasajeros = this.aeropuerto.embarcarViajerosAvion(this.maxPasajeros);
                recogerPasajeros();
                log.escribirEvento("AVION " + this.getIdAvion() + " accede a una puerta de embarque y recoge " + this.numPasajeros + " pasajeros.");
                // Abandona la puerta de embarque
                this.aeropuerto.quitarPuertasEmbarque(this);

                // Area de rodaje
                log.escribirEvento("AVION " + this.getIdAvion() + " accede a un area de rodaje y espera pista para despegue.");
                this.aeropuerto.entrarAreaDeRodaje(this);
                this.aeropuerto.salirAreaDeRodaje(this, despegando);

                // Pista
                log.escribirEvento("AVION " + this.getIdAvion() + " accede a una pista y se prepara para despegar.");
                this.aeropuerto.entrarPista(this);
                Thread.sleep(r.nextInt(2000) + 1000);
                Thread.sleep(r.nextInt(4000) + 1000);
                this.aeropuerto.salirPista(this);

                // Aerovía
                log.escribirEvento("AVION " + this.getIdAvion() + " en el aire, aerovía " + this.aeropuerto.getNombre() + "-" + this.destino.getNombre() + ".");
                this.aeropuerto.entrarAerovia(this, despegando);
                // Cambiar aeropuerto, estatus (despegando), entrar en aerovia destino
                Aeropuerto temp = aeropuerto;
                aeropuerto = destino;
                destino = temp;
                despegando = false;
                this.aeropuerto.entrarAerovia(this, despegando);
                // Trayecto
                Thread.sleep(r.nextInt(15000) + 15000);
                // Buscar pista -> espera activa?
                /*
                log.escribirEvento("AVION " + this.getIdAvion() + " busca una pista libre en " + this.aeropuerto.getNombre() + ".");
                while(pistas ocupadas){
                    Thread.sleep(r.nextInt(4000) + 1000);
                }
                log.escribirEvento("AVION " + this.getIdAvion() + " ha encontrado una pista libre en " + this.aeropuerto.getNombre() + ", procede a aterrizar.");
                */
                // Salir aerovias
                this.destino.salirAerovia(this);
                this.aeropuerto.salirAerovia(this);

                // Pista
                log.escribirEvento("AVION " + this.getIdAvion() + " ha aterrizado exitosamente en " + this.aeropuerto.getNombre() + ".");
                this.aeropuerto.entrarPista(this);
                Thread.sleep(r.nextInt(4000) + 1000);
                this.aeropuerto.salirPista(this);

                // Area de rodaje
                log.escribirEvento("AVION " + this.getIdAvion() + " accede al area de rodaje, hace comprobaciones y busca una puerta de embarque para desembarcar.");
                this.aeropuerto.entrarAreaDeRodaje(this);
                Thread.sleep(r.nextInt(2000) + 3000);
                this.aeropuerto.salirAreaDeRodaje(this, despegando);

                // Añadir el avion a las puertas de embarque
                this.aeropuerto.insertarPuertasEmbarque(this, despegando);
                log.escribirEvento("AVION " + this.getIdAvion() + " desembarca " + this.numPasajeros + " pasajeros.");
                Thread.sleep(r.nextInt(4000) + 1000);
                this.aeropuerto.sumarViajerosAvion(numPasajeros);
                numPasajeros = 0;
                this.aeropuerto.quitarPuertasEmbarque(this);

                // Añadir el avion al area de estacionamiento:
                this.aeropuerto.incluirAvionEnAreaEst(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " se dirije al área de estacionamiento y se pone a la cola para entrar al taller.");
                Thread.sleep(r.nextInt(4000) + 1000);
                this.aeropuerto.quitarAvionDeAreaEst(this, despegando);

                // Añadir el avion al taller
                this.aeropuerto.entrarTaller(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " entrando al taller.");
                Thread.sleep(1000);

                // Elegir tipo de revision
                if ((numViajes % 15) == 0) {
                    // Inspeccion en profuncidad
                    log.escribirEvento("AVION " + this.getIdAvion() + " tiene " + this.numViajes + " viajes totales. Llevando a cabo una inspección en profuncidad.");
                    Thread.sleep(r.nextInt(5000) + 5000);
                } else {
                    // Inspeccion rutinaria
                    log.escribirEvento("AVION " + this.getIdAvion() + " tiene " + this.numViajes + " viajes totales. Llevando a cabo una inspección rutinaria.");
                    Thread.sleep(r.nextInt(4000) + 1000);
                }
                
                log.escribirEvento("AVION " + this.getIdAvion() + " saliendo del taller.");
                Thread.sleep(1000);
                this.aeropuerto.salirTaller(this);

                // Añadir avion al hangar y decirdir si esperar o continar
                this.aeropuerto.incluirAvionEnHangar(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " entrando en el hangar de " + this.aeropuerto.getNombre() + ".");
                double decision = r.nextDouble();
                if (decision < 0.5){
                    log.escribirEvento("AVION " + this.getIdAvion() + " reposa en el hangar de " + this.aeropuerto.getNombre() + ".");
                    Thread.sleep(r.nextInt(15000) + 15000);
                }
                else {
                    continue;
                }
            }
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        }
    }
    
    // Metodos utiles para el ciclo de vida del avion
    
    private void recogerPasajeros() throws InterruptedException {
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
    }
    
}
