package progavanzada;

import interfaz.Interfaz;
import interfaz.InterfazCliente;

public class Main {

    public static void main(String[] args) {
        
        // Control
        Paso paso = new Paso();
        
        // Interfaz grafica
        
        Interfaz ui = new Interfaz(paso);
        
        
        // Declarar los aeropuertos del sistema:
        Aeropuerto barajas = new Aeropuerto("Barajas", ui);
        Aeropuerto prat = new Aeropuerto("Prat", ui);
        InterfazCliente ic = new InterfazCliente(paso, barajas, prat);
        // Crear e iniciar el hilo de generación de aviones y buses con ambos aeropuertos
        HiloGenAviones hiloGenAviones = new HiloGenAviones(barajas, prat, paso);
        HiloGenBuses hiloGenBuses = new HiloGenBuses(barajas, prat, paso);
        
        hiloGenAviones.start();
        hiloGenBuses.start();
        
        
    }
} // Fin clase Main
