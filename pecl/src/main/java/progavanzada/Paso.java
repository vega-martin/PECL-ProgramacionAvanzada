package progavanzada;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Paso {
    
    private boolean cerrado = false;
    private Lock control = new ReentrantLock();
    private Condition parar = control.newCondition();
    
    Log log = new Log("evolucionAeropuerto.txt");
    
    public void mirar(){
        try {
            control.lock();
            while (cerrado) {
                parar.wait();
            }
        }
        catch (InterruptedException e) {}
        finally {
            control.unlock();
        }
    }
    
    public void abrir(){
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
    
    public void cerrar(){
        try {
            control.lock();
            cerrado = true;
            log.escribirEvento("*****  PARANDO EL SIMULADOR  *****");
        }
        finally {
            control.unlock();
        }
    }
}
