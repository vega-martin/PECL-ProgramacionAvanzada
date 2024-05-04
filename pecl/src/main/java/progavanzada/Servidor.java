package progavanzada;

import UserInterface.Interfaz;
import UserInterface.InterfazCliente;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) throws RemoteException, AccessException, AlreadyBoundException {
        
        // Control
        Paso paso = new Paso();
        
        // Interfaz grafica
        InterfazCliente ic = new InterfazCliente(paso);
        Interfaz ui = new Interfaz(paso);
        
        // Declarar los aeropuertos del sistema:
        Aeropuerto barajas = new Aeropuerto("Barajas", ui);
        Aeropuerto prat = new Aeropuerto("Prat", ui);
        
        // Crear e iniciar el hilo de generación de aviones y buses con ambos aeropuertos
        HiloGenAviones hiloGenAviones = new HiloGenAviones(barajas, prat, paso);
        HiloGenBuses hiloGenBuses = new HiloGenBuses(barajas, prat, paso);
        
        
        
        hiloGenAviones.start();
        hiloGenBuses.start();
        
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("Paso", paso);
        registry.bind("AeropuertoBarajas", barajas);
        registry.bind("AeropuertoPrat", prat);
        
        
    }
} // Fin clase Servidor
