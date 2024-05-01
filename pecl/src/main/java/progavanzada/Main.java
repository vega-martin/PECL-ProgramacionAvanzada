package progavanzada;

import interfaz.Interfaz;

public class Main {

    public static void main(String[] args) {
        
        // Interfaz grafica
        Interfaz ui = new Interfaz();
        
        
        // Declarar los aeropuertos del sistema:
        Aeropuerto barajas = new Aeropuerto("Barajas", ui);
        Aeropuerto prat = new Aeropuerto("Prat", ui);
        
        // Crear e iniciar el hilo de generación de aviones y buses con ambos aeropuertos
        HiloGenAviones hiloGenAviones = new HiloGenAviones(barajas, prat);
        HiloGenBuses hiloGenBuses = new HiloGenBuses(barajas, prat);
        
        hiloGenAviones.start();
        hiloGenBuses.start();
        
        
    }
}
