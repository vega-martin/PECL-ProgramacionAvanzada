package progavanzada;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HiloGenAviones extends Thread {
    
    // Recursos compartidos
    private static final AtomicInteger contadorAviones = new AtomicInteger(0);
    
    private final Aeropuerto barajas;
    private final Aeropuerto prat;
    
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
            
            // TEST:
            System.out.println("Avion " + i + " creado, va a " + avion.getAeropuerto().getNombre()
                    + ". Su id es " + avion.getIdAvion());
            
            // Iniciar el hilo:
            avion.start();  
            
        }
    } 
}
