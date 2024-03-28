package progavanzada;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Aeropuerto {
    
    private String nombre;
    private int viajeros;

    /* Estructuras de datos para las zonas de cada avión
        - ARRAYLIST --> CAPACIDAD ILIMITADA
        - ARRAY ESTATICO --> CAPACIDAD LIMITADA
    */
    
    private ArrayList<Avion> hangar = new ArrayList<>();
    private Avion[] taller = new Avion[20];
    private Avion[] puertasEmbarque = new Avion[6];
    private Avion[] pistas = new Avion[4];
    private ArrayList<Avion> areaEstacionamiento = new ArrayList<>();
    private ArrayList<Avion> areaRodaje = new ArrayList<>();
    private Avion[] aerovias = new Avion[2];
    
    // Cola (FIFO) para las puertas de embarque:
    Queue<Avion> colaPuertasEmbarque = new LinkedList<>();

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

    public synchronized ArrayList<Avion> getHangar() {
        return hangar;
    }

    public synchronized void setHangar(ArrayList<Avion> hangar) {
        this.hangar = hangar;
    }

    public synchronized Avion[] getTaller() {
        return taller;
    }

    public synchronized void setTaller(Avion[] taller) {
        this.taller = taller;
    }

    public synchronized Avion[] getPuertasEmbarque() {
        return puertasEmbarque;
    }

    public synchronized void setPuertasEmbarque(Avion[] puertasEmbarque) {
        this.puertasEmbarque = puertasEmbarque;
    }

    public synchronized Avion[] getPistas() {
        return pistas;
    }

    public synchronized void setPistas(Avion[] pistas) {
        this.pistas = pistas;
    }

    public synchronized ArrayList<Avion> getAreaEstacionamiento() {
        return areaEstacionamiento;
    }

    public synchronized void setAreaEstacionamiento(ArrayList<Avion> areaEstacionamiento) {
        this.areaEstacionamiento = areaEstacionamiento;
    }

    public synchronized ArrayList<Avion> getAreaRodaje() {
        return areaRodaje;
    }

    public synchronized void setAreaRodaje(ArrayList<Avion> areaRodaje) {
        this.areaRodaje = areaRodaje;
    }

    public synchronized Avion[] getAerovias() {
        return aerovias;
    }

    public synchronized void setAerovias(Avion[] aerovias) {
        this.aerovias = aerovias;
    } 

    public synchronized Queue<Avion> getColaPuertasEmbarque() {
        return colaPuertasEmbarque;
    }

    public synchronized void setColaPuertasEmbarque(Queue<Avion> colaPuertasEmbarque) {
        this.colaPuertasEmbarque = colaPuertasEmbarque;
    }
    
   
    
    public Aeropuerto(String nombre){
        this.nombre = nombre;
    }
    
}
