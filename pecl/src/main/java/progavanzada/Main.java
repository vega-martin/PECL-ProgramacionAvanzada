package progavanzada;

public class Main {

    public static void main(String[] args) {
        
        // Declarar los aeropuertos del sistema:
        Aeropuerto barajas = new Aeropuerto("Barajas");
        Aeropuerto prat = new Aeropuerto("Prat");
        
        // Crear e iniciar el hilo de generación de aviones y buses con ambos aeropuertos
        //HiloGenAviones hiloGenAviones = new HiloGenAviones(barajas, prat);
        HiloGenBuses hiloGenBuses = new HiloGenBuses(barajas, prat);
        
        //hiloGenAviones.start();
        hiloGenBuses.start();
        
        
        
    }
}
