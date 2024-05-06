package progavanzada;

import interfaces.IPaso;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Paso extends UnicastRemoteObject implements IPaso {
    
    private boolean cerrado = false;
    
    // Mecanismo para la exclusión mutua:
    private Lock control = new ReentrantLock();
    private Condition parar = control.newCondition();
    
    // Instancia para registrar eventos:
    Log log = new Log("evolucionAeropuerto.txt");
    
    // Constructor de la clase, para habilitar su instanciación:
    public Paso() throws RemoteException {}
    
    /*  Comprobar si se debe pausar el programa 
        --> revisar la variable booleana compartida:
    */
    public void mirar() throws RemoteException {
        try {
            control.lock();
            while (cerrado) {
                parar.await();
            }
        }
        catch (InterruptedException e) {}
        finally {
            control.unlock();
        }
    }
    
    // Pausar el programa:
    public void cerrar() {
        try {
            control.lock();
            cerrado = true;
            log.escribirEvento("*****  PARANDO EL SIMULADOR  *****");
        }
        finally {
            control.unlock();
        }
    }
    
    // Reanudar la ejecución del programa:
    public void abrir() {
        try {
            control.lock();
            cerrado = false;
            log.escribirEvento("*****  REANUDANDO EL SIMULADOR  *****");
            parar.signalAll();
        }
        finally {
            control.unlock();
        }
    }
    
} // Fin clase Paso
