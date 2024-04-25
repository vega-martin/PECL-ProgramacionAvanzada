package progavanzada;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aeropuerto {
    
    private String nombre;
    private int viajeros;
    
    
    /* Estructuras de datos para las zonas de cada avión
        - ARRAYLIST --> CAPACIDAD ILIMITADA
        - ARRAY ESTATICO --> CAPACIDAD LIMITADA
    */
    
    private final ArrayList<Avion> hangar = new ArrayList<>();
    private final Queue<Avion> areaEstacionamiento = new LinkedList<>();
    
    private Avion[] taller = new Avion[20];
    private Avion[] puertasEmbarque = new Avion[6];
    private Avion[] pistas = new Avion[4];
    
    private ArrayList<Avion> areaRodaje = new ArrayList<>();
    private Avion[] aerovias = new Avion[2];
    
    // Cola (FIFO) para las puertas de embarque:
    //public final Queue<Avion> colaEsperaPuertasEmbarque = new LinkedList<>();
    
    // MECANISMOS PARA PROTEGER LAS ESTRUCTURAS DE DATOS:
    private final Lock lockViajeros = new ReentrantLock();
    private final Lock lockHangar = new ReentrantLock();
    private final Lock lockAreaEst = new ReentrantLock();
    private final Lock lockPuertasEmb = new ReentrantLock();
    private final Condition esperarPuertasEmb = lockPuertasEmb.newCondition();
    private final Semaphore semPuertasEmb = new Semaphore(6, true);
    
    //

    public synchronized String getNombre() {
        return nombre;
    }

    public synchronized void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public synchronized int getViajeros() {
        return viajeros;
    }

    public synchronized void setViajeros(int viajeros) {
        this.viajeros = viajeros;
    }

    public void insertarPuertasEmbarque(Avion avion, boolean embarque) throws InterruptedException {
        semPuertasEmb.acquire();
        lockPuertasEmb.lock();
        try {
            for (int i = 1; i <= 6; i++){
                // La puerta 1 solo vale para embarques y la 2 solo vale para desembarques:
                if (((i == 1) && (!embarque)) || ((i == 2) && (embarque))){
                    continue;                        
                }
                // Elige puerta
                if (puertasEmbarque[i] == null ) {
                    this.puertasEmbarque[i] = avion;
                }
            }
        } finally { lockPuertasEmb.unlock(); }
    }
    
    public void quitarPuertasEmbarque(Avion avion) throws InterruptedException {
        lockPuertasEmb.lock();
        try {
            for (int i = 1; i <= 6; i++){
                if (this.puertasEmbarque[i] == avion) {
                    this.puertasEmbarque[i] = null;
                    break;
                }
            }
            esperarPuertasEmb.signal();
        } finally { lockPuertasEmb.unlock(); }
        semPuertasEmb.release();
    }
    
    public Aeropuerto(String nombre){
        this.nombre = nombre;
    }
    
    public void sumarViajerosBus(int pasajerosBus){ 
        try {
            lockViajeros.lock();
            viajeros += pasajerosBus;  
        }
        finally {
            lockViajeros.unlock();
        }
    }
    
    public void restarViajerosBus(int pasajerosBus){
        try {
            lockViajeros.lock();
            viajeros -= pasajerosBus;  
        }
        finally {
            lockViajeros.unlock();
        }
    }
    
    public int contarAvionesHangar(){
        return hangar.size();
    }
    
    public int contarAvionesAreaEst(){
        return areaEstacionamiento.size();
    }

    public void incluirAvionEnHangar(Avion avion){
        try {
            lockHangar.lock();
            avion.getAeropuerto().hangar.add(avion);
        }
        finally {
            lockHangar.unlock();
        }  
    }
    
    public void quitarAvionDeHangar(Avion avion){
        try {
            lockHangar.lock();
            avion.getAeropuerto().hangar.remove(avion);
        }
        finally {
            lockHangar.unlock();
        }  
    }
    
    public void incluirAvionEnAreaEst(Avion avion){
        try {
            lockAreaEst.lock();
            avion.getAeropuerto().areaEstacionamiento.add(avion);
        } 
        finally {
            lockAreaEst.unlock();
        }  
    }
    
    public void quitarAvionDeAreaEst(Avion avion){
        try {
            lockAreaEst.lock();
            while ((puertasEmbarque[0] != null) && (puertasEmbarque[1] != null) &&
                   (puertasEmbarque[2] != null) && (puertasEmbarque[3] != null) &&
                   (puertasEmbarque[4] != null) && (puertasEmbarque[5] != null)){
                esperarPuertasEmb.await();
            }
            avion.getAeropuerto().areaEstacionamiento.remove(avion);
        } catch (InterruptedException e) {}
        finally {
            lockAreaEst.unlock();
        }  
    }
}
