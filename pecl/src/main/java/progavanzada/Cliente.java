package progavanzada;

import UserInterface.InterfazCliente;
import interfaces.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        
        //Registry registro = LocateRegistry.getRegistry("localhost", 1099);
        IPaso paso = (IPaso) Naming.lookup("//127.0.0.1/ObjetoPaso");
        IAeropuerto barajas = (IAeropuerto) Naming.lookup("//127.0.0.1/ObjetoAeropuertoBarajas");
        IAeropuerto prat = (IAeropuerto) Naming.lookup("//127.0.0.1/ObjetoAeropuertoPrat");
        // Interfaz grafica
        InterfazCliente ic = new InterfazCliente(paso, barajas, prat);
        
        while(true) {
            // Actualizar la informacion de los Aeropuertos "a la vez"
            
            // Numero de viajeros Aeropuertos
            ic.getInfo_numPas_Bar().setText(Integer.toString(barajas.getViajeros()));
            ic.getInfo_numPas_Prat().setText(Integer.toString(prat.getViajeros()));
            
            // Numero de aviones en los Hangares
            ic.getInfo_numHangar_Bar().setText(Integer.toString(barajas.contarAvionesHangar()));
            ic.getInfo_numHangar_Prat().setText(Integer.toString(prat.contarAvionesHangar()));
            
            // Numero de aviones en los Talleres
            ic.getInfo_numTaller_Bar().setText(Integer.toString(barajas.contarAvionesTaller()));
            ic.getInfo_numTaller_Prat().setText(Integer.toString(prat.contarAvionesTaller()));
            
            // Numero de aviones en las Areas de Estacionamiento
            ic.getInfo_numArEst_Bar().setText(Integer.toString(barajas.contarAvionesAreaEst()));
            ic.getInfo_numArEst_Prat().setText(Integer.toString(prat.contarAvionesAreaEst()));
            
            // Numero de aviones en la Areas de Rodaje
            ic.getInfo_numArRod_Bar().setText(Integer.toString(barajas.contarAvionesAreaRod()));
            ic.getInfo_numArRod_Prat().setText(Integer.toString(prat.contarAvionesAreaRod()));
            
            // Numero de Puertas de Embarque ocupadas
            ic.getInfo_numPuerEmb_Bar().setText(Integer.toString(barajas.contarAvionesPuertasEmb()));
            ic.getInfo_numPuerEmb_Prat().setText(Integer.toString(prat.contarAvionesPuertasEmb()));
            
            // Numero de Pistas ocupadas
            ic.getInfo_numPistas_Bar().setText(Integer.toString(barajas.contarAvionesPistas()));
            ic.getInfo_numPistas_Prat().setText(Integer.toString(prat.contarAvionesPistas()));
            
            // Aerovias
            ic.getInfo_ae_Bar_Prat().setText(barajas.getAvionesEnAerovia());
            ic.getInfo_ae_Prat_Bar().setText(prat.getAvionesEnAerovia());
            
            
        }
        
    }
    
}
