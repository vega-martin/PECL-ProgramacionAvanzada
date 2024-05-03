package progavanzada;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HiloGenBuses extends Thread {
    
    // Recursos compartidos
    private static final AtomicInteger contadorBus = new AtomicInteger(0);
    
    private final Aeropuerto barajas;
    private final Aeropuerto prat;
    
    Log log = new Log("evolucionAeropuerto.txt");
    
    private final Paso paso;
    
    // Constructor que recibe Barajas y Prat:
    public HiloGenBuses(Aeropuerto barajas, Aeropuerto prat, Paso p) {
        this.barajas = barajas;
        this.prat = prat;
        this.paso = p;
    }
    
    public void run(){
        
        Random r = new Random();
        
        for (int i = 0; i < 4000; i++){
            
            // Esperar de 0.5 a 1 segundos:
            try {
                Thread.sleep(r.nextInt(500) + 500);
            }
            catch (InterruptedException ie) {}
            
            // Generar el bus
            Bus bus;
            
            // Decidir a qué aeropuerto va:
            if (i % 2 == 0) {
                bus = new Bus(barajas, contadorBus.getAndIncrement(), paso); // Barajas
            }
            
            else {
                bus = new Bus(prat, contadorBus.getAndIncrement(), paso); // El Prat
            }
            
            // Registrar en el log
            
            String str = ("BUS " + i + " creado, va a " + bus.getAeropuerto().getNombre()
                    + ". Su id es " + bus.getIdBus());
            
            log.escribirEvento(str);
            
            // Iniciar el hilo:
            bus.start();  
            
            // Cada vez que termina de generar un bus mira si se tiene que parar
            paso.mirar();
            
        }
    }
} // Fin clase HiloGenBuses
