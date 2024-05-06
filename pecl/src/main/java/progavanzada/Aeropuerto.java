package progavanzada;

import userInterfaces.InterfazServidor;
import interfaces.IAeropuerto;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aeropuerto extends UnicastRemoteObject implements IAeropuerto {
    
    // Atributos identificadores:
    private String nombre;
    private int viajeros;
    
    // Instancia de la clase InterfazServidor:
    private InterfazServidor interfaz;
    
    /* Estructuras de datos para las zonas de cada avión
        - ArrayList --> capacidad ILIMITADA
        - Array (estático) --> capacidad LIMITADA
        - Queue --> estructura con FIFO
    */
    
    private final ArrayList<Avion> hangar = new ArrayList<>();
    private final Queue<Avion> areaEstacionamiento = new LinkedList<>();
    
    private Avion[] taller = new Avion[20];
    private Avion[] puertasEmbarque = new Avion[6];
    private Avion[] pistas = new Avion[4];
    
    // Estructura para la disponibilidad de las pistas:
    private boolean[] pistasDisponibles = new boolean[]{true, true, true, true};
    private boolean[] pistasHabilitadas = new boolean[]{true, true, true, true};
    
    private ArrayList<Avion> areaRodaje = new ArrayList<>();
    private ArrayList<Avion>[] aerovias = new ArrayList[2];
    
    
    // MECANISMOS PARA PROTEGER LAS ESTRUCTURAS DE DATOS:
    private final Lock lockViajerosBus = new ReentrantLock();
    private final Lock lockHangar = new ReentrantLock();
    private final Lock lockAreaEst = new ReentrantLock();
    private final Lock lockPuertasEmb = new ReentrantLock();
    private final Condition esperarPuertasEmb = lockPuertasEmb.newCondition();
    private final Semaphore semPuertasEmb = new Semaphore(6, true);
    private final Lock lockAreaRod = new ReentrantLock();
    private final Lock lockPistas = new ReentrantLock();
    private final Lock lockPistasDisponibles = new ReentrantLock();
    private final Lock lockPistasHabilitadas = new ReentrantLock();
    private final Semaphore semPistas = new Semaphore(4, true);
    private final Condition esperarPistas = lockPistas.newCondition();
    private final Lock lockAerovias = new ReentrantLock();
    private final Lock lockTaller = new ReentrantLock();
    private final Semaphore semTaller = new Semaphore(20, true);
    private final Condition esperarTaller = lockTaller.newCondition();
    
    // Constructor de la clase Aeropuerto:
    public Aeropuerto(String nombre, InterfazServidor ui) throws RemoteException {
        this.nombre = nombre;
        this.interfaz = ui;
        // Crear dos ArrayList adicionales para las aerovías:
        for (int i = 0; i < aerovias.length; i++) {
            aerovias[i] = new ArrayList<>();
        }
    }
    
    // Métodos para obtener información sobre el aeropuerto:
    @Override
    public String getNombre() throws RemoteException {
        return nombre;
    }
    
    @Override
    public int contarAvionesHangar() throws RemoteException {
        return hangar.size();
    }
    
    @Override
    public int contarAvionesTaller() throws RemoteException {
        int tamaño = 0;
        for(int i = 0; i < taller.length; i++){
            if(taller[i] != null){
                tamaño++;
            }
        }
        return tamaño;
    }
    
    @Override
    public int contarAvionesAreaEst() throws RemoteException {
        return areaEstacionamiento.size();
    }
    
    @Override
    public int contarAvionesAreaRod() throws RemoteException {
        return areaRodaje.size();
    }
    
    @Override
    public int contarAvionesPuertasEmb() throws RemoteException {
        int tamaño = 0;
        for(int i = 0; i < puertasEmbarque.length; i++){
            if(puertasEmbarque[i] != null){
                tamaño ++;
            }
        }
        return tamaño;
    }
    
    @Override
    public int contarAvionesPistas() throws RemoteException {
        int tamaño = 0;
        for(int i = 0; i < pistas.length; i++){
            if(pistas[i] != null){
                tamaño ++;
            }
        }
        return tamaño;
    }
    
    @Override
    public String getAvionesEnAerovia() throws RemoteException {
        String str = "";
        for (int i = 0; i < this.aerovias[0].size(); i++) {
            str += this.aerovias[0].get(i).getIdAvion() + ", ";
        }
        return str;
    }
    
    @Override
    public void setEstadoPista(int numPista, boolean estado) throws RemoteException {
        lockPistasHabilitadas.lock();
        try {
            pistasHabilitadas[numPista] = estado;
        } finally {
            lockPistasHabilitadas.unlock();
        }
    }
    
    @Override
    public int getViajeros() throws RemoteException {
        return viajeros;
    }
    
    public void sumarViajeros(int pasajerosBus){ 
        try {
            lockViajerosBus.lock();
            viajeros += pasajerosBus;
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_numPas_Bar().setText(Integer.toString(viajeros));
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_numPas_Prat().setText(Integer.toString(viajeros));
            }
        }
        finally {
            lockViajerosBus.unlock();
        }
    }
    
    public void restarViajeros(int pasajerosBus){
        try {
            lockViajerosBus.lock();
            viajeros -= pasajerosBus;
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_numPas_Bar().setText(Integer.toString(viajeros));
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_numPas_Prat().setText(Integer.toString(viajeros));
            }
        }
        finally {
            lockViajerosBus.unlock();
        }
    }
    
    public int getMaxViajeros(int maxPasajeros) {
        int pasajeros = 0;
        if(maxPasajeros <= this.viajeros){
            pasajeros = maxPasajeros;
            restarViajeros(pasajeros);
        }
        else {
            pasajeros = this.viajeros;
            restarViajeros(pasajeros);
        }
        return pasajeros; 
    }
    
    /*  Métodos usados para manejar las estructuras de datos de los aviones:
        introducir o eliminar los aviones de las diferentes zonas
    */
    
    public void incluirAvionEnHangar(Avion avion){
        try {
            lockHangar.lock();
            avion.getAeropuerto().hangar.add(avion);
            String str = "";
            for (int i = 0; i < hangar.size(); i++) {
                str += hangar.get(i).getIdAvion() + ", ";
            }
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_hangar_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_hangar_Prat().setText(str);
            }
        }
        finally {
            lockHangar.unlock();
        }  
    }
    
    public void quitarAvionDeHangar(Avion avion){
        try {
            lockHangar.lock();
            avion.getAeropuerto().hangar.remove(avion);
            String str = "";
            for (int i = 0; i < this.hangar.size(); i++) {
                str += this.hangar.get(i).getIdAvion() + ", ";
            }
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_hangar_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_hangar_Prat().setText(str);
            }
        }
        finally {
            lockHangar.unlock();
        }  
    }
    
    public void incluirAvionEnAreaEst(Avion avion){
        try {
            lockAreaEst.lock();
            avion.getAeropuerto().areaEstacionamiento.add(avion);
            String str = "";
            for (Avion a : areaEstacionamiento) {
                str += a.getIdAvion() + ", ";
            }
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_est_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_est_Prat().setText(str);
            }
        } 
        finally {
            lockAreaEst.unlock();
        }  
    }
    
    public void quitarAvionDeAreaEst(Avion avion, boolean despegando){
        lockAreaEst.lock();
        try {
            // Si el avión va a despegar, debe esperar a una puerta de embarque libre:
            if (despegando) {
                try {
                    lockPuertasEmb.lock();
                    while ((puertasEmbarque[0] != null) && (puertasEmbarque[1] != null) &&
                            (puertasEmbarque[2] != null) && (puertasEmbarque[3] != null) &&
                            (puertasEmbarque[4] != null) && (puertasEmbarque[5] != null)){
                        esperarPuertasEmb.await();
                    }
                    avion.getAeropuerto().areaEstacionamiento.remove(avion);
                } catch (InterruptedException e) {}
                finally {
                    lockPuertasEmb.unlock();
                }
            }
            // Si el avión acaba de aterrizar, debe esperar para ir al taller:
            else {
                try {
                    lockTaller.lock();
                    while (this.tallerCompleto()){
                        esperarTaller.await();
                    }
                    avion.getAeropuerto().areaEstacionamiento.remove(avion);
                } catch (InterruptedException e) {}
                finally {
                    lockTaller.unlock();
                }
            }
            String str = "";
            for (Avion a : areaEstacionamiento) {
                str += a.getIdAvion() + ", ";
            }
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_est_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_est_Prat().setText(str);
            }
        } finally {
            lockAreaEst.unlock();
        }
    }
    
    public void insertarPuertasEmbarque(Avion avion, boolean embarcando) throws InterruptedException {
        semPuertasEmb.acquire();
        lockPuertasEmb.lock();
        try {
            int puertaDisponible = -1; // Inicialmente no hay ninguna puerta disponible
            for (int i = 0; i < 6; i++){
                // La puerta 1 solo vale para embarques, y la 2 solo vale para desembarques:
                if (((i == 0) && (!embarcando)) || ((i == 1) && (embarcando))){
                    continue;                        
                }
                // Si la puerta está disponible, la marcamos como elegida y salimos:
                if (puertasEmbarque[i] == null) {
                    puertaDisponible = i;
                    break;
                }
            }

            // Si hay una puerta disponible, se asigna el avión a esa puerta:
            if (puertaDisponible != -1) {
                this.puertasEmbarque[puertaDisponible] = avion;
                String str = embarcando ? "Embarcando:" : "Desembarcando:";
                
                // Actualizar el JTextField:
                if("Barajas".equals(this.nombre)) {
                    switch (puertaDisponible) {
                        case 0 -> this.interfaz.getInfo_pEmb1_Bar().setText(str + avion.getIdAvion());
                        case 1 -> this.interfaz.getInfo_pEmb2_Bar().setText(str + avion.getIdAvion());
                        case 2 -> this.interfaz.getInfo_pEmb3_Bar().setText(str + avion.getIdAvion());
                        case 3 -> this.interfaz.getInfo_pEmb4_Bar().setText(str + avion.getIdAvion());
                        case 4 -> this.interfaz.getInfo_pEmb5_Bar().setText(str + avion.getIdAvion());
                        case 5 -> this.interfaz.getInfo_pEmb6_Bar().setText(str + avion.getIdAvion());
                    }
                }
                else if("Prat".equals(this.nombre)) {
                    switch (puertaDisponible) {
                        case 0 -> this.interfaz.getInfo_pEmb1_Prat().setText(str + avion.getIdAvion());
                        case 1 -> this.interfaz.getInfo_pEmb2_Prat().setText(str + avion.getIdAvion());
                        case 2 -> this.interfaz.getInfo_pEmb3_Prat().setText(str + avion.getIdAvion());
                        case 3 -> this.interfaz.getInfo_pEmb4_Prat().setText(str + avion.getIdAvion());
                        case 4 -> this.interfaz.getInfo_pEmb5_Prat().setText(str + avion.getIdAvion());
                        case 5 -> this.interfaz.getInfo_pEmb6_Prat().setText(str + avion.getIdAvion());
                    }
                }
            }  
        } 
        finally {
            lockPuertasEmb.unlock();
        }
    }
    
    public void quitarPuertasEmbarque(Avion avion) throws InterruptedException {
        lockPuertasEmb.lock();
        try {
            for (int i = 0; i < 6; i++){
                if (this.puertasEmbarque[i] == avion) {
                    this.puertasEmbarque[i] = null;
                    if("Barajas".equals(this.nombre)) {
                        switch (i) {
                            case 0 -> this.interfaz.getInfo_pEmb1_Bar().setText("");
                            case 1 -> this.interfaz.getInfo_pEmb2_Bar().setText("");
                            case 2 -> this.interfaz.getInfo_pEmb3_Bar().setText("");
                            case 3 -> this.interfaz.getInfo_pEmb4_Bar().setText("");
                            case 4 -> this.interfaz.getInfo_pEmb5_Bar().setText("");
                            case 5 -> this.interfaz.getInfo_pEmb6_Bar().setText("");
                        }
                    }
                    else if("Prat".equals(this.nombre)) {
                        switch (i) {
                            case 0 -> this.interfaz.getInfo_pEmb1_Prat().setText("");
                            case 1 -> this.interfaz.getInfo_pEmb2_Prat().setText("");
                            case 2 -> this.interfaz.getInfo_pEmb3_Prat().setText("");
                            case 3 -> this.interfaz.getInfo_pEmb4_Prat().setText("");
                            case 4 -> this.interfaz.getInfo_pEmb5_Prat().setText("");
                            case 5 -> this.interfaz.getInfo_pEmb6_Prat().setText("");
                        }
                    }
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
            String str = "";
            for (int i = 0; i < this.areaRodaje.size(); i++) {
                str += this.areaRodaje.get(i).getIdAvion() + ", ";
            }
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_rodaje_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_rodaje_Prat().setText(str);
            }
        } 
        finally {
            lockAreaRod.unlock();
        }  
    }
    
    public void salirAreaDeRodaje(Avion avion, boolean despegando){
        lockAreaRod.lock();
        try {
            // Si el avión va a despegar, debe esperar a una pista libre:
            if (despegando) {
                try {
                    lockPistas.lock();
                    while ((pistas[0] != null) && (pistas[1] != null) &&
                            (pistas[2] != null) && (pistas[3] != null) ){
                        esperarPistas.await();
                    }
                    avion.getAeropuerto().areaRodaje.remove(avion);
                } catch (InterruptedException e) {}
                finally {
                    lockPistas.unlock();
                }
            }
            // Si el avión acaba de aterrizar, debe esperar a una puerta de embarque libre:
            else {
                try {
                    lockPuertasEmb.lock();
                    while ((puertasEmbarque[0] != null) && (puertasEmbarque[1] != null) &&
                            (puertasEmbarque[2] != null) && (puertasEmbarque[3] != null) &&
                            (puertasEmbarque[4] != null) && (puertasEmbarque[5] != null)){
                        esperarPuertasEmb.await();
                    }
                    avion.getAeropuerto().areaRodaje.remove(avion);
                } catch (InterruptedException e) {}
                finally {
                    lockPuertasEmb.unlock();
                }
            }
            String str = "";
            for (int i = 0; i < this.areaRodaje.size(); i++) {
                str += this.areaRodaje.get(i).getIdAvion() + ", ";
            }
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_rodaje_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_rodaje_Prat().setText(str);
            }
        } finally {
            lockAreaRod.unlock();
        }
    }
    
    public int buscarPista() {
        try {
            lockPistasDisponibles.lock();
            int pista = -1;
            for (int i = 0; i < 4; i++){
                if (pistasDisponibles[i]) {
                    pistasDisponibles[i] = false;
                    pista = i;
                    break;
                }
            }
            return pista;
        }
        finally {
            lockPistasDisponibles.unlock();
        }
    }
    
    public void liberarPista(int pista) {
        try {
            lockPistasDisponibles.lock();
            pistasDisponibles[pista] = true;
        }
        finally {
            lockPistasDisponibles.unlock();
        }
    }
    public void entrarPista(Avion avion) throws InterruptedException {
        semPistas.acquire();
        lockPistas.lock();
        try {
            for (int i = 0; i < 4; i++){
                
                // Si la pista está cerrada (parte de programación distribuida) pasa a la siguiente:
                if (pistasHabilitadas[i] == false) {
                    continue;
                }
                
                // Si encuentra una pista libre, la elige, actualiza su JTextField y salimosd de la búsqueda:
                if (pistas[i] == null) {
                    this.pistas[i] = avion;
                    if("Barajas".equals(this.nombre)) {
                        switch (i) {
                            case 0 -> this.interfaz.getInfo_pista1_Bar().setText(avion.getIdAvion());
                            case 1 -> this.interfaz.getInfo_pista2_Bar().setText(avion.getIdAvion());
                            case 2 -> this.interfaz.getInfo_pista3_Bar().setText(avion.getIdAvion());
                            case 3 -> this.interfaz.getInfo_pista4_Bar().setText(avion.getIdAvion());
                        }
                    }
                    else if("Prat".equals(this.nombre)) {
                        switch (i) {
                            case 0 -> this.interfaz.getInfo_pista1_Prat().setText(avion.getIdAvion());
                            case 1 -> this.interfaz.getInfo_pista2_Prat().setText(avion.getIdAvion());
                            case 2 -> this.interfaz.getInfo_pista3_Prat().setText(avion.getIdAvion());
                            case 3 -> this.interfaz.getInfo_pista4_Prat().setText(avion.getIdAvion());
                        }
                    }
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
                    if("Barajas".equals(this.nombre)) {
                        switch (i) {
                            case 0 -> this.interfaz.getInfo_pista1_Bar().setText("");
                            case 1 -> this.interfaz.getInfo_pista2_Bar().setText("");
                            case 2 -> this.interfaz.getInfo_pista3_Bar().setText("");
                            case 3 -> this.interfaz.getInfo_pista4_Bar().setText("");
                        }
                    }
                    else if("Prat".equals(this.nombre)) {
                        switch (i) {
                            case 0 -> this.interfaz.getInfo_pista1_Prat().setText("");
                            case 1 -> this.interfaz.getInfo_pista2_Prat().setText("");
                            case 2 -> this.interfaz.getInfo_pista3_Prat().setText("");
                            case 3 -> this.interfaz.getInfo_pista4_Prat().setText("");
                        }
                    }
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
                
                String str = "";
                for (int i = 0; i < this.aerovias[0].size(); i++) {
                    str += this.aerovias[0].get(i).getIdAvion() + ", ";
                }
                if("Barajas".equals(this.nombre)) {
                    this.interfaz.getInfo_ae_Bar_Prat().setText(str);
                }
                else if("Prat".equals(this.nombre)) {
                    this.interfaz.getInfo_ae_Prat_Bar().setText(str);
                }
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
                String str = "";
                for (int i = 0; i < this.aerovias[0].size(); i++) {
                    str += this.aerovias[0].get(i).getIdAvion() + ", ";
                }
                if("Barajas".equals(this.nombre)) {
                    this.interfaz.getInfo_ae_Bar_Prat().setText(str);
                }
                else if("Prat".equals(this.nombre)) {
                    this.interfaz.getInfo_ae_Prat_Bar().setText(str);
                }
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
            String str = "";
            for (int i = 0; i < taller.length; i++){
                // Elige puesto en el taller
                if (taller[i] == null) {
                    this.taller[i] = avion;
                    break;
                }
            }
            // Rellena el texto de información
            for (int j = 0; j < taller.length; j++){
                if(taller[j] != null) {
                    str += taller[j].getIdAvion() + ", ";
                }
            }
            // Imprime la información
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_taller_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_taller_Prat().setText(str);
            }
        } finally { lockTaller.unlock(); }
    }
    
    public void salirTaller(Avion avion) throws InterruptedException {
        lockTaller.lock();
        try {
            String str = "";
            for (int i = 0; i < taller.length; i++){
                if (this.taller[i] == avion) {
                    this.taller[i] = null;
                    break;
                }
            }
            // Rellena el texto de información
            for (int j = 0; j < taller.length; j++){
                if(taller[j] != null) {
                    str += taller[j].getIdAvion() + ", ";
                }
            }
            // Imprime la información
            if("Barajas".equals(this.nombre)) {
                this.interfaz.getInfo_taller_Bar().setText(str);
            }
            else if("Prat".equals(this.nombre)) {
                this.interfaz.getInfo_taller_Prat().setText(str);
            }
        } finally { lockTaller.unlock(); }
        semTaller.release();
    }
    
    // Para comprobar si el taller está completo:   
    public boolean tallerCompleto(){ 
        boolean completo = true;
        
        for(Avion a : taller) {
            if (a == null){
                completo = false;
                break;
            }
        }
        
        return completo;
    }
    
} // Fin clase Aeropuerto
