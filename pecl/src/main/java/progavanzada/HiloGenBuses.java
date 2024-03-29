package progavanzada;

import java.util.Random;

public class HiloGenBuses extends Thread {
    
    private final Aeropuerto barajas;
    private final Aeropuerto prat;
    
    // Constructor que recibe Barajas y Prat:
    public HiloGenBuses(Aeropuerto barajas, Aeropuerto prat) {
        this.barajas = barajas;
        this.prat = prat;
    }
    
    public void run(){
        
        Random r = new Random();
        
        for (int i = 0; i < 1; i++){
            
            // Esperamos de 0.5 a 1 segundos:
            try {
                Thread.sleep(r.nextInt(500) + 500);
            }
            catch (InterruptedException ie) {}
            
            // Se genera el bus...
            
            Bus bus;
            
            // Decidir a qué aeropuerto va:
            if (i % 2 == 0) {
                bus = new Bus(barajas); // Barajas
            }
            
            else {
                bus = new Bus(prat); // El Prat
            }
            
            // TEST:
            System.out.println("Bus " + i + " creado, va a " + bus.getAeropuerto().getNombre()
                    + " Su id es " + bus.getIdBus());
            
            // Iniciar el hilo:
            bus.start();  
            
        }
    }
}
