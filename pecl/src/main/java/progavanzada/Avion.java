package progavanzada;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Avion extends Thread {
    
    // Recursos compartidos:
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
    
    // Instancia de Log para registrar eventos:
    Log log = new Log("evolucionAeropuerto.txt");
    
    // Instancia de Paso para pausar el programa:
    private final Paso paso;

    // Getters y setters:
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
    public Avion(Aeropuerto aeropuerto, int numCompartido, Aeropuerto aeropuertoDestino, Paso p){
        this.id = generarIdAvion(numCompartido);
        this.maxPasajeros = r.nextInt(201) + 100;
        this.aeropuerto = aeropuerto;
        this.destino = aeropuertoDestino;
        this.paso = p;
        
        // Inclur el avión directamente al hangar del aeropuerto:
        this.aeropuerto.incluirAvionEnHangar(this);
    }
    
    @Override
    public void run(){
        try {
            while (true) {
                
                // Actualizar el número de viajes realizados:                
                numViajes++;

                // Eliminar el avión del hangar:
                this.aeropuerto.quitarAvionDeHangar(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " sale del hangar de " + this.aeropuerto.getNombre() + " y se dirige al área de estacionamiento.");
                paso.mirar();
                  
                // Área de estacionamiento:
                this.aeropuerto.incluirAvionEnAreaEst(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " entra al área de estacionamiento y espera a que haya una puerta de embarque libre.");
                paso.mirar();
                this.aeropuerto.quitarAvionDeAreaEst(this, despegando);

                // Añadir el avión a las puertas de embarque
                this.aeropuerto.insertarPuertasEmbarque(this, despegando);
                paso.mirar();
                
                // Llenar el avión de pasajeros:
                recogerPasajeros();
                log.escribirEvento("AVION " + this.getIdAvion() + " accede a una puerta de embarque y recoge " + this.numPasajeros + " pasajeros.");
                paso.mirar();
                
                // Abandonar la puerta de embarque:
                this.aeropuerto.quitarPuertasEmbarque(this);

                // Área de rodaje:
                log.escribirEvento("AVION " + this.getIdAvion() + " accede a un area de rodaje y espera pista para despegue.");
                this.aeropuerto.entrarAreaDeRodaje(this);
                paso.mirar();
                this.aeropuerto.salirAreaDeRodaje(this, despegando);

                // Pistas:
                log.escribirEvento("AVION " + this.getIdAvion() + " accede a una pista y se prepara para despegar.");
                this.aeropuerto.entrarPista(this);
                Thread.sleep(r.nextInt(2000) + 1000);
                Thread.sleep(r.nextInt(4000) + 1000);
                paso.mirar();
                this.aeropuerto.salirPista(this);

                // Aerovías:
                log.escribirEvento("AVION " + this.getIdAvion() + " en el aire, aerovía " + this.aeropuerto.getNombre() + "-" + this.destino.getNombre() + ".");
                this.aeropuerto.entrarAerovia(this, despegando);
                
                // Cambiar aeropuerto, status (despegando), y entrar en la aerovia de destino:
                Aeropuerto temp = aeropuerto;
                aeropuerto = destino;
                destino = temp;
                despegando = false;
                this.aeropuerto.entrarAerovia(this, despegando);
                
                // Trayecto:
                Thread.sleep(r.nextInt(15000) + 15000);
                paso.mirar();
                
                // Buscar pista:                
                log.escribirEvento("AVION " + this.getIdAvion() + " busca una pista libre en " + this.aeropuerto.getNombre() + ".");
                int pista = -1;
                do {
                    pista = this.aeropuerto.buscarPista();
                    if (pista == -1) {
                        log.escribirEvento(" ** AVION " + this.getIdAvion() + " no encuentra pista libre en " + this.aeropuerto.getNombre() + ".");
                        Thread.sleep(r.nextInt(4000) + 1000);    
                    }
                }                            
                while(pista == -1);                                  
                log.escribirEvento("AVION " + this.getIdAvion() + " ha encontrado una pista libre en " + this.aeropuerto.getNombre() + ", procede a aterrizar.");
                
                // Salir de las aerovías:
                this.destino.salirAerovia(this);
                this.aeropuerto.salirAerovia(this);

                // Pista:
                log.escribirEvento("AVION " + this.getIdAvion() + " ha aterrizado exitosamente en " + this.aeropuerto.getNombre() + ".");
                this.aeropuerto.entrarPista(this);
                Thread.sleep(r.nextInt(4000) + 1000);
                paso.mirar();
                this.aeropuerto.salirPista(this);
                this.aeropuerto.liberarPista(pista);

                // Área de rodaje:
                log.escribirEvento("AVION " + this.getIdAvion() + " accede al area de rodaje, hace comprobaciones y busca una puerta de embarque para desembarcar.");
                this.aeropuerto.entrarAreaDeRodaje(this);
                Thread.sleep(r.nextInt(2000) + 3000);
                paso.mirar();
                this.aeropuerto.salirAreaDeRodaje(this, despegando);

                // Puertas de embarque:
                this.aeropuerto.insertarPuertasEmbarque(this, despegando);
                log.escribirEvento("AVION " + this.getIdAvion() + " desembarca " + this.numPasajeros + " pasajeros.");
                Thread.sleep(r.nextInt(4000) + 1000);
                paso.mirar();
                this.aeropuerto.sumarViajeros(numPasajeros);
                numPasajeros = 0;
                paso.mirar();
                this.aeropuerto.quitarPuertasEmbarque(this);

                // Área de estacionamiento:
                this.aeropuerto.incluirAvionEnAreaEst(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " se dirije al área de estacionamiento y se pone a la cola para entrar al taller.");
                Thread.sleep(r.nextInt(4000) + 1000);
                paso.mirar();
                this.aeropuerto.quitarAvionDeAreaEst(this, despegando);

                // Añadir el avión al taller:
                this.aeropuerto.entrarTaller(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " entrando al taller.");
                Thread.sleep(1000);
                paso.mirar();

                // Elegir tipo de revisión:
                if ((numViajes % 15) == 0) {
                    // Inspeccion en profundidad:
                    log.escribirEvento("AVION " + this.getIdAvion() + " tiene " + this.numViajes + " viajes totales. Llevando a cabo una inspección en profundidad.");
                    Thread.sleep(r.nextInt(5000) + 5000);
                    paso.mirar();
                } else {
                    // Inspeccion rutinaria:
                    log.escribirEvento("AVION " + this.getIdAvion() + " tiene " + this.numViajes + " viajes totales. Llevando a cabo una inspección rutinaria.");
                    Thread.sleep(r.nextInt(4000) + 1000);
                    paso.mirar();
                }
                
                log.escribirEvento("AVION " + this.getIdAvion() + " saliendo del taller.");
                Thread.sleep(1000);
                paso.mirar();
                this.aeropuerto.salirTaller(this);

                // Añadir avión al hangar: 
                this.aeropuerto.incluirAvionEnHangar(this);
                log.escribirEvento("AVION " + this.getIdAvion() + " entrando en el hangar de " + this.aeropuerto.getNombre() + ".");
                paso.mirar();
                
                // Decidir si esperar o continuar:
                double decision = r.nextDouble();
                if (decision < 0.5){
                    // Reposar en el hangar:
                    paso.mirar();
                    log.escribirEvento("AVION " + this.getIdAvion() + " reposa en el hangar de " + this.aeropuerto.getNombre() + ".");
                    Thread.sleep(r.nextInt(15000) + 15000);
                }
                else {
                    // Continuar con el ciclo de vida:
                    paso.mirar();
                }
            }
        }
        catch (InterruptedException ie){
            System.out.println("Se ha interrumpido el sistema");
        } catch (RemoteException ex) {
            Logger.getLogger(Avion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Metodo útil para el ciclo de vida del avión:    
    private void recogerPasajeros() throws InterruptedException, RemoteException {
        
        numPasajeros = this.aeropuerto.getMaxViajeros(maxPasajeros);
        Thread.sleep(r.nextInt(2000) + 1000);
        paso.mirar();
        
        if (numPasajeros != maxPasajeros) {
            Thread.sleep(r.nextInt(4000) + 1000);
            paso.mirar();
            int pas = 0;
            for (int i = 1; i < 3; i++) {
                pas = maxPasajeros - numPasajeros;
                numPasajeros += this.aeropuerto.getMaxViajeros(pas);
                Thread.sleep(r.nextInt(2000) + 1000);
                paso.mirar();
                if (this.numPasajeros == this.maxPasajeros) {
                    break;
                }
            }
        }
    }
    
} // Fin clase Avión
