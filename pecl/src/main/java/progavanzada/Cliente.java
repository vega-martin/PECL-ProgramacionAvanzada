/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progavanzada;

import UserInterface.InterfazCliente;
import interfaces.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Vega
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        IPaso paso = (IPaso) registry.lookup("Paso");
        IAeropuerto barajas = (IAeropuerto) registry.lookup("AeropuertoBarajas");
        IAeropuerto prat = (IAeropuerto) registry.lookup("AeropuertoPrat");
        // Interfaz grafica
        InterfazCliente ic = new InterfazCliente((Paso) paso);
    }
    
}
