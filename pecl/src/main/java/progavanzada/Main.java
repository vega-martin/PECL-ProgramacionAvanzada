package progavanzada;

public class Main {

    public static void main(String[] args) {
        
        // Declarar los aeropuertos del sistema:
        Aeropuerto barajas = new Aeropuerto("Barajas");
        Aeropuerto prat = new Aeropuerto("Prat");
        
        // Crear e iniciar el hilo de generación de aviones con ambos aeropuertos
        HiloGenAviones hiloGenAviones = new HiloGenAviones(barajas, prat);
        hiloGenAviones.start();
        
    }
}
