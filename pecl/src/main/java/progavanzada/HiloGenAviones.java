package progavanzada;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloGenAviones extends Thread {
    
    // Recursos compartidos:
    private static final AtomicInteger contadorAviones = new AtomicInteger(0); // Variable atómica  
    private final Aeropuerto barajas;
    private final Aeropuerto prat;
    
    // Instancia para registrar eventos:
    Log log = new Log("evolucionAeropuerto.txt");
    
    // Instancia para pausar el programa:
    private final Paso paso;
    
    // Constructor que recibe Barajas y Prat:
    public HiloGenAviones(Aeropuerto barajas, Aeropuerto prat, Paso p) {
        this.barajas = barajas;
        this.prat = prat;
        this.paso = p;
    }
    
    public void run(){
        
        Random r = new Random();
        
        for (int i = 0; i < 8000; i++){
            
            try {

                // Esperamos de 1 a 3 segundos:
                try {
                    Thread.sleep(r.nextInt(2000) + 1000);
                }
                catch (InterruptedException ie) {}
                
                // Se genera el avión:                
                Avion avion;
                
                // Decidir a qué aeropuerto va:
                if (i % 2 == 0) {
                    avion = new Avion(barajas, contadorAviones.getAndIncrement(), prat, paso); // Barajas
                }
                
                else {
                    avion = new Avion(prat, contadorAviones.getAndIncrement(), barajas, paso); // El Prat
                }
                
                // Generar el evento a registrar:               
                String str = ("AVION " + i + " creado, va a " + avion.getAeropuerto().getNombre()
                        + ". Su id es " + avion.getIdAvion());
                
                // Registrar en el log:
                log.escribirEvento(str);
                
                // Iniciar el hilo:
                avion.start();
                
                // Comprobar si se debe pausar el programa:
                paso.mirar();
                
            }
            catch (RemoteException ex) {
                Logger.getLogger(HiloGenAviones.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    } 
} // Fin clase HiloGenAviones
