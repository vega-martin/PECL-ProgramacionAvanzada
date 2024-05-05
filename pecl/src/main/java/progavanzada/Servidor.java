package progavanzada;

import UserInterface.Interfaz;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) throws RemoteException, AccessException, AlreadyBoundException, MalformedURLException {
        
        // Control
        Paso paso = new Paso();
        
        // Interfaz grafica
        Interfaz ui = new Interfaz(paso);
        
        // Declarar los aeropuertos del sistema:
        Aeropuerto barajas = new Aeropuerto("Barajas", ui);
        Aeropuerto prat = new Aeropuerto("Prat", ui);
        
        // Crear e iniciar el hilo de generación de aviones y buses con ambos aeropuertos
        HiloGenAviones hiloGenAviones = new HiloGenAviones(barajas, prat, paso);
        HiloGenBuses hiloGenBuses = new HiloGenBuses(barajas, prat, paso);
        
        
        
        hiloGenAviones.start();
        hiloGenBuses.start();
        
        Registry registro = LocateRegistry.createRegistry(1099);
        Naming.rebind("//localhost/ObjetoPaso", paso);
        Naming.rebind("//localhost/ObjetoAeropuertoBarajas", barajas);
        Naming.rebind("//localhost/ObjetoAeropuertoPrat", prat);
        
        
    }
} // Fin clase Servidor
