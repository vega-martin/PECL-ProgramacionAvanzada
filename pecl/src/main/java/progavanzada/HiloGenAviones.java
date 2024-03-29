package progavanzada;

public class HiloGenAviones extends Thread {
    
    private final Aeropuerto barajas;
    private final Aeropuerto prat;
    
    // Constructor que recibe Barajas y Prat:
    public HiloGenAviones(Aeropuerto barajas, Aeropuerto prat) {
        this.barajas = barajas;
        this.prat = prat;
    }
    
    public void run(){
        
        for (int i = 0; i < 8000; i++){
            
            Avion avion;
            
            // Decidir a qué aeropuerto va:
            if (i % 2 == 0) {
                avion = new Avion(barajas); // Barajas
            }
            
            else {
                avion = new Avion(prat); // El Prat
            }
            
            // TEST:
            System.out.print("Avion " + i + " creado, va a " + avion.getAeropuerto().getNombre());
            System.out.println(" Su id es " + avion.getIdAvion());
            
            // Iniciar el hilo:
            avion.start();  
            
        }
    } 
}
