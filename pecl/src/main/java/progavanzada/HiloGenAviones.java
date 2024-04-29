package progavanzada;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HiloGenAviones extends Thread {
    
    // Recursos compartidos
    private static final AtomicInteger contadorAviones = new AtomicInteger(0);
    
    private final Aeropuerto barajas;
    private final Aeropuerto prat;
    
    Log log = new Log("evolucionAeropuerto.txt");
    
    private Paso paso;
    
    // Constructor que recibe Barajas y Prat:
    public HiloGenAviones(Aeropuerto barajas, Aeropuerto prat) {
        this.barajas = barajas;
        this.prat = prat;
    }
    
    public void run(){
        
        Random r = new Random();
        
        for (int i = 0; i < 8000; i++){
            
            // Esperamos de 1 a 3 segundos:
            try {
                Thread.sleep(r.nextInt(2000) + 1000);
            }
            catch (InterruptedException ie) {}
            
            // Se genera el avión...
            
            Avion avion;
            
            // Decidir a qué aeropuerto va:
            if (i % 2 == 0) {
                avion = new Avion(barajas, contadorAviones.getAndIncrement(), prat); // Barajas
            }
            
            else {
                avion = new Avion(prat, contadorAviones.getAndIncrement(), barajas); // El Prat
            }
            
            // Registrar en el log
            
            String str = ("AVION " + i + " creado, va a " + avion.getAeropuerto().getNombre()
                    + ". Su id es " + avion.getIdAvion());
            
            log.escribirEvento(str);
            
            // Iniciar el hilo:
            avion.start(); 
            
            // Cada vez que termina de generar un avion mira si se tiene que parar
            paso.mirar();
            
        }
    } 
}
