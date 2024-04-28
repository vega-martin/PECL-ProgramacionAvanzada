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
    private ArrayList<Avion>[] aerovias = new ArrayList[2];
    
    // Cola (FIFO) para las puertas de embarque:
    //public final Queue<Avion> colaEsperaPuertasEmbarque = new LinkedList<>();
    
    // MECANISMOS PARA PROTEGER LAS ESTRUCTURAS DE DATOS:
    private final Lock lockViajerosBus = new ReentrantLock();
    private final Lock lockViajerosAvion = new ReentrantLock();
    private final Lock lockHangar = new ReentrantLock();
    private final Lock lockAreaEst = new ReentrantLock();
    private final Lock lockPuertasEmb = new ReentrantLock();
    private final Condition esperarPuertasEmb = lockPuertasEmb.newCondition();
    private final Semaphore semPuertasEmb = new Semaphore(6, true);
    private final Lock lockAreaRod = new ReentrantLock();
    private final Lock lockPistas = new ReentrantLock();
    private final Semaphore semPistas = new Semaphore(4, true);
    private final Condition esperarPistas = lockPistas.newCondition();
    private final Lock lockAerovias = new ReentrantLock();
    private final Lock lockTaller = new ReentrantLock();
    private final Semaphore semTaller = new Semaphore(20, true);
    private final Condition esperarTaller = lockTaller.newCondition();
    
    // Metodos para obtener información sobre el aeropuerto
    
    public Aeropuerto(String nombre){
        this.nombre = nombre;
    }
    
    public synchronized String getNombre() {
        return nombre;
    }

    public synchronized int getViajeros() {
        return viajeros;
    }
    
    public int contarAvionesHangar(){
        return hangar.size();
    }
    
    public int contarAvionesAreaEst(){
        return areaEstacionamiento.size();
    }
    
    // Metodos usados por los autobuses
    
    public void sumarViajerosBus(int pasajerosBus){ 
        try {
            lockViajerosBus.lock();
            viajeros += pasajerosBus;  
        }
        finally {
            lockViajerosBus.unlock();
        }
    }
    
    public void restarViajerosBus(int pasajerosBus){
        try {
            lockViajerosBus.lock();
            viajeros -= pasajerosBus;  
        }
        finally {
            lockViajerosBus.unlock();
        }
    }
    
    // Metodos usados por los aviones
    
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
    
    public void quitarAvionDeAreaEst(Avion avion, boolean despegando){
        // Si el avion va a despegar espera para puertas de embarque
        if (despegando) {
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
        // Si el avion acaba de aterrizar espera para el taller
        else {
            try {
                lockAreaEst.lock();
                while (this.esTallerCompleto()){
                    esperarTaller.await();
                }
                avion.getAeropuerto().areaEstacionamiento.remove(avion);
            } catch (InterruptedException e) {}
            finally {
                lockAreaEst.unlock();
            }
        }
    }
    
    public void insertarPuertasEmbarque(Avion avion, boolean embarcando) throws InterruptedException {
        semPuertasEmb.acquire();
        lockPuertasEmb.lock();
        try {
            for (int i = 0; i < 6; i++){
                // La puerta 1 solo vale para embarques y la 2 solo vale para desembarques:
                if (((i == 0) && (!embarcando)) || ((i == 1) && (embarcando))){
                    continue;                        
                }
                // Elige puerta
                if (puertasEmbarque[i] == null ) {
                    this.puertasEmbarque[i] = avion;
                    break;
                }
            }
        } finally { lockPuertasEmb.unlock(); }
    }
    
    public void sumarViajerosAvion(int pasajerosAvion){ 
        try {
            lockViajerosAvion.lock();
            viajeros += pasajerosAvion;  
        }
        finally {
            lockViajerosAvion.unlock();
        }
    }
    
    public void restarViajerosAvion(int pasajerosAvion){
        try {
            lockViajerosAvion.lock();
            viajeros -= pasajerosAvion;  
        }
        finally {
            lockViajerosAvion.unlock();
        }
    }
    
    public void quitarPuertasEmbarque(Avion avion) throws InterruptedException {
        lockPuertasEmb.lock();
        try {
            for (int i = 0; i < 6; i++){
                if (this.puertasEmbarque[i] == avion) {
                    this.puertasEmbarque[i] = null;
                    break;
                }
            }
            esperarPuertasEmb.signal();
        } finally { lockPuertasEmb.unlock(); }
        semPuertasEmb.release();
    }
    
    public void entrarAreaDeRodaje(Avion avion){
        try {
            lockAreaRod.lock();
            avion.getAeropuerto().areaRodaje.add(avion);
        } 
        finally {
            lockAreaRod.unlock();
        }  
    }
    
    public void salirAreaDeRodaje(Avion avion, boolean despegando){
        // Si el avion va a despegar espera para pistas
        if (despegando) {
            try {
                lockAreaRod.lock();
                while ((pistas[0] != null) && (pistas[1] != null) &&
                       (pistas[2] != null) && (pistas[3] != null) ){
                    esperarPistas.await();
                }
                avion.getAeropuerto().areaRodaje.remove(avion);
            } catch (InterruptedException e) {}
            finally {
                lockAreaRod.unlock();
            }
        }
        // Si el avion acaba de aterrizar espera para puertas de embarque
        else {
            try {
                lockAreaRod.lock();
                while ((puertasEmbarque[0] != null) && (puertasEmbarque[1] != null) &&
                       (puertasEmbarque[2] != null) && (puertasEmbarque[3] != null) &&
                       (puertasEmbarque[4] != null) && (puertasEmbarque[5] != null)){
                    esperarPuertasEmb.await();
                }
                avion.getAeropuerto().areaRodaje.remove(avion);
            } catch (InterruptedException e) {}
            finally {
                lockAreaRod.unlock();
            }
        }
    }
    
    public void entrarPista(Avion avion) throws InterruptedException {
        semPistas.acquire();
        lockPistas.lock();
        try {
            for (int i = 0; i < 4; i++){
                // Si la pista está desactivada se pasaría al siguiente
                // todavia no está es opcion implementada
                
                // Elige pista
                if (pistas[i] == null) {
                    this.pistas[i] = avion;
                    break;
                }
            }
        } finally { lockPistas.unlock(); }
    }
    
    public void salirPista(Avion avion) throws InterruptedException {
        lockPistas.lock();
        try {
            for (int i = 0; i < 4; i++){
                if (this.pistas[i] == avion) {
                    this.pistas[i] = null;
                    break;
                }
            }
            esperarPistas.signal();
        } finally { lockPistas.unlock(); }
        semPistas.release();
    }
    
    public void entrarAerovia(Avion avion, boolean despegando){
        if (despegando) {
            lockAerovias.lock();
            try {
                avion.getAeropuerto().aerovias[0].add(avion);
            } 
            finally {
                lockAerovias.unlock();
            }
        }
        else {
            lockAerovias.lock();
            try {
                avion.getAeropuerto().aerovias[1].add(avion);
            } 
            finally {
                lockAerovias.unlock();
            }
        }
    }
    
    public void salirAerovia(Avion avion){
        Avion avionBuscado = null;
        for(Avion a : aerovias[0]){
            if (a == avion){
                avionBuscado = a;
                aerovias[0].remove(a);
                break;
            }
        }
        if(avionBuscado != avion){
            for(Avion a : aerovias[1]){
                if (a == avion){
                    avionBuscado = a;
                    aerovias[1].remove(a);
                    break;
                }
            }
        }
    }
    
    public void entrarTaller(Avion avion) throws InterruptedException {
        semTaller.acquire();
        lockTaller.lock();
        try {
            for (int i = 0; i < 20; i++){
                // Elige puesto en el taller
                if (taller[i] == null) {
                    this.taller[i] = avion;
                    break;
                }
            }
        } finally { lockTaller.unlock(); }
    }
    
    public void salirTaller(Avion avion) throws InterruptedException {
        lockTaller.lock();
        try {
            for (int i = 0; i < 20; i++){
                if (this.taller[i] == avion) {
                    this.taller[i] = null;
                    break;
                }
            }
            esperarTaller.signal();
        } finally { lockTaller.unlock(); }
        semTaller.release();
    }
    
    // Para comporbar si el taller esta completo (el nombre es raro xd)
    public boolean esTallerCompleto(){ 
        boolean completo = true;
        
        for(Avion a : taller) {
            if (a == null){
                completo = false;
                break;
            }
        }
        
        return completo;
    }
    
}
