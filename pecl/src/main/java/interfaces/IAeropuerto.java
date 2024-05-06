package interfaces;

import userInterfaces.InterfazServidor;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAeropuerto extends Remote {
    
    // Métodos que se van a usar en el CLIENTE:
    public String getNombre() throws RemoteException;
    public int contarAvionesHangar() throws RemoteException;
    public int contarAvionesTaller() throws RemoteException;
    public int contarAvionesAreaEst() throws RemoteException;
    public int contarAvionesAreaRod() throws RemoteException;
    public int contarAvionesPuertasEmb() throws RemoteException;
    public int contarAvionesPistas() throws RemoteException;
    public int getViajeros() throws RemoteException;
    public String getAvionesEnAerovia() throws RemoteException;
    public void setEstadoPista(int numPista, boolean estado) throws RemoteException;
}
