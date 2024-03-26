package progavanzada;

import java.util.ArrayList;

public class Aeropuerto {
    
    private String nombre;

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
    
    public Aeropuerto(String nombre){
        this.nombre = nombre;
    }
    
}
