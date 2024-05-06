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
        
        // Instancia para revisar el control del programa (si se debe pausar):
        Paso paso = new Paso();
        
        // Interfaz gráfica:
        Interfaz ui = new Interfaz(paso);
        
        // Declarar los aeropuertos del sistema:
        Aeropuerto barajas = new Aeropuerto("Barajas", ui);
        Aeropuerto prat = new Aeropuerto("Prat", ui);
        
        // Crear los hilos de generación de aviones y buses, con ambos aeropuertos:
        HiloGenAviones hiloGenAviones = new HiloGenAviones(barajas, prat, paso);
        HiloGenBuses hiloGenBuses = new HiloGenBuses(barajas, prat, paso);
        
        // Iniciar los hilos generadores:        
        hiloGenAviones.start();
        hiloGenBuses.start();
        
        // Crear un registro RMI:
        Registry registro = LocateRegistry.createRegistry(1099);
        
        /* Vincular los objetos remotos a su respectivo nombre lógico
        en el registro RMI del servidor local:
        */
        Naming.rebind("//localhost/ObjetoPaso", paso);
        Naming.rebind("//localhost/ObjetoAeropuertoBarajas", barajas);
        Naming.rebind("//localhost/ObjetoAeropuertoPrat", prat);        
        
    }
} // Fin clase Servidor
