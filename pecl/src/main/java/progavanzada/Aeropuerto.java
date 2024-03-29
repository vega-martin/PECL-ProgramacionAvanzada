package progavanzada;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
    private final ArrayList<Avion> areaEstacionamiento = new ArrayList<>();
    
    private Avion[] taller = new Avion[20];
    private final Avion[] puertasEmbarque = new Avion[6];
    private Avion[] pistas = new Avion[4];
    
    private ArrayList<Avion> areaRodaje = new ArrayList<>();
    private Avion[] aerovias = new Avion[2];
    
    // Cola (FIFO) para las puertas de embarque:
    public final Queue<Avion> colaEsperaPuertasEmbarque = new LinkedList<>();
    
    // MECANISMOS PARA PROTEGER LAS ESTRUCTURAS DE DATOS:
    private final Lock lockViajeros = new ReentrantLock();
    private final Lock lockHangar = new ReentrantLock();
    private final Lock lockAreaEst = new ReentrantLock();
    
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
            avion.getAeropuerto().areaEstacionamiento.remove(avion);
        }
        finally {
            lockAreaEst.unlock();
        }  
    }
}
