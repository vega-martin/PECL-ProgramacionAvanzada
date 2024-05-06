package progavanzada;

import userInterfaces.InterfazCliente;
import interfaces.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Cliente {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        
        // Buscar un objeto en el registro RMI utilizando la IP y nombre lógico correspondientes:
        IPaso paso = (IPaso) Naming.lookup("//127.0.0.1/ObjetoPaso");
        IAeropuerto barajas = (IAeropuerto) Naming.lookup("//127.0.0.1/ObjetoAeropuertoBarajas");
        IAeropuerto prat = (IAeropuerto) Naming.lookup("//127.0.0.1/ObjetoAeropuertoPrat");
        
        // Interfaz gráfica:
        InterfazCliente ic = new InterfazCliente(paso, barajas, prat);
        
        while(true) {
            // Actualizar la información de los aeropuertos "a la vez":
            
            // Número de viajeros en los aeropuertos:
            ic.getInfo_numPas_Bar().setText(Integer.toString(barajas.getViajeros()));
            ic.getInfo_numPas_Prat().setText(Integer.toString(prat.getViajeros()));
            
            // Número de aviones en los hangares:
            ic.getInfo_numHangar_Bar().setText(Integer.toString(barajas.contarAvionesHangar()));
            ic.getInfo_numHangar_Prat().setText(Integer.toString(prat.contarAvionesHangar()));
            
            // Número de aviones en los talleres:
            ic.getInfo_numTaller_Bar().setText(Integer.toString(barajas.contarAvionesTaller()));
            ic.getInfo_numTaller_Prat().setText(Integer.toString(prat.contarAvionesTaller()));
            
            // Número de aviones en las áreas de estacionamiento:
            ic.getInfo_numArEst_Bar().setText(Integer.toString(barajas.contarAvionesAreaEst()));
            ic.getInfo_numArEst_Prat().setText(Integer.toString(prat.contarAvionesAreaEst()));
            
            // Número de aviones en las áreas de rodaje:
            ic.getInfo_numArRod_Bar().setText(Integer.toString(barajas.contarAvionesAreaRod()));
            ic.getInfo_numArRod_Prat().setText(Integer.toString(prat.contarAvionesAreaRod()));
            
            // Número de puertas de embarque ocupadas:
            ic.getInfo_numPuerEmb_Bar().setText(Integer.toString(barajas.contarAvionesPuertasEmb()));
            ic.getInfo_numPuerEmb_Prat().setText(Integer.toString(prat.contarAvionesPuertasEmb()));
            
            // Número de pistas ocupadas:
            ic.getInfo_numPistas_Bar().setText(Integer.toString(barajas.contarAvionesPistas()));
            ic.getInfo_numPistas_Prat().setText(Integer.toString(prat.contarAvionesPistas()));
            
            // Aviones en las aerovías:
            ic.getInfo_ae_Bar_Prat().setText(barajas.getAvionesEnAerovia());
            ic.getInfo_ae_Prat_Bar().setText(prat.getAvionesEnAerovia());
            
            
        }
        
    }
    
}
